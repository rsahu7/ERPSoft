package com.hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="noninventoryitem")
public class NonInventoryItem {

	@Id
	@Column(name = "internalid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	private String code;
	
	@Column(name = "creationdate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date creationdate;
	
	private String description;
	private String groupname;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		if(creationdate == null ){
			this.creationdate = new Date();
		}else{
			this.creationdate = creationdate;
		}
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
