package cn.cong.utils.pic;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;
import java.util.List;

public class PicUtils {

    public static final int CENTER_CROP = 1;
    public static final int CENTER_FIT = 2;
    public static final int CENTER_INSIDE = 3;

    public static final int TRANSFORM_NO = 0;
    public static final int TRANSFORM_CIRCLE = 1;
    public static final int TRANSFORM_ROUNDED = 2;

    private PicUtils(PicParams params) {
        GlideRequests requests = GlideApp.with(params.ctx);
        // 确定load中的类型（字符串、图片资源id、Uri）
        Object u = null;
        if (params.loadType == String.class) {
            u = params.url;
        } else if (params.loadType == Integer.class) {
            u = params.imgId;
        } else if (params.loadType == Uri.class) {
            u = params.uri;
        }
        // 判断是否是Gif图
        GlideRequest load;
        if (params.isGif) {
            load = requests.asGif().load(u);
        } else {
            load = requests.load(u);
        }
        // 处理预览图、加载错误图
        if (params.preImgId != 0) load.placeholder(params.preImgId);
        if (params.errorImgId != 0) load.placeholder(params.errorImgId);
        // 判断是否跳过缓存
        if (params.noDiskCache) load.diskCacheStrategy(DiskCacheStrategy.NONE);
        if (params.noMemoryCache) load.skipMemoryCache(true);
        // 加载样式
        load.transforms((Transformation<Bitmap>[]) params.tranList.toArray()).into(params.target);

    }


    public static Builder builder(Context ctx) {
        return new Builder(ctx);
    }

    // 为了创建PicUtils的对象
    public static class Builder {

        // 保存配置
        private final PicParams params;

        private Builder(Context ctx) {
            params = new PicParams();
            params.ctx = ctx;
        }

        public Builder load(String url) {
            params.loadType = String.class;
            params.url = url;
            return this;
        }

        public Builder load(Integer imgId) {
            params.loadType = Integer.class;
            params.imgId = imgId;
            return this;
        }

        public Builder load(Uri uri) {
            params.loadType = Uri.class;
            params.uri = uri;
            return this;
        }

        public Builder asGif() {
            params.isGif = true;
            return this;
        }

        public Builder setPlaceholder(int preImgId) {
            params.preImgId = preImgId;
            return this;
        }

        public Builder setError(int errorImgId) {
            params.preImgId = errorImgId;
            return this;
        }

        public Builder noDiskCache() {
            params.noDiskCache = true;
            return this;
        }

        public Builder noMemoryCache() {
            params.noMemoryCache = true;
            return this;
        }

        public Builder addCenterType(int type) {
            switch (type) {
                case CENTER_CROP:
                    params.tranList.add(new CenterCrop());
                    break;
                case CENTER_FIT:
                    params.tranList.add(new FitCenter());
                    break;
                case CENTER_INSIDE:
                    params.tranList.add(new CenterInside());
                    break;
            }
            return this;
        }

        public Builder addTransform(int transform, int px) {
            switch (transform) {
                case TRANSFORM_NO:
                    break;
                case TRANSFORM_CIRCLE:
                    params.tranList.add(new CircleCrop());
                    break;
                case TRANSFORM_ROUNDED:
                    params.tranList.add(new RoundedCorners(px));
                    break;
            }
            return this;
        }

        public PicUtils build(ImageView iv) {
            params.target = iv;
            return new PicUtils(params);
        }


    }

    // 保存配置
    private static class PicParams {
        private Context ctx;

        private Class loadType;
        private String url;
        private Integer imgId;
        private Uri uri;

        private int preImgId;
        private int errorImgId;
        private boolean isGif = false;
        private boolean noDiskCache;
        private boolean noMemoryCache;
        private List<Transformation<Bitmap>> tranList = new ArrayList<>();
        private ImageView target;
    }

}
