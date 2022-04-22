package com.tracker.models;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class DateFilter {
	
	@NotNull(message="Date is required! Format is MM/DD/YYYY")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date d1;
	
	@NotNull(message="Date is required! Format is MM/DD/YYYY")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date d2;
	
	private Long id;
	
	public DateFilter() {
		
	}

	public Date getD1() {
		return d1;
	}

	public void setD1(Date d1) {
		this.d1 = d1;
	}

	public Date getD2() {
		return d2;
	}

	public void setD2(Date d2) {
		this.d2 = d2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
