package message;

/**
 * 请求信息
 */
public class RequestMessage implements RpcMessage {

    private String     messageId;
    private boolean    sync;
    private Class<?>   className;
    private String     methodName;
    private Class<?>[] parameterTypes;
    private Object[]   parameters;

    public RequestMessage() {}

    public RequestMessage(String messageId,          boolean sync,
                          Class<?> className,        String methodName,
                          Class<?>[] parameterTypes, Object[] parameters) {
        this.messageId      = messageId;
        this.sync           = sync;
        this.className      = className;
        this.methodName     = methodName;
        this.parameterTypes = parameterTypes;
        this.parameters     = parameters;
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

    public Class<?> getClassName() {
        return className;
    }

    public void setClassName(Class<?> className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}