package message;

/**
 * 响应信息
 */
public class ResponseMessage implements RpcMessage {

    private String    messageId;
    private boolean   sync;
    private Object    result;
    private Throwable error;

    public ResponseMessage() {}

    public ResponseMessage(String messageId, boolean sync, Object result) {
        this.messageId = messageId;
        this.sync      = sync;
        this.result    = result;
    }

    public ResponseMessage(String messageId, boolean sync, Throwable error) {
        this.messageId = messageId;
        this.sync      = sync;
        this.error     = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}