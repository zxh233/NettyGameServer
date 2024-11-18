package com.snowcattle.game.service.net.tcp.session;

import com.snowcattle.game.service.message.INetMessage;

public class KDJLTcpSession {

    public static class LOGINSTEP{

        public static final int INIT = 0;
        public static final int LOGIN = 1;

        public static final int SEL_PLAYER = 2;
    }

    private int seed;
    private String cid;

    private String server;
    private String fenXian;

    private int GAME_STEP = LOGINSTEP.INIT;

    public KDJLTcpSession(String cid) {
        this.cid = cid;
        this.seed = getSeedByCid(cid);
        this.GAME_STEP = LOGINSTEP.LOGIN;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFenXian() {
        return fenXian;
    }

    public void setFenXian(String fenXian) {
        this.fenXian = fenXian;
    }

    private int getSeedByCid(String cid){
        int seed = 0;
        for (int k = 0; k < cid.length(); k++)
            seed += cid.charAt(k);
        return seed;
    }

    public void setGAME_STEP(int GAME_STEP) {
        this.GAME_STEP = GAME_STEP;
    }

    public int getGAME_STEP() {
        return GAME_STEP;
    }
}
