package com.hrms.model;

import java.util.Date;
import java.util.List;

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
@Table(name="rfqitems")
public class RFQItem {
	
 	@Id
	@Column(name = "internalid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rfqItemId;
 	
    private String description;
    private String quantity;
    private String unit;
    private String email;    
    
	@Column(name = "creationdate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat (pattern="dd/MM/yyyy")
	private Date creationdate;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, targetEntity = RFQSupplier.class)
    @JoinColumn(name="rfqitemid")
	@JsonManagedReference
	private List<RFQSupplier> rfqSuppliers;
    

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRfqItemId() {
		return rfqItemId;
	}

	public void setRfqItemId(int rfqItemId) {
		this.rfqItemId = rfqItemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
	public void setCreationdate(Date creationdate) {
		if(creationdate == null ){
			this.creationdate = new Date();
		}else{
			this.creationdate = creationdate;
		}
	}

	public List<RFQSupplier> getRfqSuppliers() {
		return rfqSuppliers;
	}

	public void setRfqSuppliers(List<RFQSupplier> rfqSuppliers) {
		this.rfqSuppliers = rfqSuppliers;
	}


}
