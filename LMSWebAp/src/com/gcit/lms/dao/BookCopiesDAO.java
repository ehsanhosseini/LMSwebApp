package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Branch;

public class BookCopiesDAO extends BaseDAO<BookCopies>{

	public BookCopiesDAO(Connection conn) {
		super(conn);
	}

	public void saveBookCopies(BookCopies bookCopies) throws SQLException, ClassNotFoundException{
		save("insert into tbl_book_copies (NoOfCopies) values (?)", new Object[]{ bookCopies.getNoCopies() });
	}
	
	public Integer addBookCopiesGetPK(BookCopies bookCopies) throws SQLException, ClassNotFoundException {
		return saveWithID("INSERT INTO tbl_book_copies (noOfCopies) VALUES (?)", new Object[] {bookCopies.getNoCopies()});
	}
	
	public Integer saveBookCopiesWithID(BookCopies bookCopies) throws SQLException, ClassNotFoundException{
		return saveWithID("insert into tbl_book_copies (NoOfCopies) values (?)", new Object[]{ bookCopies.getNoCopies() });
	}
	
	public void updateBookCopies(BookCopies bookCopies, Book book, Branch branch) throws SQLException, ClassNotFoundException{
		save("update tbl_book_copies set NoOfCopies = ? where bookId = ? AND branchId = ?", new Object[]{ bookCopies.getNoCopies(), book.getBookId(), branch.getBranchId() });
	}
	
	public void deleteBookCopies(BookCopies bookCopies) throws SQLException, ClassNotFoundException{
		save("delete from tbl_book_copies where bookId = ? AND branchId = ?", new Object[]{ bookCopies.getBook().getBookId(), bookCopies.getBranch().getBranchId() });
	}
	
	public List<BookCopies> readAllBookCopies() throws SQLException, ClassNotFoundException{
		return read("select * from tbl_book_copies", null);
	}
	
	public List<BookCopies> readBookCopies(Book book, Branch branch) throws SQLException, ClassNotFoundException{
		return read("select * from tbl_book_copies where bookId = ? AND branchId = ?", new Object[]{ book.getBookId(), branch.getBranchId() });
	}
	

	@Override
	public List<BookCopies> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookCopies> listBookCopies = new ArrayList<>();
//		AuthorDAO adao = new AuthorDAO(conn);
		 
			while(rs.next()){
				BookCopies bookCopies = new BookCopies();
				bookCopies.setNoCopies(rs.getInt("NoOfCopies"));
				
				//bookCopies.setAuthors(adao.readFirstLevel("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?))", new Object[]{bookCopies.getBookId()}));
				//pdao.getPublosher
				//gdao.read(get all genres)
				//br, copies
				listBookCopies.add(bookCopies);
			}		
		return listBookCopies;
	}
	
	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookCopies> listBookCopies = new ArrayList<>();
		
			while(rs.next()){
				BookCopies bookCopies = new BookCopies();
				bookCopies.setNoCopies(rs.getInt("NoOfCopies"));
				listBookCopies.add(bookCopies);
			}
		
		return listBookCopies;
	}
}

