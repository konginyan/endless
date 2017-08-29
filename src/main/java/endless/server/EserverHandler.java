package endless.server;

import io.netty.channel.ChannelHandlerContext;

public class EserverHandler extends ServerHandler{

    public EserverHandler(Server server) {
        super(server);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
