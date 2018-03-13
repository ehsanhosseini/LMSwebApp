package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
/**
 * 
 * @author 
 *
 */
public class BorrowerDAO extends BaseDAO<Borrower> {

	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	public void saveBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("insert into tbl_borrower (name, address, phone) values (?, ?, ?)", new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}

	public Integer addBorrowerGetPK(Borrower borrower) throws SQLException, ClassNotFoundException {
		return saveWithID("INSERT INTO tbl_borrower (name) VALUES (?)", new Object[] {borrower.getName()});
	}
	
	public void updateBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("update tbl_borrower set Name = ? where cardNo = ?",
				new Object[] { borrower.getName(), borrower.getCardNo() });
	}

	public void deleteBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("delete from tbl_borrower where cardNo = ?", new Object[] { borrower.getCardNo() });
	}

	public List<Borrower> readAllBorrowers() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_borrower", null);
	}

	public List<Borrower> readBorrowersByName(String borrowerName) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_borrower  where borrowerName = ?", new Object[] { borrowerName });
	}
	
	public List<Borrower> readBorrowerByCardNo(Integer cardNo) throws SQLException, ClassNotFoundException {
	return read("select * from tbl_borrower  where cardNo = ?", new Object[] { cardNo });
}
	
	@Override
	public List<Borrower> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		BookLoansDAO bdao = new BookLoansDAO(conn);
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhone(rs.getString("phone"));
//			b.setBookLoans(bdao.readFirstLevel(
//					"select * from tbl_book_loans where cardNo IN (select cardNo from tbl_borrower where cardNo = ?)",
//					new Object[] { b.getCardNo() }));
			borrowers.add(b);
		}
		return borrowers;
		
	}
//
	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhone(rs.getString("phone"));
			
			borrowers.add(b);
		}
		return borrowers;
	}

}
