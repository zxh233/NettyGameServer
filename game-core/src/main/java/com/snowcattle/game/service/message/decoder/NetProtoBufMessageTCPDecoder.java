package com.snowcattle.game.service.message.decoder;

import com.snowcattle.game.bootstrap.manager.LocalMananger;
import com.snowcattle.game.service.lookup.NetTcpSessionLoopUpService;
import com.snowcattle.game.service.message.AbstractNetProtoBufMessage;
import com.snowcattle.game.service.net.tcp.session.NettyTcpSession;
import com.snowcattle.game.service.net.tcp.session.builder.NettyTcpSessionBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by jiangwenping on 17/2/3.
 */

public class NetProtoBufMessageTCPDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final Charset charset;

    private INetProtoBufTcpMessageDecoderFactory iNetMessageDecoderFactory;

    public NetProtoBufMessageTCPDecoder() {
        this(CharsetUtil.UTF_8);
        NetProtoBufTcpMessageDecoderFactory netProtoBufTcpMessageDecoderFactory = LocalMananger.getInstance().getLocalSpringBeanManager().getNetProtoBufTcpMessageDecoderFactory();
        this.iNetMessageDecoderFactory = netProtoBufTcpMessageDecoderFactory;
    }

    public NetProtoBufMessageTCPDecoder(Charset charset) {
        if(charset == null) {
            throw new NullPointerException("charset");
        } else {
            this.charset = charset;
        }
    }



    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        long sessonId = ctx.channel().attr(NettyTcpSessionBuilder.channel_session_id).get();
        NetTcpSessionLoopUpService netTcpSessionLoopUpService = LocalMananger.getInstance().getLocalSpringServiceManager().getNetTcpSessionLoopUpService();
        NettyTcpSession nettySession = (NettyTcpSession) netTcpSessionLoopUpService.lookup(sessonId);
        if (nettySession!=null){
            if (!nettySession.isNeedDecode()){
                parseNotDecodeMessage(ctx, msg, out, nettySession);
            }else{
                AbstractNetProtoBufMessage abstractNetProtoBufMessage = iNetMessageDecoderFactory.praseMessage(msg);
                if (abstractNetProtoBufMessage != null) {
                    out.add(abstractNetProtoBufMessage);
                }
            }
        }

    }

    private void parseNotDecodeMessage(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out,NettyTcpSession nettySession) {
        String message = msg.toString(charset);
        System.out.println("message:" + message);
        if (message.contains("CONNECT")) {
            nettySession.getKdjlTcpSession().setServer(message.substring(8,11));
            nettySession.getKdjlTcpSession().setFenXian(message.substring(11));
            return;
        }
        if (message.contains("kawa")) {
            nettySession.setNeedDecode(true);
            return;
        }
    }
}

