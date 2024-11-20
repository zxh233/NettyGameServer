package com.snowcattle.game.kdjl;

import com.snowcattle.game.bootstrap.GameServer;

/**
 *
 * 口袋精灵服务器程序入口
 *
 * Created by luoji on 2024/11/20.
 */
public class GameServerKDJL extends GameServer{

    public static void main(String[] args) {
        GameServerKDJL gameServerKDJL = new GameServerKDJL();
        GlobalManagerKDJL globalManagerKDJL = new GlobalManagerKDJL();
        gameServerKDJL.setGlobalManager(globalManagerKDJL);
        gameServerKDJL.startServer();
    }
}
