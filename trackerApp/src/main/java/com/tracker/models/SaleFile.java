package com.tracker.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="saleFile")
public class SaleFile {
	
	// new to agency section
	// which agent in office sold policy
	// need to get total for date filter
	// change true/false to yes/no
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Name is required!")
    @Size(min=3, max=100, message="Name must be between 3 and 100 characters")
    private String name;
    
    @NotEmpty(message="Type is required!")
    private String type;
    
    @NotEmpty(message="Company is required!")
    private String company;
    
    @NotNull(message="Premium is required!")
    private int premium;
    
    @NotNull(message="Date is required! Format is MM/DD/YYYY")
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date saleDate;
    
    @NotNull(message="Sale Count is required!")
    @Min(value=1, message="Must be at least 1!")
    private int saleCount;
    
    private boolean driverLicense;
    
    private boolean signedApp;
    
    private boolean lifeQuote;
    
    private boolean scanned;
    
    private String comments;
    
    private boolean reviewComplete = false;
    
    @Column(updatable=false)
    private Date createdAt;
    
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    private boolean newToAgency;
    
    private String officeAgent;
    
    public SaleFile() {
        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPremium() {
		return premium;
	}

	public void setPremium(int premium) {
		this.premium = premium;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public boolean isDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(boolean driverLicense) {
		this.driverLicense = driverLicense;
	}

	public boolean isSignedApp() {
		return signedApp;
	}

	public void setSignedApp(boolean signedApp) {
		this.signedApp = signedApp;
	}

	public boolean isLifeQuote() {
		return lifeQuote;
	}

	public void setLifeQuote(boolean lifeQuote) {
		this.lifeQuote = lifeQuote;
	}

	public boolean isScanned() {
		return scanned;
	}

	public void setScanned(boolean scanned) {
		this.scanned = scanned;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isReviewComplete() {
		return reviewComplete;
	}

	public void setReviewComplete(boolean reviewComplete) {
		this.reviewComplete = reviewComplete;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public boolean isNewToAgency() {
		return newToAgency;
	}

	public void setNewToAgency(boolean newToAgency) {
		this.newToAgency = newToAgency;
	}

	public String getOfficeAgent() {
		return officeAgent;
	}

	public void setOfficeAgent(String officeAgent) {
		this.officeAgent = officeAgent;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
}
