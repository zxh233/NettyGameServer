package com.snowcattle.game.service.message;

import com.snowcattle.game.service.message.bean.*;
import com.snowcattle.game.service.net.tcp.session.NettySession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class OutMessageUtil {

    public static byte[] generateOut(AbstractNetMessage message,
                                     NettySession session,
                                     KDJLMapData mapData,// 地图 方法O0OOOOO
                                     List<KDJLImageData> imageData,// 图片 方法O0O0
                                     List<KDJLJingLingTuData> jingLingList,// 精灵图  方法O000OO
                                     List<KDJLNowMapJingLingData> currentMapJingLingList,// 当前地图精灵
                                     List<KDJLStringData> strings){// 字符串
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {


            //地图
            if (mapData != null) {
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(127);
                byteArrayOutputStream.write(mapData.getMapName().getBytes());
                byteArrayOutputStream.write(0);
                int len = mapData.getMapData().length;
                byteArrayOutputStream.write(len & 0xff);
                byteArrayOutputStream.write((len >> 8) & 0xff);
                byteArrayOutputStream.write(mapData.getMapData());
            }

            //图片
            if (imageData != null && imageData.size() > 0) {
                for (KDJLImageData imageData1 : imageData) {
                    byteArrayOutputStream.write(0);
                    byteArrayOutputStream.write(0);
                    byteArrayOutputStream.write(126);
                    byteArrayOutputStream.write(imageData1.getImageName().getBytes());
                    byteArrayOutputStream.write(0);
                    int len = imageData1.getImageData().length;
                    byteArrayOutputStream.write(len & 0xff);
                    byteArrayOutputStream.write((len >> 8) & 0xff);
                    byteArrayOutputStream.write(imageData1.getImageData());
                }
            }

            //精灵图
            if (jingLingList != null && jingLingList.size() > 0) {
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(125);
                byteArrayOutputStream.write(jingLingList.size());
                for (KDJLJingLingTuData jingLingTuData : jingLingList) {
                    int len1 = jingLingTuData.getJingLingData().length;
                    byteArrayOutputStream.write(len1 & 0xff);
                    byteArrayOutputStream.write((len1 >> 8) & 0xff);
                    int len2 = jingLingTuData.getJingLingId();
                    byteArrayOutputStream.write(len2 & 0xff);
                    byteArrayOutputStream.write((len2 >> 8) & 0xff);
                    byteArrayOutputStream.write(jingLingTuData.getJingLingData());
                }
            }

            //当前地图精灵
            if (currentMapJingLingList != null && currentMapJingLingList.size() > 0) {

                for (KDJLNowMapJingLingData nowMapJingLingData : currentMapJingLingList) {
                    byteArrayOutputStream.write(nowMapJingLingData.getJingLingData());
                }
            }

            //字符串
            for (KDJLStringData stringData : strings) {
                byteArrayOutputStream.write(stringData.getStringName().getBytes());
                byteArrayOutputStream.write(10);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

}
