package com.snowcattle.game.service.message.encoder;

import com.snowcattle.game.service.message.AbstractNetProtoBufMessage;
import com.snowcattle.game.service.message.KDJLNetMessage;
import com.snowcattle.game.service.message.NetMessageBody;
import com.snowcattle.game.service.message.NetMessageHead;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.stereotype.Service;

/**
 * Created by jiangwenping on 17/2/8.
 */
@Service
public class NetProtoBufTcpMessageEncoderFactory implements INetProtoBufTcpMessageEncoderFactory {

    @Override
    public ByteBuf createByteBuf(AbstractNetProtoBufMessage netMessage) throws Exception {
        if (netMessage instanceof KDJLNetMessage){
            int length = ((KDJLNetMessage) netMessage).getOutBtytes().length;
            ByteBuf byteBuf = Unpooled.buffer(length+6);
            byte[] head = getHead(length);
            byteBuf.writeBytes(head);
            getBody(((KDJLNetMessage) netMessage).getOutBtytes(),head[4]);
            byteBuf.writeBytes(((KDJLNetMessage) netMessage).getOutBtytes());
            return byteBuf;
        }
        return null;
    }

    private byte[] getHead(int len){
        byte[] head = new byte[6];
        head[0] = (byte) 0x7f;
        head[1] = (byte) 0x7f;
        head[2] = (byte) 0x7f;
        head[3] = (byte) 0x7f;
        int lenEncode = len^0xa5a5;
        head[4] = (byte) (lenEncode & 0xff);
        head[5] = (byte) ((lenEncode >> 8) & 0xff);
        return head;
    }

    private void getBody(byte[] bytes,byte byte0){
        if (bytes!=null&&bytes.length>0){
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] ^= byte0;
                byte0 = bytes[i];
            }
        }
    }
}

