package com.loc.analyst.model;

public class ModelFactory{
	
	private static ModelFactory factory = new ModelFactory();

	// 单例
	private ModelFactory() {
	}

	public static ModelFactory getInstance() {
		return factory;
	}
	
	public Model getHeartDiseaseModel() {
		return new HeartDiseaseModel();
	}
	
}
