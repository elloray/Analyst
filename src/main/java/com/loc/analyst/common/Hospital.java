package com.loc.analyst.common;

public class Hospital {
	
	public String hosname;
	public String city;
	public String dep;
	public int docnum;
	public int professornum;
	public int papernum;
	public int honornum;
	
	public int getDocnum() {
		return docnum;
	}

	public void setDocnum(int docnum) {
		this.docnum = docnum;
	}
	public String getHosname() {
		return hosname;
	}

	public void setHosname(String hosname) {
		this.hosname = hosname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public int getProfessornum() {
		return professornum;
	}

	public void setProfessornum(int professornum) {
		this.professornum = professornum;
	}

	public int getPapernum() {
		return papernum;
	}

	public void setPapernum(int papernum) {
		this.papernum = papernum;
	}

	public int getHonornum() {
		return honornum;
	}

	public void setHonornum(int honornum) {
		this.honornum = honornum;
	}

	public Hospital(String hosname, String city, String dep, int pro, int paper, int honor, int docnum) {
		this.hosname = hosname;
		this.city = city;
		this.dep = dep;
		this.professornum = pro;
		this.papernum = paper;
		this.honornum = honor;
		this.docnum = docnum;
	}
}
