package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;


public class BranchDAO extends BaseDAO<Branch> {

	public BranchDAO(Connection conn) {
		super(conn);
	}

	public void saveBranch(Branch branch) throws SQLException, ClassNotFoundException {
		save("insert into tbl_library_branch (branchName) values (?)", new Object[] { branch.getBranchName() });
	}
	
	public Integer addBranchGetPK(Branch branch) throws SQLException, ClassNotFoundException {
		return saveWithID("INSERT INTO tbl_library_branch (branchName) VALUES (?)", new Object[] {branch.getBranchName()});
	}

	public void updateBranch(Branch branch) throws SQLException, ClassNotFoundException {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { branch.getBranchName(), branch.getAddress(), branch.getBranchId() });
	}

	public void deleteBranch(Branch branch) throws SQLException, ClassNotFoundException {
		save("delete from tbl_library_branch where branchId = ?", new Object[] { branch.getBranchId() });
	}

	public List<Branch> readAllBranch() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_library_branch", null);
	}
	
	public List<Branch> readBranchesByName(String branchName) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_library_branch where branchName = ?", new Object[] { branchName });
	}
	
	public Integer getBranchesCount(String branchName) throws ClassNotFoundException, SQLException{
		return getCount("SELECT COUNT(*) COUNT FROM tbl_library_branch", null);
	}
	
	public Integer addBranchWithID(Branch branch) throws SQLException, ClassNotFoundException {
		List<Object> list = new ArrayList<>();
		list.add(branch.getBranchName());
		return saveWithID2("insert into tbl_library_branch (branchName) values (?)", list);
	}
	
	public Branch readBranchByPk(Integer branchId) throws ClassNotFoundException, SQLException{
		List<Branch> branches =  read("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[]{branchId});
		if(branches!=null){
			return branches.get(0);
		}
		return null;
	}
	
	public List<Branch> readBranchHaveLoanedBook(Integer cardNo) throws SQLException, ClassNotFoundException {
		return read("select distinct tbl_library_branch.* from tbl_library_branch Join tbl_book_loans on tbl_library_branch.branchId = tbl_book_loans.branchId where tbl_book_loans.dateIn= \"1900-01-01 00:00\" and tbl_book_loans.cardNo = ?", new Object[] { cardNo });
	}

	
	
	@Override
	public List<Branch> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
	//	BookLoansDAO bdao = new BookLoansDAO(conn);
		//BookCopiesDAO bdao = new BookCopiesDAO(conn);

		List<Branch> branches = new ArrayList<>();
		while (rs.next()) {
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setAddress(rs.getString("branchAddress"));

//			branch.setBookLoans(bdao.readFirstLevel(
//					"select * from tbl_book_loans where cardNo IN (select cardNo from tbl_branch where cardNo = ?)",
//					new Object[] { branch.getCardNo() }));
			branches.add(branch);
		}
		return branches;
		
	}
//
	@Override
	public List<Branch> extractDataFirstLevel(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Branch> branches = new ArrayList<>();
			while (rs.next()) {
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branchId"));
				branch.setBranchName(rs.getString("branchName"));
				branch.setAddress(rs.getString("branchAddress"));
		
				branches.add(branch);
			}
		return branches;
	}
}

