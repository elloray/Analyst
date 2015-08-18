package com.loc.analyst.recommand.offline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.loc.analyst.common.HospitalInfo;
import com.loc.analyst.util.Constant;

import scala.Tuple2;

public class RecommendCrontab {

	public static HashMap<String, ArrayList<HospitalInfo>> hosMap = new HashMap();

	public static void main(String[] args, JavaSparkContext sc) {
		JavaRDD<String> lines = sc.textFile(Constant.HOSINFO_PATH);
		JavaPairRDD<String, HospitalInfo> kvpair = lines.mapToPair(s -> {
			String[] tmp = s.split(",");
			String s0 = tmp[0].trim();
			String s1 = tmp[8].trim();
			int s2 = Integer.parseInt(tmp[23].trim());
			String s3 = tmp[24];
			int prof_num = 0;
			if (s3.contains("教授")) {
				prof_num = 1;
			}
			Random random = new Random();
			int paper_num = random.nextInt(90) + 10;
			int honor_num = random.nextInt(30);
			return new Tuple2<String, HospitalInfo>(s0, new HospitalInfo(s0,
					s1, 1, prof_num, paper_num, honor_num));
		});
		JavaPairRDD<String, HospitalInfo> result = kvpair
				.reduceByKey((s1, s2) -> {
					HospitalInfo hos = new HospitalInfo(s1.getName(), s1
							.getCity(),
							s1.getDoctor_num() + s2.getDoctor_num(), s1
									.getProf_num() + s2.getProf_num(), s1
									.getPaper_num() + s2.getPaper_num(), s1
									.getHonor_num() + s2.getHonor_num());
					return hos;
				});

		for (int i = 0; i < Constant.HOS_NUM; i++) {
			if (result.lookup(Integer.toString(i)).size() != 0) {
				if (hosMap.get(result.lookup(Integer.toString(i)).get(0)
						.getCity()) == null) {
					hosMap.put(result.lookup(Integer.toString(i)).get(0)
							.getCity(), new ArrayList<HospitalInfo>());
				} else {
					hosMap.get(
							result.lookup(Integer.toString(i)).get(0).getCity())
							.add(result.lookup(Integer.toString(i)).get(0));
				}
			}

		}
	}
}
