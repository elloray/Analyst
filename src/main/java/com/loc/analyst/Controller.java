package com.loc.analyst;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.log4j.Logger;

import com.loc.analyst.predict.offline.PredictCrontab;
import com.loc.analyst.predict.online.PredictContainer;
import com.loc.analyst.recommand.offline.RecommandCrontab;
import com.loc.analyst.util.Constant;

public class Controller {

	private static Logger logger = Logger.getLogger(Controller.class);

	public static void main(String[] args) {
		// 启动每天定时离线任务
		startOfflineService();
		// 启动在线实时任务
		startOnlineService();
	}

	private static void startOfflineService() {
		ScheduledExecutorService offline_service = Executors
				.newScheduledThreadPool(2);
		offline_service.schedule(new PredictCrontab(), 0,
				Constant.OFFLINE_PERIOD);
		offline_service.schedule(new RecommandCrontab(), 0,
				Constant.OFFLINE_PERIOD);
		logger.info("offline service is started");
	}

	private static void startOnlineService() {
		ExecutorService online_service = Executors.newFixedThreadPool(2);
		online_service.submit(new PredictContainer());
	}
}
