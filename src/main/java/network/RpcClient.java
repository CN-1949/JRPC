package network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import message.*;
import network.codec.RpcDecoder;
import network.codec.RpcEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rpc.RpcProxy;

import java.util.concurrent.TimeUnit;

public class RpcClient {

    public static final RpcClient client;

    static {
        client = new RpcClient(new NioEventLoopGroup(), "127.0.0.1", 8080);
        client.start();
    }

    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    private EventLoopGroup boos;
    private String ip;
    private int port;

    private Bootstrap boot;
    private int reconnection;

    private Channel channel;

    public RpcClient(EventLoopGroup boss, String ip, int port) {
        this.boos = boss;
        this.ip = ip;
        this.port = port;

        this.boot = new Bootstrap();
        this.reconnection = 0;
    }

    public void start() {
        try {
            channel = boot.group(boos)
                    .remoteAddress(ip, port)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(
                                    new IdleStateHandler(0, 0, 10000, TimeUnit.MILLISECONDS),
                                    new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 8, 0, 8),
                                    new LengthFieldPrepender(8),
                                    new RpcDecoder(),
                                    new RpcEncoder(),
                                    new SocketFrameHandler());
                        }
                    }).connect().sync().channel();
        } catch (Exception e) {
            logger.error("RpcClient -> start {}", e.getMessage());
            reconnection = 8;
        }
    }

    public void stop() {
        channel.close();
    }

    public void submit(RequestMessage msg) {
        channel.writeAndFlush(msg);
    }

    public boolean isActive() {
        return reconnection == 0;
    }

    private class SocketFrameHandler extends SimpleChannelInboundHandler<RpcMessage> {

        @Override
        public void channelRead0(ChannelHandlerContext ctx, RpcMessage msg) {
            try {
                if (msg instanceof ResponseMessage) {
                    ResponseMessage res = (ResponseMessage) msg;
                    if (res.isSync()) {
                        RpcProxy.putResponse(res);
                    } else if (res.hasError()) {
                        logger.error("RpcClient -> {} : ", res.getMessageId(), res.getError());
                    }
                } else if (msg instanceof DiscoveryMessage) {

                }
            } catch (Exception e) {
                logger.error("RpcClient -> channelRead0", e);
            }
        }

        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) {
            if (reconnection++ < 10) {
                ctx.channel().eventLoop().schedule(() -> {
                    try {
                        channel = boot.connect().addListener((ChannelFutureListener) future -> {
                            if (future.cause() == null) {
                                reconnection = 0;
                            }
                        }).sync().channel();
                    } catch (InterruptedException e) {
                        logger.error("RpcClient -> channelUnregistered", e);
                    }
                }, 5000, TimeUnit.MILLISECONDS);
            } else {
                stop();
            }
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent e = (IdleStateEvent) evt;
                switch (e.state()) {
                    case ALL_IDLE:
                        ctx.channel().writeAndFlush(PingMessage.ping);
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            ctx.close();
        }
    }
}