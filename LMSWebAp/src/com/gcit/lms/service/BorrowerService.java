package com.gcit.lms.service;

import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
/**
 * 
 * @author 
 *
 */
public class BorrowerService {
	
    ConnectionUtil connUtil = new ConnectionUtil();
	
	public List<Borrower> readBorrowerByCardNo(Integer cardNo) throws SQLException{
		Connection conn = null;
		List<Borrower> borrowerByCardNo = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			borrowerByCardNo = bdao.readBorrowerByCardNo(cardNo);

			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return borrowerByCardNo;
	}
/**
 * read all branch which have at least one copy
 */
	public List<Branch> readAllBranch() throws SQLException{
		Connection conn = null;
		List<Branch> branchList = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO branchDao = new BranchDAO(conn);
			branchList = branchDao.readAllBranch();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return branchList;
	}
	
	public List<Book> readAllBooksAvailable(Branch branch) throws SQLException{
		Connection conn = null;
		List<Book> bookList = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bookDao = new BookDAO(conn);
			bookList = bookDao.readAllBooksAvailable(branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return bookList;
	}
	
	public void saveBookLoans(Book book, Branch branch, Integer cardNo, String dateOut, String dueDate) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
			bookLoansDao.saveBookLoans(book, branch, cardNo, dateOut, dueDate);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
	
	public List<Branch> readBranchHaveLoanedBook(Integer cardNo) throws SQLException{
		Connection conn = null;
		List<Branch> branchList = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO branchDao = new BranchDAO(conn);
			branchList = branchDao.readBranchHaveLoanedBook(cardNo);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return branchList;
	}
	
	public List<Book> readBooksInBranchInBorrowerCheckedout(Branch branch, Integer cardNo) throws SQLException{
		Connection conn = null;
		List<Book> bookList = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bookDao = new BookDAO(conn);
			bookList = bookDao.readBooksInBranchInBorrowerCheckedout(branch, cardNo);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return bookList;
	}
	
	public void updateDateInBookLoans(String dateIn, Book book, Branch branch, Integer cardNo) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
			bookLoansDao.updateDateInBookLoans(dateIn, book, branch, cardNo);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
	public List<BookLoans> readBookLoanByIds(Integer bookId, Integer branchId, Integer cardNo) throws SQLException{
		Connection conn = null;
		List<BookLoans> bookLoansList = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bookloansDao = new BookLoansDAO(conn);
			bookLoansList = bookloansDao.readBookLoanByIds(bookId, branchId, cardNo);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return bookLoansList;
	}
	
}
