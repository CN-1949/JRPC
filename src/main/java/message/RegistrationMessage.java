package message;

/**
 * 服务注册信息
 */
public class RegistrationMessage implements RpcMessage {

    private int        port;
    private Class<?>[] clazz;

    public RegistrationMessage() {}

    public RegistrationMessage(int port, Class<?>[] clazz) {
        this.port  = port;
        this.clazz = clazz;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Class<?>[] getClazz() {
        return clazz;
    }

    public void setClazz(Class<?>[] clazz) {
        this.clazz = clazz;
    }
}