package com.hrms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="rfq")
public class RFQ{

	@Id
	@Column(name = "internalid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private int rfqno;
	
	@Column(name = "rfqdate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat (pattern="dd/MM/yyyy")
	private Date rfqdate;
	
	@Column(name = "deliveryduedate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat (pattern="dd/MM/yyyy")
	private Date deliveryduedate;
	
	@Column(name = "creationdate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat (pattern="dd/MM/yyyy")
	private Date creationdate;
	
	private int refno;
	private String warehouse;
	private String project;
	private String reqdepartment;
	private String reqby;
	private String address;
	private String remarks;
	private String status;
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, targetEntity = RFQItem.class)
    @JoinColumn(name="rfqid")
	@JsonManagedReference
	private List<RFQItem> items;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getRfqno() {
		return rfqno;
	}

	public void setRfqno(int rfqno) {
		this.rfqno = rfqno;
	}

	public Date getRfqdate() {
		return rfqdate;
	}

	public void setRfqdate(Date rfqdate) {
		this.rfqdate = rfqdate;
	}

	public Date getDeliveryduedate() {
		return deliveryduedate;
	}

	public void setDeliveryduedate(Date deliveryduedate) {
		this.deliveryduedate = deliveryduedate;
	}

	public int getRefno() {
		return refno;
	}

	public void setRefno(int refno) {
		this.refno = refno;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getReqdepartment() {
		return reqdepartment;
	}

	public void setReqdepartment(String reqdepartment) {
		this.reqdepartment = reqdepartment;
	}

	public String getReqby() {
		return reqby;
	}

	public void setReqby(String reqby) {
		this.reqby = reqby;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public List<RFQItem> getItems() {
		return items;
	}

	public void setItems(List<RFQItem> items) {
		this.items = items;
	}

	public void setCreationdate(Date creationdate) {
		if(creationdate == null ){
			this.creationdate = new Date();
		}else{
			this.creationdate = creationdate;
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationdate() {
		return creationdate;
	}

}
