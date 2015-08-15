package com.loc.analyst.model;

public class ModelFactory {
	
	private static ModelFactory factory = new ModelFactory();

	// 单例模式
	private ModelFactory() {
	}

	public static ModelFactory getInstance() {
		return factory;
	}
	

}
