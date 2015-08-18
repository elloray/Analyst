package com.loc.analyst.crontab;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import com.loc.analyst.predict.offline.PredictCrontab;
import com.loc.analyst.recommand.offline.RecommendCrontab;

public class Job extends Thread {
	public static String[] args = {};
	SparkConf conf;
	JavaSparkContext sc;

	@Override
	public void run() {
		PredictCrontab.main(args, sc);
		RecommendCrontab.main(args, sc);
	}

	public Job(String[] args) {
		Job.args = args;
		conf = new SparkConf();
		sc = new JavaSparkContext(conf);
	}
}
