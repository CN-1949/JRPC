package network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import kryo.KryoUtil;

import java.util.List;

/**
 * rpc 解码器
 */
public class RpcDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int length = in.readableBytes();
        byte[] code = new byte[length];
        in.readBytes(code);
        out.add(KryoUtil.readFromByteArray(code));
    }
}