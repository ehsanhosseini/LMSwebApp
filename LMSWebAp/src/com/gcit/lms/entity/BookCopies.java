package com.gcit.lms.entity;

import java.util.List;
/**
 * 
 * @author 
 *
 */
public class BookCopies {
	
	private Book book;
	private Branch branch;
	private Integer noCopies;
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	/**
	 * @return the noCopies
	 */
	public Integer getNoCopies() {
		return noCopies;
	}
	/**
	 * @param noCopies the noCopies to set
	 */
	public void setNoCopies(Integer noCopies) {
		this.noCopies = noCopies;
	}
	
	
}
