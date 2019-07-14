package com.itutortime.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class ParentChild {
	private @GeneratedValue @javax.persistence.Id int id;
	private int parentid;
	private String name;
	private String year;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
