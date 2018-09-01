package cn.cong.utils;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


// 降低耦合度-二次封装

// 添加Gson依赖:
// implementation 'com.google.code.gson:gson:2.8.5'

// 使用举例：
//  例1：User user = JsonUtils.toBean(json,User.class);
//  例2：Result<User> result = JsonUtils.toBean(Json,JsonUtils.newParamType(Result.class,User.class));
//　Result<List<User>> - newParamType(Result.class, newParamType(List.class,User.class));

public class JsonUtils {

    /**
     * @param json
     * @param type 不带泛型的类型，可直接XX.class；带泛型的类型，请使用newParamType()方法创建Type
     * @return
     */
    public static <T> T toBean(String json, Type type) {
        return new Gson().fromJson(json, type); // Type-帮我们记录泛型的Type
    }


    /**
     * 创建带有泛型<T>的类型type
     *
     * @param type   原类型
     * @param params 原类型的泛型，可多个
     * @return
     */
    public static Type newParamType(Type type, Type... params) {
        if (params.length == 0) {
            return type;
        } else {
            return new JsonType(type, params);
        }
    }

    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    // ParameterizedType : 带参数的类型-带泛型的类型
    // 根据ParameterizedType接口的规范，创建自定义类，为了【记录泛型数据】
    private static class JsonType implements ParameterizedType {
        // 以Result<User>为例子：

        // 记录原类型-Result
        private Type type;
        // 记录泛型-User（数组：如Map<String,User>是2个泛型，存储多个则需要集合或数组）
        private Type[] params;

        private JsonType(Type type, Type... params) {
            this.type = type;
            this.params = params;
        }

        @Override
        public Type[] getActualTypeArguments() { // 返回 泛型信息 User
            return params;
        }

        @Override
        public Type getRawType() { // 返回 原类型信息 Result
            return type;
        }

        @Override
        public Type getOwnerType() { // 要 拥有者 当类是内部类，这里对应他的外部类
            return null;
        }
    }

 }
