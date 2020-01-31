package message;

/**
 * 服务发现信息
 */
public class DiscoveryMessage implements RpcMessage {

    private String ip;
    private int port;
    private Class<?>[] clazz;

    public DiscoveryMessage() {}

    public DiscoveryMessage(String ip, int port, Class<?>[] clazz) {
        this.ip = ip;
        this.port = port;
        this.clazz = clazz;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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