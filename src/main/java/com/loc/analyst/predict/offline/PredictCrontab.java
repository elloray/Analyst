package com.loc.analyst.predict.offline;

import java.util.HashMap;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class PredictCrontab extends Thread {
	@Override
	public void run() {
		SparkConf conf = new SparkConf();
		conf.setAppName("predict-offline");
		JavaSparkContext sc = new JavaSparkContext(conf);
		// JavaRDD<String> lines = sc.textFile("", 1)
		// .toJavaRDD().filter(s -> s.contains("华北区"));
		JavaRDD<HashMap<String, HashMap<String, String>>> lines = sc
				.textFile("1.csv")
				.map(line -> {
					HashMap<String, HashMap<String, String>> resMap = new HashMap<String, HashMap<String, String>>();
					String[] dims = line.split(" ");
					HashMap<String, String> dimMap = new HashMap<String, String>();
					for (int i = 1; i < dims.length; i++) {
						dimMap.put("", dims[i]);
					}
					resMap.put(dims[0], dimMap);
					return resMap;
				});
		JavaRDD<HashMap<String, HashMap<String, String>>> res = lines
				.union(lines);
		System.out.println(lines.count());
		sc.close();
	}
}
