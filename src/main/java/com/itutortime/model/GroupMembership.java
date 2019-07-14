package com.itutortime.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class GroupMembership {
	private @GeneratedValue @javax.persistence.Id int Id;
	
	private int groupId;
	private int memberId;
	private String memberType;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
	
	
}
