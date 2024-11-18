package com.snowcattle.game.service.message;

import com.snowcattle.game.service.net.tcp.session.NettyTcpSession;

import java.util.regex.Pattern;

public class InMessageUtil {


    public static class CommandConstants {
        public static final int LOGIN = 2000;
        public static final int NEW_LOGIN = 2001;
        public static final int NOMAL_DEAL = 2002;
    }


    public static int getCommand(NettyTcpSession nettyTcpSession, KDJLNetMessage kdjlNetMessage) {
        if (kdjlNetMessage.getInCmdData().contains("new 1")){
            return CommandConstants.NEW_LOGIN;
        }
        if (Pattern.compile(".*#maps 60.*").matcher(kdjlNetMessage.getInCmdData()).find()){
            return CommandConstants.LOGIN;
        }
        return CommandConstants.NOMAL_DEAL;
    }
    public static int getCommand(KDJLNetMessage kdjlNetMessage) {
        return getCommand(null, kdjlNetMessage);
    }


}
