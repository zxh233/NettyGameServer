package com.snowcattle.game.service.message.bean;


/**
 * 下发的精灵图数据
 * 方法O000OO
 */
public class KDJLJingLingTuData {
    private String jingLingName;
    private byte[] jingLingData;

    public KDJLJingLingTuData(String jingLingName, byte[] jingLingData) {
        this.jingLingName = jingLingName;
        this.jingLingData = jingLingData;
    }

    public String getJingLingName() {
        return jingLingName;
    }

    public void setJingLingName(String jingLingName) {
        this.jingLingName = jingLingName;
    }

    public byte[] getJingLingData() {
        return jingLingData;
    }

    public void setJingLingData(byte[] jingLingData) {
        this.jingLingData = jingLingData;
    }
}
