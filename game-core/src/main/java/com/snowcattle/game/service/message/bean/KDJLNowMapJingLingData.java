package com.snowcattle.game.service.message.bean;

/**
 * 下发的当前地图精灵数据，用于绘制
 */
public class KDJLNowMapJingLingData {
    private byte[] jingLingData;

    public KDJLNowMapJingLingData( byte[] jingLingData) {
        this.jingLingData = jingLingData;
    }

    public byte[] getJingLingData() {
        return jingLingData;
    }

    public void setJingLingData(byte[] jingLingData) {
        this.jingLingData = jingLingData;
    }
}
