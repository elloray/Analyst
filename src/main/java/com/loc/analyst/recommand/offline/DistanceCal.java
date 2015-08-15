package com.loc.analyst.recommand.offline;

import com.loc.analyst.common.Hospital;

public class DistanceCal {
	
	public double cosSimilarity(Hospital h1, Hospital h2) {
		double res = 0.0;
		int[] hos1 = {h1.getDocnum(), h1.getHonornum(), h1.getPapernum(), h1.getProfessornum()};
		int[] hos2 = {h2.getDocnum(), h2.getHonornum(), h2.getPapernum(), h2.getProfessornum()};
		int part1 = 0, part2 = 0, part3 = 0;
		for(int i = 0; i < 4; i++) {
			part1 += hos1[i] * hos2[i];
			part2 += hos1[i] * hos1[i];
			part3 += hos2[i] * hos2[i];
		}
		res = part1 / (Math.sqrt(part2) * Math.sqrt(part3));
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
