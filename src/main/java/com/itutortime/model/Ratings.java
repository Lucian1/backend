package com.itutortime.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Ratings {

	private @GeneratedValue @javax.persistence.Id int Id;

	private Long rateeid;
	private String rateetype;
	private Long raterid;
	private Long score;
	private String review;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Long getRateeid() {
		return rateeid;
	}

	public void setRateeid(Long rateeid) {
		this.rateeid = rateeid;
	}

	public String getRateetype() {
		return rateetype;
	}

	public void setRateetype(String rateetype) {
		this.rateetype = rateetype;
	}

	public Long getRaterid() {
		return raterid;
	}

	public void setRaterid(Long raterid) {
		this.raterid = raterid;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}
