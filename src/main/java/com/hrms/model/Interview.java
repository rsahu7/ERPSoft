/**
 *
 */
package com.hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * @author Ricky
 *
 */
@Entity
@Table(name = "interview", uniqueConstraints = {
@UniqueConstraint(columnNames = "ID")})
public class Interview {

	 	@Id
		@Column(name = "id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

	    @ManyToOne
	    @JsonBackReference
	    private Employee employee;

	    @Override
		public String toString() {
			return "Interview [id=" + id + ", companyName=" + companyName + ", department="
					+ department + ", position=" + position + ", interviewDate=" + interviewDate + ", status=" + status
					+ ", remark=" + remark +", interviewID=" + interviewId + "]";
		}

		@Column(name = "company_name")
	    private String companyName  ;

		private String department  ;

		private String position  ;

		@Column(name = "inter_date", columnDefinition = "DATETIME")
		@Temporal(TemporalType.TIMESTAMP)
		private Date interviewDate  ;

		private String status  ;

		private String remark  ;

		@Transient
		private Integer empId  ;

		@Transient
		private Integer interviewId  ;

	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	    public Employee getEmployee() {
	        return employee;
	    }

	    public void setEmployee(Employee employee) {
	        this.employee = employee;
	    }

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public Date getInterviewDate() {
			return interviewDate;
		}

		public void setInterviewDate(Date interviewDate) {
			this.interviewDate = interviewDate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public Integer getEmpId() {
			return empId;
		}

		public void setEmpId(int empId) {
			this.empId = empId;
		}

		public Integer getInterviewId() {
			return interviewId;
		}

		public void setInterviewId(int interviewId) {
			this.interviewId = interviewId;
		}

}









