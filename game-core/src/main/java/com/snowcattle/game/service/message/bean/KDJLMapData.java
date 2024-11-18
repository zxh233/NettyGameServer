package com.snowcattle.game.service.message.bean;


/**
 * 下发的地图数据
 *   方法O0OOOOO
 */
public class KDJLMapData {
    private String mapName;
    private byte[] mapData;

    public KDJLMapData(String mapName, byte[] mapData) {
        this.mapName = mapName;
        this.mapData = mapData;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public byte[] getMapData() {
        return mapData;
    }

    public void setMapData(byte[] mapData) {
        this.mapData = mapData;
    }
}
