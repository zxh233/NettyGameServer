package com.snowcattle.game.service.message.bean;


/**
 * 下发的精灵图数据
 * 方法O000OO
 */
public class KDJLJingLingTuData {
    private int jingLingId;
    private byte[] jingLingData;

    public KDJLJingLingTuData(int jingLingId, byte[] jingLingData) {
        this.jingLingId = jingLingId;
        this.jingLingData = jingLingData;
    }

    public int getJingLingId() {
        return jingLingId;
    }

    public void setJingLingId(int jingLingId) {
        this.jingLingId = jingLingId;
    }

    public byte[] getJingLingData() {
        return jingLingData;
    }

    public void setJingLingData(byte[] jingLingData) {
        this.jingLingData = jingLingData;
    }
}
