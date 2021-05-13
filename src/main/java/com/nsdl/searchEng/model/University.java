package com.nsdl.searchEng.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*University model class 
 * to hold the data*/
@Table
@Entity
public class University {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Long universityCode;
	private String universityName;
	public Long getUniversityCode() {
		return universityCode;
	}
	public void setUniversityCode(Long universityCode) {
		this.universityCode = universityCode;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public University(Long id2, String universityName) {
		super();
		this.universityCode = id2;
		this.universityName = universityName;
	}
	@Override
	public String toString() {
		return "University [id=" + id + ", universityCode=" + universityCode + ", universityName=" + universityName
				+ "]";
	}
	public University() {
		super();
	}


	
}
