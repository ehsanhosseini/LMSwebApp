package com.gcit.lms.entity;

import java.util.List;

public class Branch {
	

	private Integer branchId;
	private String branchName;
	private String address;
	private List<BookLoans> bookLoans;
	private List<BookCopies> bookCopies;
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<BookLoans> getBookLoans() {
		return bookLoans;
	}
	public void setBookLoans(List<BookLoans> bookLoans) {
		this.bookLoans = bookLoans;
	}
	public List<BookCopies> getBookCopies() {
		return bookCopies;
	}
	public void setBookCopies(List<BookCopies> bookCopies) {
		this.bookCopies = bookCopies;
	}

	
	
}