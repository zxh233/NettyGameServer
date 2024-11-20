package com.snowcattle.game.message.handler.impl.online;

import com.snowcattle.game.bootstrap.GamerServerStartFinishedService;
import com.snowcattle.game.common.annotation.MessageCommandAnnotation;
import com.snowcattle.game.common.constant.Loggers;
import com.snowcattle.game.common.exception.NetMessageException;
import com.snowcattle.game.kdjl.map.MapUtils;
import com.snowcattle.game.logic.player.GamePlayer;
import com.snowcattle.game.bootstrap.manager.LocalMananger;
import com.snowcattle.game.message.handler.AbstractMessageHandler;
import com.snowcattle.game.message.logic.tcp.online.client.OnlineLoginClientTcpMessage;
import com.snowcattle.game.message.logic.tcp.online.server.OnlineLoginServerTcpMessage;
import com.snowcattle.game.service.lookup.GamePlayerLoopUpService;
import com.snowcattle.game.service.message.InMessageUtil;
import com.snowcattle.game.service.message.KDJLNetMessage;
import com.snowcattle.game.service.message.OutMessageUtil;
import com.snowcattle.game.service.message.bean.KDJLJingLingTuData;
import com.snowcattle.game.service.message.bean.KDJLMapData;
import com.snowcattle.game.service.message.bean.KDJLNowMapJingLingData;
import com.snowcattle.game.service.message.bean.KDJLStringData;
import com.snowcattle.game.service.net.tcp.MessageAttributeEnum;
import com.snowcattle.game.service.message.AbstractNetMessage;
import com.snowcattle.game.service.message.command.MessageCommandIndex;
import com.snowcattle.game.service.net.tcp.session.KDJLTcpSession;
import com.snowcattle.game.service.net.tcp.session.NettyTcpSession;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jiangwenping on 17/2/21.
 */
public class OnlineTcpHandlerImpl extends AbstractMessageHandler {

    private final AtomicLong id = new AtomicLong();

