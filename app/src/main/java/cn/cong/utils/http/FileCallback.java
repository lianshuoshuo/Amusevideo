package cn.cong.utils.http;

import com.lzy.okgo.model.Progress;

public abstract class FileCallback extends JsonCallback {

    @Override
    public void downloadProgress(Progress progress) {
        progress(progress.fileName, progress.totalSize, progress.currentSize);
    }

    protected abstract void progress(String fileName, long totalSize, long currentSize);

    @Override
    public void uploadProgress(Progress progress) {
        progress(progress.fileName, progress.totalSize, progress.currentSize);
    }
}
