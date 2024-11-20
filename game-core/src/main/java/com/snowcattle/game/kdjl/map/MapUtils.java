package com.snowcattle.game.kdjl.map;

import com.snowcattle.game.Util;
import com.snowcattle.game.common.util.ResourceUtil;

public class MapUtils {


    public static byte[] getMap(String world, String map) {
        //从resource中读取地图数据
        String string = ResourceUtil.getTextFormResource("kdjl/maps/"+world + "/" + map + ".sj.txt");
        System.out.println("地图 = " + string);
        return Util.hexStringToByteArray(string);

    }

}
