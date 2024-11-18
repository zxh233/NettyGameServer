package com.snowcattle.game.service.message.bean;


/**
 *    下发图片数据
 *   方法O0O0
 */
public class KDJLImageData {

    private String imageName;
    private byte[] imageData;

    public KDJLImageData(String imageName, byte[] imageData) {
        this.imageName = imageName;
        this.imageData = imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
