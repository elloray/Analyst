package com.loc.analyst.recommand.offline;

import scala.Serializable;


public class HospitalInfo implements Serializable{
/**
	 * 
	 */ 
	private static final long serialVersionUID = -5563947103560830697L;
	
private String name;
private String city;
private String doctor_num;
private String prof_num;
private String paper_num = "";
private String honor_num = "";

public HospitalInfo(String name,String city,String doctor_num,String prof_num, String paper_num,String honor_num){
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
public String getDoctor_num() {
	return doctor_num;
}
public void setDoctor_num(String doctor_num) {
	this.doctor_num = doctor_num;
}
public String getProf_num() {
	return prof_num;
}
public void setProf_num(String prof_num) {
	this.prof_num = prof_num;
}
public String getPaper_num() {
	return paper_num;
}
public void setPaper_num(String paper_num) {
	this.paper_num = paper_num;
}
public String getHonor_num() {
	return honor_num;
}
public void setHonor_num(String honor_num) {
	this.honor_num = honor_num;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}
}
