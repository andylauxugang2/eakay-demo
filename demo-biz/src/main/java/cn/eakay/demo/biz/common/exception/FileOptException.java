package cn.eakay.demo.biz.common.exception;

/**
 * 文件操作异常 包括上传 下载 删除 业务使用
 * Created by xugang on 16/4/13.
 */
public class FileOptException extends RuntimeException {
    private static final long serialVersionUID = -307372475976201841L;

    public FileOptException(String message) {
        super(message);
    }

    public FileOptException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileOptException(Throwable cause) {
        super(cause);
    }
}
