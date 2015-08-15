package com.loc.analyst.model;

public interface Model<T> {
	
	public void set(String key, T value);

	public T get(String key);
}
