package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Branch;

public class BookDAO extends BaseDAO<Book>{

	public BookDAO(Connection conn) {
		super(conn);
	}

	public void addBookAuthor(Book book, Author author) throws ClassNotFoundException, SQLException {
		List<Object> list = new ArrayList<>();
		list.add(book.getBookId());
		list.add(author.getAuthorId());
		saveList("insert into tbl_book_authors (bookId, authorId) values (?, ?)", list);
	}
	
	
	public void addBook(Book book) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_book (bookName) VALUES (?)", new Object[] {book.getTitle()});
	}

	public Integer addBookGetPK(Book book) throws SQLException, ClassNotFoundException {
		return saveWithID("INSERT INTO tbl_book (bookName) VALUES (?)", new Object[] {book.getTitle()});
	}
	public void saveBook(Book book) throws SQLException, ClassNotFoundException{
		save("insert into tbl_book (title) values (?)", new Object[]{book.getTitle()});
	}
	
	public Integer saveBookWithID(Book book) throws SQLException, ClassNotFoundException{
		return saveWithID("insert into tbl_book (title) values (?)", new Object[]{book.getTitle()});
	}
	
	// change from title to bookName
	public void updateBook(Book book) throws SQLException, ClassNotFoundException{
		save("update tbl_book set bookName = ? where bookId = ?", new Object[]{book.getTitle(), book.getBookId()});
	}
	
	public void deleteBook(Book book) throws SQLException, ClassNotFoundException{
		save("delete from tbl_book where bookId = ?", new Object[]{book.getBookId()});
	}
	
	public List<Book> readAllBooks() throws SQLException, ClassNotFoundException{
		return read("select * from tbl_book", null);
	}

	

	public List<Book> readAllBooksInBranch(Branch branch) throws SQLException, ClassNotFoundException{
		return read("select * from tbl_book left join tbl_book_copies on tbl_book.bookId = tbl_book_copies.bookId left join tbl_library_branch on tbl_library_branch.branchId = tbl_book_copies.branchId where tbl_library_branch.branchId = ?", new Object[]{branch.getBranchId()});
	}
	
	public List<Book> readAllBooksAvailable(Branch branch) throws SQLException, ClassNotFoundException{
		return read("select b.* from tbl_book_copies a join tbl_book b on a.bookId = b.bookId left join (select l.bookId, count(l.bookId) as rented from tbl_book_loans l where l.branchId = ? and l.dateIn= \"1900-01-01 00:00\" group by l.bookId) c on a.bookId = c.bookId where a.noOfCopies > 0 and a.branchId = ? and a.noOfCopies > ifnull(rented,0)", new Object[]{branch.getBranchId(), branch.getBranchId()});
	}
	
	public List<Book> readBooksInBranchInBorrowerCheckedout(Branch branch, Integer cardNo) throws SQLException, ClassNotFoundException{
		return read("select * from tbl_book left join tbl_book_loans on tbl_book.bookId = tbl_book_loans.bookId left join tbl_library_branch on tbl_library_branch.branchId = tbl_book_loans.branchId where tbl_library_branch.branchId = ? and tbl_book_loans.cardNo = ? and tbl_book_loans.dateIn= \"1900-01-01 00:00\"", new Object[]{branch.getBranchId(), cardNo});
	}
	
	public Book readBookByPK(Integer bookId) throws ClassNotFoundException, SQLException{
		List<Book> books =  read("SELECT * FROM tbl_book WHERE bookId = ?", new Object[]{bookId});
		if(books!=null){
			return books.get(0);
		}
		return null;
	}

	
	
	@Override
	public List<Book> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Book> books = new ArrayList<>();
		
		AuthorDAO adao = new AuthorDAO(conn);
		BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
		
			while(rs.next()){
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				b.setAuthors(adao.readFirstLevel("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[]{b.getBookId()}));
				books.add(b);
			}		
		return books;
	}
	
	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Book> books = new ArrayList<>();
		
			while(rs.next()){
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				books.add(b);
			}
		
		return books;
	}
}
