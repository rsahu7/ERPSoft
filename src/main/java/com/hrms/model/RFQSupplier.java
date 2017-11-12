package com.hrms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="rfqsuppliers", uniqueConstraints = {
		@UniqueConstraint(columnNames = "internalid")})
public class RFQSupplier {
	
 	@Id
	@Column(name = "internalid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rfqSupplierId;
    private String rate;
    private String deliveryTerms;
	private String supplierName;
    private int supplierId;
	private int rfqItemId;
    private int rfqId;
    
	public int getRfqSupplierId() {
		return rfqSupplierId;
	}
	public void setRfqSupplierId(int rfqSupplierId) {
		this.rfqSupplierId = rfqSupplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getRfqItemId() {
		return rfqItemId;
	}
	public void setRfqItemId(int rfqItemId) {
		this.rfqItemId = rfqItemId;
	}
	public int getRfqId() {
		return rfqId;
	}
	public void setRfqId(int rfqId) {
		this.rfqId = rfqId;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getDeliveryTerms() {
		return deliveryTerms;
	}
	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}    
	
    @Override
	public String toString() {
		return "RFQSupplier [rfqSupplierId=" + rfqSupplierId + ", rate=" + rate + ", deliveryTerms=" + deliveryTerms
				+ ", supplierName=" + supplierName + ", supplierId=" + supplierId + ", rfqItemId=" + rfqItemId
				+ ", rfqId=" + rfqId + "]";
	}
}