    @MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_LOGIN_TCP_CLIENT_MESSAGE)
    public AbstractNetMessage handleOnlineLoginClientTcpMessage(OnlineLoginClientTcpMessage message) throws Exception {
        OnlineLoginServerTcpMessage onlineLoginServerTcpMessage = new OnlineLoginServerTcpMessage();
        long playerId = 6666 + id.incrementAndGet();
        int tocken = 333;
        onlineLoginServerTcpMessage.setPlayerId(playerId);
        onlineLoginServerTcpMessage.setTocken(tocken);
        if (Loggers.sessionLogger.isDebugEnabled()) {
            Loggers.sessionLogger.debug( "playerId " + playerId + "tocken " + tocken + "login");
        }
        NettyTcpSession clientSesion = (NettyTcpSession) message.getAttribute(MessageAttributeEnum.DISPATCH_SESSION);
        GamePlayer gamePlayer = new GamePlayer(clientSesion.getNettyTcpNetMessageSender(), playerId, tocken);
        GamePlayerLoopUpService gamePlayerLoopUpService = LocalMananger.getInstance().getLocalSpringServiceManager().getGamePlayerLoopUpService();
        gamePlayerLoopUpService.addT(gamePlayer);
        return onlineLoginServerTcpMessage;
    }


    @MessageCommandAnnotation(command = InMessageUtil.CommandConstants.LOGIN)
    public AbstractNetMessage login(AbstractNetMessage message) {
        if (message instanceof KDJLNetMessage) {
            NettyTcpSession nettyTcpSession = (NettyTcpSession) message.getAttribute(MessageAttributeEnum.DISPATCH_SESSION);
            message.removeAttribute(MessageAttributeEnum.DISPATCH_SESSION);
            KDJLNetMessage kdjlNetMessage = (KDJLNetMessage) message;
            System.out.println(kdjlNetMessage.getInCmdData());
            KDJLNetMessage kdjlNetMessage1 = new KDJLNetMessage();
            kdjlNetMessage1.setOutBtytes(OutMessageUtil.generateOut(message, nettyTcpSession, null, null, null, null, Arrays.asList(new KDJLStringData("<cid>" + nettyTcpSession.getKdjlTcpSession().getCid()),new KDJLStringData("<newacc>123456:123456"),new KDJLStringData("<r>charset|[1]53:菁裆哈哈红红火火恍恍惚惚|[2]53:好果汁|[3]54:太宋豪侠"))));
            nettyTcpSession.getKdjlTcpSession().setGAME_STEP(KDJLTcpSession.LOGINSTEP.SEL_PLAYER);
            return kdjlNetMessage1;
        }
        return null;
    }

    @MessageCommandAnnotation(command = InMessageUtil.CommandConstants.NOMAL_DEAL)
    public AbstractNetMessage deal(AbstractNetMessage message) {
        if (message instanceof KDJLNetMessage) {
            NettyTcpSession nettyTcpSession = (NettyTcpSession) message.getAttribute(MessageAttributeEnum.DISPATCH_SESSION);
            message.removeAttribute(MessageAttributeEnum.DISPATCH_SESSION);
            KDJLNetMessage kdjlNetMessage = (KDJLNetMessage) message;
            System.out.println(kdjlNetMessage.getInCmdData());
            KDJLNetMessage kdjlNetMessage1 = new KDJLNetMessage();
            kdjlNetMessage1.setOutBtytes(OutMessageUtil.generateOut(message, nettyTcpSession, null, null, null, null, Arrays.asList(new KDJLStringData("<cid>" + nettyTcpSession.getKdjlTcpSession().getCid()),new KDJLStringData("<newacc>123456:123456"),new KDJLStringData("<r>charset|[1]53:菁裆哈哈红红火火恍恍惚惚|[2]53:好果汁|[3]54:太宋豪侠"))));
            nettyTcpSession.getKdjlTcpSession().setGAME_STEP(KDJLTcpSession.LOGINSTEP.SEL_PLAYER);
            return kdjlNetMessage1;
        }
        return null;
    }

    @MessageCommandAnnotation(command = InMessageUtil.CommandConstants.SEL_PLAYER)
    public AbstractNetMessage selPlayer(AbstractNetMessage message) {
        if (message instanceof KDJLNetMessage) {
            NettyTcpSession nettyTcpSession = (NettyTcpSession) message.getAttribute(MessageAttributeEnum.DISPATCH_SESSION);
            message.removeAttribute(MessageAttributeEnum.DISPATCH_SESSION);
            KDJLNetMessage kdjlNetMessage = (KDJLNetMessage) message;
            System.out.println(kdjlNetMessage.getInCmdData());
            KDJLNetMessage kdjlNetMessage1 = new KDJLNetMessage();
            kdjlNetMessage1.setOutBtytes(OutMessageUtil.generateOut(message, nettyTcpSession,
                    new KDJLMapData("sgz/xunlianchang3.sj",
                            MapUtils.getMap("sgz","xunlianchang3")), null, null,
                    Arrays.asList(new KDJLNowMapJingLingData(new byte[]{0, 23, 1, -107, 115, 103, 122, 47, 120, 117, 110, 108, 105, 97, 110, 99, 104, 97, 110, 103, 51, 46, 115, 99, 0}),
                            new KDJLNowMapJingLingData(new byte[]{0, 59, 4, -112, -27, -82, -74, -26, -105, -113, -25, -82, -95, -25, -112, -122, -27, -111, -104, 0, 127, 56, 79, 0, 127, -24, 35, 2, 8, -106, -27, -82, -96, -25, -119, -87, -23, -87, -81, -27, -123, -69, -23, -94, -122, -27, -91, -106, -27, -92, -124, 0, 127, -82, 16, 0, 127, -23, 35, 7, 6}),
                            new KDJLNowMapJingLingData(new byte[]{0, 27, 4, -111, -24, -113, -127, -24, -93, -122, 29, -26, -105, -96, -25, -89, -80, -27, -113, -73, 0, 127, 0, 30, 6, 1, 0, 9, 0}),
                            new KDJLNowMapJingLingData(new byte[]{0, 12, 4, -127, 0, 127, 42, 15, 6, 127, -56, 0, 0, 9})
                            ),
                    Arrays.asList(new KDJLStringData("<log_suc>"),new KDJLStringData("<setStarKey>3"),new KDJLStringData("<r>cdc 0,0,0"),
                            new KDJLStringData("<r>ctc 0"),new KDJLStringData("<r>walk 1"),new KDJLStringData("<title>光芒市场"),
                            new KDJLStringData("<r>tns 50|↑ 罗克萨斯家,80|← 光芒市场,120|→ 时光之路,"),new KDJLStringData("<cnt>"),new KDJLStringData("<r>cnt"),
                            new KDJLStringData("<r>killcycle 20"),new KDJLStringData("<setv>67"),new KDJLStringData("<r>exp 1,1")
                            )));
            nettyTcpSession.getKdjlTcpSession().setGAME_STEP(KDJLTcpSession.LOGINSTEP.SEL_PLAYER);
            return kdjlNetMessage1;
        }
        return null;
    }

    @MessageCommandAnnotation(command = InMessageUtil.CommandConstants.NEW_LOGIN)
    public AbstractNetMessage newLogin(AbstractNetMessage message) {
        if (message instanceof KDJLNetMessage) {
            NettyTcpSession nettyTcpSession = (NettyTcpSession) message.getAttribute(MessageAttributeEnum.DISPATCH_SESSION);
            message.removeAttribute(MessageAttributeEnum.DISPATCH_SESSION);
            KDJLNetMessage kdjlNetMessage = (KDJLNetMessage) message;
            System.out.println(kdjlNetMessage.getInCmdData());
            KDJLNetMessage kdjlNetMessage1 = new KDJLNetMessage();
            //kdjlNetMessage1.setOutBtytes(OutMessageUtil.generateOut(message, nettyTcpSession, null, null, null, null, Arrays.asList(new KDJLStringData("<cid>uHaq3Eg7VaNk1JEF2L"),new KDJLStringData("<!--TROODONMARK-->"),new KDJLStringData("<newproxy>#6_192.168.5.161:7090|s192.168.5.161:7090|s192.168.5.161:7090|s192.168.5.161:7090|h192.168.5.161:7090:5926"),new KDJLStringData("<cache>borough:<menu(g:a)(b)(sr:11000100)(line:333333,0,0,0,0,0,0,0)>选择服务器|[1p2j0#6]卡车镇( \u001B3新开\u001B0 )"))));
            try {
                nettyTcpSession.close();
            } catch (NetMessageException e) {
                throw new RuntimeException(e);
            }
//            GamerServerStartFinishedService gamerServerStartFinishedService = LocalMananger.getInstance().get(GamerServerStartFinishedService.class);
//            gamerServerStartFinishedService.getThreadPool().addDelayTask(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        nettyTcpSession.close();
//                    } catch (NetMessageException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, 100, TimeUnit.MILLISECONDS);
            return null;
        }
        return null;
    }


}
