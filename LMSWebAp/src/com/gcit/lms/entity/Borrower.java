package com.gcit.lms.entity;

import java.util.List;
/**
 * 
 * @author 
 *
 */
public class Borrower {
	
	private Integer CardNo;
	private String name;
	private String address;
	private String phone;
	private List<BookLoans> bookLoans;
		
	/**
	 * @return the cardNo
	 */
	public Integer getCardNo() {
		return CardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Integer cardNo) {
		CardNo = cardNo;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @return the bookLoans
	 */
	public List<BookLoans> getBookLoans() {
		return bookLoans;
	}
	/**
	 * @param bookLoans the bookLoans to set
	 */
	public void setBookLoans(List<BookLoans> bookLoans) {
		this.bookLoans = bookLoans;
	}

}
