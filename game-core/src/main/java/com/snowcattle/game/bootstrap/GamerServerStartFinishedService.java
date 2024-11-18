package com.snowcattle.game.bootstrap;

import com.snowcattle.game.bootstrap.manager.LocalMananger;
import com.snowcattle.game.common.ThreadNameFactory;
import com.snowcattle.game.common.constant.GlobalConstants;
import com.snowcattle.game.common.constant.Loggers;
import com.snowcattle.game.common.constant.ServiceName;
import com.snowcattle.game.common.util.ThreadPool;
import com.snowcattle.game.service.IService;
import com.snowcattle.game.service.net.LocalNetService;
import org.slf4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by jiangwenping on 17/3/13.
 * 服务器启动结束服务
 */
public class GamerServerStartFinishedService implements IService{

    private final Logger logger = Loggers.serverLogger;
    private LocalNetService localNetService;

    private ThreadPool threadPool;

    public LocalNetService getLocalNetService() {
        return localNetService;
    }

    public void setLocalNetService(LocalNetService localNetService) {
        this.localNetService = localNetService;
    }

    @Override
    public String getId() {
        return ServiceName.GamerServerStartFinishedService;
    }

    @Override
    public void startup() throws Exception {
        int coreSize = 3;
        ThreadNameFactory threadNameFactory = new ThreadNameFactory(GlobalConstants.Thread.START_FINISHED);
        localNetService = LocalMananger.getInstance().get(LocalNetService.class);
        threadPool = new ThreadPool(coreSize, coreSize, 0, new ArrayBlockingQueue<Runnable>(coreSize),threadNameFactory, new ThreadPoolExecutor.DiscardOldestPolicy());
        threadPool.start();
        logger.info("netty server sync start finished");

    }

    @Override
    public void shutdown() throws Exception {
       threadPool.stop();
    }

    public ThreadPool getThreadPool() {
        return threadPool;
    }
}
