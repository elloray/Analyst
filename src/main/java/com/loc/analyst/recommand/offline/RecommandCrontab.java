package com.loc.analyst.recommand.offline;

import java.util.HashMap;
import java.util.Random;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.loc.analyst.util.Constant;

import scala.Tuple2;

public class RecommandCrontab {

	public static HashMap<String, HospitalInfo> hosMap = new HashMap<String, HospitalInfo>();

	public static void main(String[] args) {
		JavaSparkContext sc = new JavaSparkContext();
		JavaRDD<String> lines = sc.textFile("/Users/Ray/Desktop/demo.csv");
		JavaPairRDD<String, HospitalInfo> kvpair = lines
				.mapToPair(s -> {
					String[] tmp = s.split(",");
					String s0 = tmp[1].trim();
					String s1 = tmp[8].trim();
					String s2 = tmp[23].trim();
					String s3 = tmp[24].trim().split("/")[1];
					int prof_num = 0;
					if (s3.contains("教授")) {
						prof_num = 1;
					}
					Random random = new Random();
					String paper_num = Integer.toString(random.nextInt(90) + 10);
					String honor_num = Integer.toString(random.nextInt(30));
					return new Tuple2<String, HospitalInfo>(s1,
							new HospitalInfo(s0, s1, "1", Integer
									.toString(prof_num), paper_num, honor_num));
				});
		JavaPairRDD<String, HospitalInfo> result = kvpair
				.reduceByKey((s1, s2) -> {
					HospitalInfo hos = new HospitalInfo(s1.getName(), s1
							.getCity(), add(s1.getDoctor_num(),
							s2.getDoctor_num()), add(s1.getProf_num(),
							s2.getProf_num()), add(s1.getPaper_num(),
							s2.getPaper_num()), add(s1.getHonor_num(),
							s2.getHonor_num()));
					return hos;
				});
		for (int i = 0; i < Constant.PROV_NUM; i++) {
			if (result.lookup(Integer.toString(i)).size() != 0) {
				hosMap.put(Integer.toString(i),
						result.lookup(Integer.toString(i)).get(0));
			}

		}
	}

	public static String add(String s1, String s2) {
		int a1 = Integer.parseInt(s1);
		int a2 = Integer.parseInt(s2);
		return Integer.toString(a1 + a2);
	}
}
