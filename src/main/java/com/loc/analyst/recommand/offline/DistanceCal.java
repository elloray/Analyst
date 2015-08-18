package com.loc.analyst.recommand.offline;

import com.loc.analyst.common.HospitalInfo;

public class DistanceCal {

	public double cosSimilarity(HospitalInfo h1, HospitalInfo h2) {
		double res = 0.0;
		int[] hos1 = { h1.getDoctor_num(), h1.getHonor_num(),
				h1.getPaper_num(), h1.getProf_num() };
		int[] hos2 = { h2.getDoctor_num(), h2.getHonor_num(),
				h2.getPaper_num(), h2.getProf_num() };
		int part1 = 0, part2 = 0, part3 = 0;
		for (int i = 0; i < 4; i++) {
			part1 += hos1[i] * hos2[i];
			part2 += hos1[i] * hos1[i];
			part3 += hos2[i] * hos2[i];
		}
		res = part1 / (Math.sqrt(part2) * Math.sqrt(part3));
		return res;
	}

}
