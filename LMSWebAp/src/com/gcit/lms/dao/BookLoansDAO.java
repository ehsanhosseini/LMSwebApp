package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
/**
 * 
 * @author 
 *
 */
public class BookLoansDAO extends BaseDAO<BookLoans> {

	public BookLoansDAO(Connection conn) {
		super(conn);
	}
	
	public void saveBookLoans(Book book, Branch branch, Integer cardNo, String dateOut, String dueDate) throws SQLException, ClassNotFoundException {
		save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values (?, ?, ?, ?, ?)", 
				new Object[] { book.getBookId(), branch.getBranchId(), cardNo, dateOut, dueDate});
	}
	
	public Integer addBookLoansGetPK(BookLoans bookLoans) throws SQLException, ClassNotFoundException {
		return saveWithID("INSERT INTO tbl_book_loans (bookId) VALUES (?)", new Object[] {bookLoans.getBranch()});
	}

	public void updateDateInBookLoans(String dateIn, Book book, Branch branch, Integer cardNo) throws SQLException, ClassNotFoundException {
		save("update tbl_book_loans set dateIn = ? where bookId = ? AND branchId = ? AND cardNo = ? AND dateIn = \"1900-01-01 00:00:00\"",
				new Object[] {dateIn, book.getBookId(), branch.getBranchId(), cardNo });
	}

	public void deleteBookLoans(BookLoans bookLoans) throws SQLException, ClassNotFoundException {
		save("delete from tbl_book_loans where bookId = ? AND branchId = ? AND cardNo = ?", new Object[] { bookLoans.getBook().getBookId(), bookLoans.getBranch().getBranchId(), bookLoans.getBorrower().getCardNo() });
	}

	public List<BookLoans> readAllBookLoans() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_loans", null);
	}
	
	public List<BookLoans> readAllBookLoansWithBookTitle() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_loans left join tbl_book on tbl_book_loans.bookId = tbl_book.bookId", null);
	}
	
	public void updateDueDateBookLoans(Date dueDate, Integer bookId, Integer branchId, Integer cardNo, Timestamp dateIn) throws SQLException, ClassNotFoundException{
		save("update tbl_book_loans set dueDate = ? where bookId = ? AND branchId = ? AND cardNo = ? AND dateIn = ?", new Object[] { dueDate, bookId, branchId, cardNo, dateIn});
	}
	
	public List<BookLoans> readBookLoanByIds(Integer bookId, Integer branchId, Integer cardNo) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_loans where bookId = ? AND branchId = ? AND cardNo = ? AND dateIn =  \"1900-01-01 00:00:00\"", new Object[]{bookId, branchId, cardNo});
	}
	
	@Override
	public List<BookLoans> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		BookDAO bdao = new BookDAO(conn);
		BranchDAO bbdao = new BranchDAO(conn);
		BorrowerDAO bwdao = new BorrowerDAO(conn);
		List<BookLoans> bookLoanList = new ArrayList<>();
		
		while (rs.next()) {
			BookLoans bookLoans = new BookLoans();
//			bookLoans.setBook(((BookLoans) rs).getBook());
//			bookLoans.setBranch(((BookLoans) rs).getBranch());
//			bookLoans.setBorrower(((BookLoans) rs).getBorrower());
			
//			bookLoans.setDateOut(rs.getDate("dateOut"));			
//			bookLoans.setDueDate(rs.getDate("dueDate"));
//			bookLoans.setDateIn(rs.getDate("dateIn"));
			
			bookLoans.setDateOut(rs.getTimestamp("dateOut"));			
			bookLoans.setDueDate(rs.getTimestamp("dueDate"));
			bookLoans.setDateIn(rs.getTimestamp("dateIn"));
			
			List<Book> bookList = bdao.readFirstLevel(
					"select * from tbl_book where bookId  = ?",
					new Object[] { rs.getInt("bookId") });
			
			List<Branch> branchList = bbdao.readFirstLevel(
					"select * from tbl_library_branch where branchId  = ?",
					new Object[] { rs.getInt("branchId") });
			List<Borrower> borrowerList = bwdao.readFirstLevel(
					"select * from tbl_borrower where cardNo  = ?",
					new Object[] { rs.getInt("cardNo") });
			
			bookLoans.setBook(bookList.get(0));
			bookLoans.setBranch(branchList.get(0));
			bookLoans.setBorrower(borrowerList.get(0));
			bookLoanList.add(bookLoans);
		}
		return bookLoanList;
		
	}
//
	@Override
	public List<BookLoans> extractDataFirstLevel(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookLoans> bookLoanList = new ArrayList<>();
		while (rs.next()) {
			BookLoans bookLoans = new BookLoans();
//			bookLoans.setBook(((BookLoans) rs).getBook());
//			bookLoans.setBranch(((BookLoans) rs).getBranch());
//			bookLoans.setBorrower(((BookLoans) rs).getBorrower());
			bookLoans.setDateOut(rs.getTimestamp("dateOut"));			
			bookLoans.setDueDate(rs.getTimestamp("dueDate"));
			bookLoans.setDateIn(rs.getTimestamp("dateIn"));
			bookLoanList.add(bookLoans);
		}
		return bookLoanList;

	}
}
