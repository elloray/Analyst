package com.loc.analyst.recommand.online;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.loc.analyst.common.HospitalInfo;
import com.loc.analyst.recommand.offline.DistanceCal;
import com.loc.analyst.recommand.offline.RecommendCrontab;

public class RecommendContainer {
	
	private static final HospitalInfo heart = new HospitalInfo("北京协和医院", "北京", 50,
			100, 40, 100);

	public static String recommend(String province) {
		HashMap<String, ArrayList<HospitalInfo>> dataset = RecommendCrontab.hosMap;
		StringBuffer sb = new StringBuffer();
		ArrayList<HospitalInfo> hoslist = dataset.get(province);
		HashMap<String, Double> reslist = new HashMap<String, Double>();

		for (HospitalInfo hos : hoslist) {
			DistanceCal dc = new DistanceCal();
			double sim = dc.cosSimilarity(heart, hos);
			reslist.put(hos.getName(), sim);
		}

		List<Map.Entry<String, Double>> list_Data = new ArrayList<Map.Entry<String, Double>>(
				reslist.entrySet());
		Collections.sort(list_Data,
				new Comparator<Map.Entry<String, Double>>() {
					public int compare(Map.Entry<String, Double> o1,
							Map.Entry<String, Double> o2) {
						if (o2.getValue() != null && o1.getValue() != null
								&& o2.getValue().compareTo(o1.getValue()) < 0) {
							return 1;
						} else {
							return -1;
						}

					}
				});

		Iterator iter = reslist.entrySet().iterator();
		int count = 0;
		while (iter.hasNext()) {
			if (count == 3)
				break;
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			sb.append(key).append("_");
			count++;
		}

		return sb.toString();
	}

}
