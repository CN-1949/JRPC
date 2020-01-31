package network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import kryo.KryoUtil;
import message.RpcMessage;

/**
 * rpc 编码器
 */
public class RpcEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) {
        if (in instanceof RpcMessage) {
            byte[] bytes = KryoUtil.writeToByteArray(in);
            out.writeBytes(bytes);
        }
    }
}