package com.loc.analyst.model;

import java.util.HashMap;
import java.util.Map;

public abstract class Model {
	
	private HashMap<String, Object> ValueMap = new HashMap<String, Object>();
	
	public void set(String key, Object value){
		ValueMap.put(key, value);
	}

	public Object get(String key){
		return ValueMap.get(key);
	}
	
	//修正向量
	public abstract void adjust(Map<String, Double> weightMap);
	
	
}
