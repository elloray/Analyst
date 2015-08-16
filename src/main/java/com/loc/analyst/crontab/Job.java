package com.loc.analyst.crontab;


import com.loc.analyst.predict.offline.DisPre;
import com.loc.analyst.recommand.offline.RecommandCrontab;

public class Job extends Thread{
	public static String[] args = {};
	@Override
	public void run(){
//		DisPre.main(args);
//		RecommandCrontab.main(args);
	}
	public Job(String[] args){
		Job.args = args;
	}
}
