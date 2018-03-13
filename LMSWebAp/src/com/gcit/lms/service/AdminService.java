package com.gcit.lms.service;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Publisher;

public class AdminService {
	
	ConnectionUtil connUtil = new ConnectionUtil();
	
	public void saveAuthor(Author author) throws SQLException{
		Connection conn = null;
	
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			if(author.getAuthorId()!=null && author.getAuthorName()!=null){ 
				adao.updateAuthor(author);
			}else if (author.getAuthorId()!=null){
				adao.deleteAuthor(author);
			}else{
				adao.addAuthorWithID(author);
			}
			//add code to save into tbl_book_authors
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		
	}

	/**
	 * read all branch which have at least one copy
	 */
	
	
	public List<Author> getAuthors(String authorName, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			if(authorName!=null){
				return adao.readAuthorsByName(authorName);
			}else{
				return adao.readAllAuthors(pageNo);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	

	public void saveBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			Integer bookId = bdao.addBookGetPK(book);
			for(Author a: book.getAuthors()){
				adao.addAuthorWithID(a);
			}
			//same fo genre
//			for(Author a: book.getAuthors()){
//				adao.addBookAuthors(bookId,a.getAuthorId());
//			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
		
	
	//read author by PK
	public Author getAuthorsByPK(Integer authorId) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorByPk(authorId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	
	public Integer getAuthorsCount() throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getAuthorsCount(null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}

	public Book getBookByPK(Integer bookId) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBookByPK(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	
	
	
	public List<Book> getBooks(String bookTitle) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public void updateBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.updateBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}		
	}
	
	public void deleteBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}		
	}
	
	public void savePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO bdao = new PublisherDAO(conn);
			bdao.savePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
	
	public Integer savePublisherWithID(Publisher publisher) throws SQLException{
		Connection conn = null;
		int id = 0;
		try {
			conn = connUtil.getConnection();
			PublisherDAO bdao = new PublisherDAO(conn);
			id = bdao.savePublisherWithID(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return id;
	}
	
	public List<Publisher> readPublisherById(Integer id) throws SQLException{
		Connection conn = null;
		List<Publisher> publisherList = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO publisherDao = new PublisherDAO(conn);
			publisherList = publisherDao.readPublisherById(id);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return publisherList;
	}
	
	public List<Publisher> readAllPublishers() throws SQLException{
		Connection conn = null;
		List<Publisher> publisherList = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO publisherDao = new PublisherDAO(conn);
			publisherList = publisherDao.readAllPublishers();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return publisherList;
	}
	
	public void updatePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO bdao = new PublisherDAO(conn);
			bdao.updatePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
	
	public void deletePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO bdao = new PublisherDAO(conn);
			bdao.deletePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
	

	public void saveBranch(Branch branch) throws SQLException{
		Connection conn = null;
	
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			if(branch.getBranchId()!=null && branch.getBranchName()!=null){ 
				bdao.updateBranch(branch);
			}else if (branch.getBranchId()!=null){
				bdao.deleteBranch(branch);
			}else{
				bdao.addBranchWithID(branch);
			}
			//add code to save into tbl_book_authors
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		
	}
	
	
	

	public List<Branch> getBranches(String branchName) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			if(branchName!=null){
				return bdao.readBranchesByName(branchName);
			}else{
				return bdao.readAllBranch();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public Branch readBranchByPK(Integer branchId) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.readBranchByPk(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	
	public void updateBranch(Branch branch) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.updateBranch(branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
	
	public void deleteBranch(Branch branch) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.deleteBranch(branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
	
	public Integer getBranchesCount() throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.getBranchesCount(null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public void saveBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.saveBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
	
	public List<Borrower> readAllBorrowers() throws SQLException{
		List<Borrower> borrowerList = null;
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			borrowerList = bdao.readAllBorrowers();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return borrowerList;
	}
	
	public void updateBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.updateBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
	
	public void deleteBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.deleteBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
	
	public List<BookLoans> readAllBookLoans() throws SQLException{
		List<BookLoans> bookLoans = null;
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			bookLoans = bdao.readAllBookLoans();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return bookLoans;
	}
	
	public List<BookLoans> readAllBookLoansWithBookTitle() throws SQLException{
		List<BookLoans> bookLoans = null;
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			bookLoans = bdao.readAllBookLoansWithBookTitle();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		return bookLoans;
	}
	
	public void updateDueDateBookLoans(Date dueDate, Integer bookId, Integer branchId, Integer cardNo, Timestamp dateIn) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			bdao.updateDueDateBookLoans(dueDate, bookId, branchId, cardNo, dateIn);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
		
	}
}
