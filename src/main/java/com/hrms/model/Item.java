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
@Table(name="item")
public class Item {

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
	private int customerid;
	private double avgcost;
	private double costprice;
	private double retailprice;
	private double wholesaleprice;
	private String uom;
	private String primaryunit;
	private String currentstock;
	private String reservedstock;
	private String minimumstock;
	private String reorderlevel;
	private String packingsize;
	private double volume;
	private String barcode;
	private String customerbarcode;
	private String brandname;
	private double wholesalemarkup;
	private double retailmarkup;
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
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public double getAvgcost() {
		return avgcost;
	}
	public void setAvgcost(double avgcost) {
		this.avgcost = avgcost;
	}
	public double getCostprice() {
		return costprice;
	}
	public void setCostprice(double costprice) {
		this.costprice = costprice;
	}
	public double getRetailprice() {
		return retailprice;
	}
	public void setRetailprice(double retailprice) {
		this.retailprice = retailprice;
	}
	public double getWholesaleprice() {
		return wholesaleprice;
	}
	public void setWholesaleprice(double wholesaleprice) {
		this.wholesaleprice = wholesaleprice;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getPrimaryunit() {
		return primaryunit;
	}
	public void setPrimaryunit(String primaryunit) {
		this.primaryunit = primaryunit;
	}
	public String getCurrentstock() {
		return currentstock;
	}
	public void setCurrentstock(String currentstock) {
		this.currentstock = currentstock;
	}
	public String getReservedstock() {
		return reservedstock;
	}
	public void setReservedstock(String reservedstock) {
		this.reservedstock = reservedstock;
	}
	public String getMinimumstock() {
		return minimumstock;
	}
	public void setMinimumstock(String minimumstock) {
		this.minimumstock = minimumstock;
	}
	public String getReorderlevel() {
		return reorderlevel;
	}
	public void setReorderlevel(String reorderlevel) {
		this.reorderlevel = reorderlevel;
	}
	public String getPackingsize() {
		return packingsize;
	}
	public void setPackingsize(String packingsize) {
		this.packingsize = packingsize;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getCustomerbarcode() {
		return customerbarcode;
	}
	public void setCustomerbarcode(String customerbarcode) {
		this.customerbarcode = customerbarcode;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public double getWholesalemarkup() {
		return wholesalemarkup;
	}
	public void setWholesalemarkup(double wholesalemarkup) {
		this.wholesalemarkup = wholesalemarkup;
	}
	public double getRetailmarkup() {
		return retailmarkup;
	}
	public void setRetailmarkup(double retailmarkup) {
		this.retailmarkup = retailmarkup;
	}
}
