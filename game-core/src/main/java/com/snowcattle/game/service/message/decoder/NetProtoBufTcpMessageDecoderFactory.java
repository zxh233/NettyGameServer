package com.snowcattle.game.service.message.decoder;

import com.snowcattle.game.bootstrap.manager.LocalMananger;
import com.snowcattle.game.service.message.*;
import com.snowcattle.game.common.constant.Loggers;
import com.snowcattle.game.common.exception.CodecException;
import com.snowcattle.game.service.message.registry.MessageRegistry;
import com.snowcattle.game.service.net.tcp.MessageAttributeEnum;
import com.snowcattle.game.service.net.tcp.session.NettySession;
import com.snowcattle.game.service.net.tcp.session.NettyTcpSession;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.stereotype.Service;

/**
 * Created by jiangwenping on 17/2/3.
 */
@Service
public class NetProtoBufTcpMessageDecoderFactory implements INetProtoBufTcpMessageDecoderFactory {

    public AbstractNetProtoBufMessage praseMessage(ByteBuf byteBuf) throws CodecException {

        int byteLength1 = byteBuf.readableBytes();
        byte[] bytes = new byte[byteLength1];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes);
        int length = bytes.length;
        if (bytes.length <= 6) {
            return null;
        }
        for (int i = length-1; i >6; i--) {
            bytes[i] ^= bytes[i-1];
        }
        bytes[6] ^=bytes[4];
        //复制第6个之后的元素
        byte[] bytes6=new byte[bytes.length-6];
        System.arraycopy(bytes,6,bytes6,0,bytes6.length);
        KDJLNetMessage netMessage = new KDJLNetMessage();
        netMessage.setInCmdData(new String(bytes6));
        netMessage.setCmd(InMessageUtil.getCommand(netMessage));
        return netMessage;
    }

    @Override
    public AbstractNetProtoBufMessage praseMessage(ByteBuf byteBuf, NettySession nettySession) throws CodecException {
        int byteLength1 = byteBuf.readableBytes();
        byte[] bytes = new byte[byteLength1];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes);
        int length = bytes.length;
        if (bytes.length <= 6) {
            return null;
        }
        for (int i = length-1; i >6; i--) {
            bytes[i] ^= bytes[i-1];
        }
        bytes[6] ^=bytes[4];
        //复制第6个之后的元素
        byte[] bytes6=new byte[bytes.length-6];
        System.arraycopy(bytes,6,bytes6,0,bytes6.length);
        KDJLNetMessage netMessage = new KDJLNetMessage();
        netMessage.setInCmdData(new String(bytes6));
        netMessage.setCmd(InMessageUtil.getCommand((NettyTcpSession) nettySession,netMessage));
        return netMessage;
    }
}
