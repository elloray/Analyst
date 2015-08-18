package com.loc.analyst.common;

import scala.Serializable;


public class HospitalInfo implements Serializable{
/**
	 * 
	 */ 
	private static final long serialVersionUID = -5563947103560830697L;
	
private String name;
private String city;
private int doctor_num;
private int prof_num;
private int paper_num;
private int honor_num;

public HospitalInfo(String name,String city,int doctor_num,int prof_num, int paper_num,int honor_num){
	this.name = name;
	this.city = city;
	this.doctor_num = doctor_num;
	this.prof_num = prof_num;
	this.paper_num = paper_num;
	this.honor_num = honor_num;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getDoctor_num() {
	return doctor_num;
}
public void setDoctor_num(int doctor_num) {
	this.doctor_num = doctor_num;
}
public int getProf_num() {
	return prof_num;
}
public void setProf_num(int prof_num) {
	this.prof_num = prof_num;
}
public int getPaper_num() {
	return paper_num;
}
public void setPaper_num(int paper_num) {
	this.paper_num = paper_num;
}
public int getHonor_num() {
	return honor_num;
}
public void setHonor_num(int honor_num) {
	this.honor_num = honor_num;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}
}
