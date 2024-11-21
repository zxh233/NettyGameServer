package com.snowcattle.game.service.message.decoder;

import com.snowcattle.game.common.exception.CodecException;
import com.snowcattle.game.service.message.AbstractNetProtoBufMessage;
import com.snowcattle.game.service.net.tcp.session.NettySession;
import io.netty.buffer.ByteBuf;

/**
 * Created by jiangwenping on 2017/9/28.
 */
public interface INetProtoBufMessageDecoderFactory {
    public AbstractNetProtoBufMessage praseMessage(ByteBuf byteBuf) throws CodecException;
    public AbstractNetProtoBufMessage praseMessage(ByteBuf byteBuf, NettySession nettySession) throws CodecException;
}
