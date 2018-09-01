package cn.cong.utils.pic;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import cn.cong.utils.app.AppContent;

/*
implementation ('com.github.bumptech.glide:glide:4.7.1'){
    exclude group:"com.android.support"
}
annotationProcessor 'com.github.bumptech.glide:compiler:4.7.1'
 */

@GlideModule
public class InitGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {

        String cachePath = context.getCacheDir().getPath() + "/img";
        long diskCacheSize = 1024 * 1024 * 100;
        long memoryCacheSize = 1024 * 1024 * 20;


        builder
                // 配置磁盘缓存 disk硬盘 cache缓存 Lru最大最少管理规则
                .setDiskCache(new DiskLruCacheFactory(cachePath, diskCacheSize))
                // 配置内存缓存
                .setMemoryCache(new LruResourceCache(memoryCacheSize))
                .setDefaultRequestOptions(new RequestOptions()
                        .error(AppContent.errorId)
                        .placeholder(AppContent.placeHolderId));

    }
}
