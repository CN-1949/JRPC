package message;

/**
 * 心跳
 */
public class PingMessage implements RpcMessage {
    public static final PingMessage ping = new PingMessage();
}