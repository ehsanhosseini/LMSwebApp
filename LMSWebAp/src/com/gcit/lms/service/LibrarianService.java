package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Branch;
/**
 * 
 * @author 
 *
 */
public class LibrarianService {
	
ConnectionUtil connUtil = new ConnectionUtil();
	
	public List<Branch> readAllBranch() throws SQLException{
		Connection conn = null;
		List<Branch> allBranch = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			BranchDAO branchDao = new BranchDAO(conn);
			allBranch = branchDao.readAllBranch();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return allBranch;
	}
	
	public void updateBookCopies(BookCopies bookCopies, Book book, Branch branch) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO bookCopiesDao = new BookCopiesDAO(conn);
			bookCopiesDao.updateBookCopies(bookCopies, book, branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}	
		
	}
	
	public void updateBranch(Branch branch) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO branchDao = new BranchDAO(conn);
			branchDao.updateBranch(branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}	
		
	}
	public List<Book> readAllBooksInBranch(Branch branch) throws SQLException{
		Connection conn = null;
		List<Book> allBook = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			BookDAO bookDao = new BookDAO(conn);
			allBook = bookDao.readAllBooksInBranch(branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return allBook;
	}
	
	public List<BookCopies> readBookCopies(Book book, Branch branch) throws SQLException{
		Connection conn = null;
		List<BookCopies> bookCopy = new ArrayList<>();
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO bookCopiesDao = new BookCopiesDAO(conn);
			bookCopy = bookCopiesDao.readBookCopies(book, branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return bookCopy;
	}

	
}
