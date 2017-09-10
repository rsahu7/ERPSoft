package com.hrms.model;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 *
 * @author Ricky
 *
 */
@Entity
@Table(name = "employee", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "EMAIL") })
public class Employee {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Transient
	private int age;

	@Column(name = "creationdate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private Date creationdate ;

	@Column(name = "birthdate", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdate;

	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="EMPLOYEE_ID")
	@JsonManagedReference
    private Set<Interview> interview = new HashSet<Interview>(0) ;

	private String lastname;

	private String middlename;

	private String gender;

	private String maritialstatus;

	private String spousename;

	private String contact1;

	private String contact2;

	private String email;

	private String address;

	private String city;

	private String state;

	private String education;

	private double exprience;

	private String designation;

	private String department;

	private String industry;

	private double salary;

	private String prefworkinterest;

	private String prefworkloc;

	private double expectedsal;

	private String worknature;

	private String areaofwork;

	private String reference;

	private String resume;

	private String resumedate;

	private String docdetails;

	private String photo;

/*	private String interview1;

	private String interview2;

	private String interview3;*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		if(getBirthdate()!=null){
			long timeBetween = new Date().getTime() - getBirthdate().getTime();
			double yearsBetween = timeBetween / 3.156e+10;
			/*System.out.println("Calculated age for "+getName()+" will be : "+(int) Math.floor(yearsBetween));*/
			return (int) Math.floor(yearsBetween);
		}else{
			return age;
		}
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		/*this.birthdate = new Date();*/
		this.birthdate = birthdate;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritialstatus() {
		return maritialstatus;
	}

	public void setMaritialstatus(String maritialstatus) {
		this.maritialstatus = maritialstatus;
	}

	public String getSpousename() {
		return spousename;
	}

	public void setSpousename(String spousename) {
		this.spousename = spousename;
	}

	public String getContact1() {
		return contact1;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public String getContact2() {
		return contact2;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public double getExprience() {
		return exprience;
	}

	public void setExprience(double exprience) {
		this.exprience = exprience;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double string) {
		this.salary = string;
	}

	public String getPrefworkloc() {
		return prefworkloc;
	}

	public void setPrefworkloc(String prefworkloc) {
		this.prefworkloc = prefworkloc;
	}

	public double getExpectedsal() {
		return expectedsal;
	}

	public void setExpectedsal(double expectedsal) {
		this.expectedsal = expectedsal;
	}

	public String getWorknature() {
		return worknature;
	}

	public void setWorknature(String worknature) {
		this.worknature = worknature;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAreaofwork() {
		return areaofwork;
	}

	public void setAreaofwork(String areaofwork) {
		this.areaofwork = areaofwork;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getResumedate() {
		return resumedate;
	}

	public void setResumedate(String resumedate) {
		this.resumedate = resumedate;
	}

	public String getDocdetails() {
		return docdetails;
	}

	public void setDocdetails(String docdetails) {
		this.docdetails = docdetails;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/*public String getInterview1() {
		return interview1;
	}

	public void setInterview1(String interview1) {
		this.interview1 = interview1;
	}

	public String getInterview2() {
		return interview2;
	}

	public void setInterview2(String interview2) {
		this.interview2 = interview2;
	}

	public String getInterview3() {
		return interview3;
	}

	public void setInterview3(String interview3) {
		this.interview3 = interview3;
	}*/

    public Set<Interview> getInterview() {
        return interview;
    }

    public void setInterview(Set<Interview> interview) {
        this.interview = interview;
    }

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", age=" + age +", Interview=" + interview;
	}

	public String getPrefworkinterest() {
		return prefworkinterest;
	}

	public void setPrefworkinterest(String prefworkinterest) {
		this.prefworkinterest = prefworkinterest;
	}
}
