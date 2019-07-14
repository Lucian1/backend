package com.itutortime.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Announcements {
	private @GeneratedValue @javax.persistence.Id int id;
	private int senderId;
	private String senderType;
	private String subject;
	private String content;

	private Timestamp time;

	private int groupId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getGroupid() {
		return groupId;
	}

	public void setGroupid(int groupid) {
		this.groupId = groupid;
	}

	public int getSenderid() {
		return senderId;
	}

	public void setSenderid(int senderid) {
		this.senderId = senderid;
	}

	
	public String getSenderType() {
		return senderType;
	}

	public void setSenderType(String senderType) {
		this.senderType = senderType;
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

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	
}
