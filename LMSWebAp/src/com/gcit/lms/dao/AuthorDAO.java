package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

public class AuthorDAO extends BaseDAO<Author> {

	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void saveAuthor(Author author) throws SQLException, ClassNotFoundException {
		save("insert into tbl_author (authorName) values (?)", new Object[] { author.getAuthorName() });
	}
	public Integer addAuthorGetPK(Author author) throws SQLException, ClassNotFoundException {
		return saveWithID("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] {author.getAuthorName()});
	}
	

	public void updateAuthor(Author author) throws SQLException, ClassNotFoundException {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void deleteAuthor(Author author) throws SQLException, ClassNotFoundException {
		save("delete from tbl_author where authorId = ?", new Object[] { author.getAuthorId() });
	}

	public List<Author> readAllAuthors(Integer pageNo) throws SQLException, ClassNotFoundException {
		setPageNo(pageNo);
		return read("select * from tbl_author", null);
	}

	public List<Author> readAuthorsByName(String authorName) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_author where authorName = ?", new Object[] { authorName });
	}
	
	public Integer getAuthorsCount(String authorName) throws ClassNotFoundException, SQLException{
		return getCount("SELECT COUNT(*) COUNT FROM tbl_author", null);
	}
	
	public Integer addAuthorWithID(Author author) throws SQLException, ClassNotFoundException {
		List<Object> list = new ArrayList<>();
		list.add(author.getAuthorName());
		return saveWithID2("insert into tbl_author (authorName) values (?)", list);
	}
	
	//add book authors
	//public void addBookAuthors(Integer bookId, Integer authorId) throws SQLException, ClassNotFoundException {
	//	save("INSERT INTO tbl_book_authors (?, ?)", new Object[]{bookId, authorId});
	//}

	//read author by key
	public Author readAuthorByPk(Integer authorId) throws ClassNotFoundException, SQLException{
		List<Author> authors =  read("SELECT * FROM tbl_author WHERE authorId = ?", new Object[]{authorId});
		if(authors!=null){
			return authors.get(0);
		}
		return null;
	}
	
	@Override
	public List<Author> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		BookDAO bdao = new BookDAO(conn);
		List<Author> authors = new ArrayList<>();
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			a.setBooks(bdao.readFirstLevel(
					"select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?)",
					new Object[] { a.getAuthorId() }));
			authors.add(a);
		}
		return authors;
	}

	@Override
	public List<Author> extractDataFirstLevel(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Author> authors = new ArrayList<>();
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}

}
