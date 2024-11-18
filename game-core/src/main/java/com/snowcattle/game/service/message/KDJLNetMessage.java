package com.snowcattle.game.service.message;

import com.snowcattle.game.common.exception.CodecException;

public class KDJLNetMessage extends AbstractNetProtoBufMessage{
    private byte[] outBtytes;

    private int inCmd;
    private String inCmdData;

    public byte[] getOutBtytes() {
        return outBtytes;
    }

    public void setOutBtytes(byte[] outBtytes) {
        this.outBtytes = outBtytes;
    }

    public int getCmd() {
        return inCmd;
    }

    public void setCmd(int inCmd) {
        this.inCmd = inCmd;
    }

    public String getInCmdData() {
        return inCmdData;
    }

    public void setInCmdData(String inCmdData) {
        this.inCmdData = inCmdData;
    }

    @Override
    public void decoderNetProtoBufMessageBody() throws Exception {

    }

    @Override
    public void release() throws CodecException {

    }

    @Override
    public void encodeNetProtoBufMessageBody() throws Exception {

    }
}
