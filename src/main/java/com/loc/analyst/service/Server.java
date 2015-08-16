package com.loc.analyst.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

import com.loc.analyst.crontab.Job;
import com.loc.analyst.predict.offline.DisPre;
import com.loc.analyst.predict.offline.PredictCrontab;
import com.loc.analyst.recommand.offline.RecommandCrontab;
import com.loc.analyst.util.Constant;

public class Server {
	public static void main(String[] args) {
		try {
			SparkConf conf = new SparkConf();
			JavaSparkContext sc = new JavaSparkContext(conf);
//			crontab(args);
			DisPre.main(args,sc);
			RecommandCrontab.main(args,sc);
			TProcessor tprocessor = new baymax.Processor(new baymaxImpl());
			TServerSocket serverTransport = new TServerSocket(
					Constant.SERVER_PORT);
			org.apache.thrift.server.TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(
					serverTransport);
			tArgs.processor(tprocessor);
		tArgs.protocolFactory(new TBinaryProtocol.Factory());
			TServer server = new TThreadPoolServer(tArgs);
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void crontab(String[] args){
		ScheduledExecutorService offline_service = Executors
				.newScheduledThreadPool(1);
		offline_service.schedule(new Job(args), 0, Constant.OFFLINE_PERIOD);
	}
}
