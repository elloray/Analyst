package com.loc.analyst.util;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.netlib.util.intW;

public class Constant {
	//服务端口号
	public final static int SERVER_PORT = 9001; 
	//离线心电图数据数据文件位置
	public final static String HEARTMAP_PATH = "/Users/Ray/Desktop/svminput.txt";
	//离线任务定时周期
	public final static TimeUnit OFFLINE_PERIOD = TimeUnit.DAYS;
	//疾病-科室对应表
	public final static HashMap<String, String> DISEASE_MAPPING = new HashMap<String, String>();
}
