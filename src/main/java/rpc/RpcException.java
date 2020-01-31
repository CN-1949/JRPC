package rpc;

/**
 * rpc 异常运行时异常处理
 */
public class RpcException extends RuntimeException {

    private int code;
    private String message;

    public RpcException() {}

    public RpcException(String message) {
        this.message = message;
    }

    public RpcException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RpcException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return message;
    }
}