package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO<T> {

	public static Connection conn = null;
	private Integer pageNo = 1;
	private Integer pageSize = 10;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public BaseDAO(Connection conn) {
		this.conn = conn;
	}

	public void save(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
	}
	public void saveList(String sql, List<Object> vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
	}
	
	public Integer saveWithID(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		while(rs.next()){
			return rs.getInt(1);
		}
		return null;
	}
	
	public Integer saveWithID2(String sql, List<Object> vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		while(rs.next()){
			return rs.getInt(1);
		}
		return null;
	}

  
	
	
	
	public Integer readCount(String sql) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(sql);
		int count = 0;
	    while(rs.next())
	        count =rs.getInt(1);
	    
		return count;
	}
	
	public List<T> read(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		Integer index = (getPageNo() -1) * getPageSize();
		sql=sql+ " LIMIT "+index+" , "+getPageSize();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		return extractData(pstmt.executeQuery());
	}
	
	

	public abstract List<T> extractData(ResultSet rs) throws ClassNotFoundException, SQLException;

	public List<T> readFirstLevel(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (vals != null) {
			int count = 1;
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		return extractDataFirstLevel(pstmt.executeQuery());
	}

	public abstract List<T> extractDataFirstLevel(ResultSet rs) throws ClassNotFoundException, SQLException;
	
	
	public Integer getCount(String sql, Object[] vals) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null){
			int count = 1;
			for(Object o:vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			return rs.getInt("COUNT");
		}
		return null;
	}
	
}
