package com.loc.analyst.predict.online;

import org.apache.spark.SparkConf;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.dstream.ReceiverInputDStream;

public class PredictContainer extends Thread {
	private SparkConf conf = new SparkConf();
	StreamingContext context;
	ReceiverInputDStream<String> stream;

	// 初始化
	private void setup() {
		conf.setMaster("spark://127.0.0.1:7077");
		conf.setAppName("demo");
		Duration duration = new Duration(1000);
		context = new StreamingContext(conf, duration);
		stream = context.socketTextStream("localhost",8888,StorageLevel.MEMORY_ONLY());
	}

	@Override
	public void run() {
		//执行初始化
		setup();
		
	}

}
