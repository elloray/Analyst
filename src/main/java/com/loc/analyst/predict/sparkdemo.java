package com.loc.analyst.predict;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class sparkdemo {
	public static void main(String[] args) {
		JavaSparkContext sc = new JavaSparkContext();
		// "spark://RaydeMacBook-Pro.local:7077",
		// "demo",
		// "/opt/ibm/spark-1.4.0-bin-hadoop2.6",
		// "/Users/Ray/Documents/workspace/analyst/target/analyst-0.0.1-SNAPSHOT.jar");
		JavaRDD<String> lines = sc.textFile("/Users/Ray/Desktop/demo.csv");
		JavaPairRDD<String, String> kvpair = lines.mapToPair(s -> {
			String[] tmp = s.split(",");
			String s1 = tmp[0].trim();
			String s2 = tmp[23].trim();
			return new Tuple2(s1, s2);
		});
		JavaPairRDD<String, String> result = kvpair
				.reduceByKey((s1,s2)->s1+"-"+s2);
		System.out.println("一共有" + result.lookup("1").get(0).split("-").length+ "个");
	}
}
