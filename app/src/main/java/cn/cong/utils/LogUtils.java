package cn.cong.utils;

import android.util.Log;

import cn.cong.utils.app.AppContent;


public class LogUtils {

    private static final String defTag = AppContent.DEF_TAG;
    private static final boolean isDebug = AppContent.IS_DEBUG;

    public static void d(String tag, String msg) {
        // 当打包为正式版后，debug值自动为false，则不再输出log日志（为了节省资源）
        if (isDebug) {
            Log.d(tag, msg);
            print(tag, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(defTag, msg);
            print(defTag, msg);
        }
    }

    public static void e(String tag, String msg) {
        // 当打包为正式版后，debug值自动为false，则不再输出log日志（为了节省资源）
        if (isDebug) {
            Log.e(tag, msg);
            print(tag, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(defTag, msg);
            print(defTag, msg);
        }
    }

    private static void print(String tag, String msg) {
        // TODO: 18/07/25 025 把log日志输出到本地文件中。。输出位txt文档
        // IO流、线程、队列结构


    }

}
