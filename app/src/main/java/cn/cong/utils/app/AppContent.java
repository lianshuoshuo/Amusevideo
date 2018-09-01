package cn.cong.utils.app;


import cn.cong.amusevideo.BuildConfig;
import cn.cong.amusevideo.R;

// 为了减少项目代码与Utils包代码的耦合度
public class AppContent {

    public static final String DEF_TAG = BuildConfig.APPLICATION_ID;
    public static final boolean IS_DEBUG = BuildConfig.DEBUG;
    public static final String APP_NAME = "Amusevideo";

    // SP的文件名
    public static final String SP_NAME = APP_NAME;

    // 默认图片
    public static final int placeHolderId = R.mipmap.placehold;
    // 加载错误的图片
    public static final int errorId = R.mipmap.error;


    // SP所用的key值
    public static final String SP_USER_TOKEN = "SP_USER_TOKEN";
    public static final String SP_USER_INFO = "SP_USER_INFO";


    // 数据库


    // ~~~


}
