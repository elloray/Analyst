package com.loc.analyst.recommand.offline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.hdfs.server.namenode.HostFileManager.EntrySet;

import com.loc.analyst.common.Hospital;

public class HosRecom {
	
	private static final HospitalInfo heart = new HospitalInfo("北京协和医院", "北京", "50",
			"100", "40", "100");

	public static String Recom(String province) {
		HashMap<String, ArrayList<HospitalInfo>> dataset = RecommandCrontab.hosMap;
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
