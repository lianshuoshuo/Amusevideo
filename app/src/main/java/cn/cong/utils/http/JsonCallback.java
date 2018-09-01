package cn.cong.utils.http;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

public abstract class JsonCallback extends StringCallback {

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        onStart(request.getTag());
    }

    public abstract void onStart(Object tag);

    @Override
    public void onSuccess(Response<String> response) {
        onSuccess(response.body());
    }

    public abstract void onSuccess(String json);

    @Override
    public void onError(Response<String> response) {
        onError(response.getException());
    }

    public abstract void onError(Throwable exception);

    @Override
    public abstract void onFinish();
}
