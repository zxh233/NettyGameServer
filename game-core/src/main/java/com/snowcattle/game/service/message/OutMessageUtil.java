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
                                     List<KDJLStringData> strings) {// 字符串
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            for (KDJLStringData stringData : strings) {
                byteArrayOutputStream.write(stringData.getStringName().getBytes());
                byteArrayOutputStream.write(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

}
