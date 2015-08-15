package com.loc.analyst.predict;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class sparkdemo {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.setAppName("demo");
		// JavaSparkContext sc = new JavaSparkContext(
		// "spark://RaydeMacBook-Pro.local:7077",
		// "demo",
		// "/opt/ibm/spark-1.4.0-bin-hadoop2.6",
		// "/Users/Ray/Documents/workspace/analyst/target/analyst-0.0.1-SNAPSHOT-jar-with-dependencies.jar");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> lines = sc.textFile("/Users/Ray/Desktop/1.csv").filter(
				s -> s.contains("天津市"));
		System.out.println("天津市出现了 ：" + lines.count() + "次");
	}
}
