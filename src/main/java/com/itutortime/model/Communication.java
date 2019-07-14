package com.itutortime.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Communication {
	private @GeneratedValue @javax.persistence.Id int id;
	private int fromid;
	private int toid;

	private String fromtype;
	private String totype;

	private String subject;
	private String content;

	private Timestamp time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromId() {
		return fromid;
	}

	public void setFromId(int fromId) {
		this.fromid = fromId;
	}

	public int getToId() {
		return toid;
	}

	public void setToId(int toId) {
		this.toid = toId;
	}

	public String getFromType() {
		return fromtype;
	}

	public void setFromType(String fromType) {
		this.fromtype = fromType;
	}

	public int getFromid() {
		return fromid;
	}

	public void setFromid(int fromid) {
		this.fromid = fromid;
	}

	public int getToid() {
		return toid;
	}

	public void setToid(int toid) {
		this.toid = toid;
	}

	public String getFromtype() {
		return fromtype;
	}

	public void setFromtype(String fromtype) {
		this.fromtype = fromtype;
	}

	public String getTotype() {
		return totype;
	}

	public void setTotype(String totype) {
		this.totype = totype;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	

}
