package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;


public class GenreDAO extends BaseDAO<Genre> {

	public GenreDAO(Connection conn) {
		super(conn);
	}

	public void saveGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}

	public Integer addGenreGetPK(Genre genre) throws SQLException, ClassNotFoundException {
		return saveWithID("INSERT INTO tbl_genre (genre_name) VALUES (?)", new Object[] {genre.getGenreName()});
	}
	
	public void updateGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("update tbl_genre set genre_name = ? where genre_Id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("delete from tbl_genre where genre_Id = ?", new Object[] { genre.getGenreId() });
	}

	public List<Genre> readAllGenres() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_genre", null);
	}


	@Override
	public List<Genre> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		//BookDAO bdao = new BookDAO(conn);
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_Id"));
			genre.setGenreName(rs.getString("genre_name"));
//			a.setBooks(bdao.readFirstLevel(
//					"select * from tbl_book where bookId IN (select bookId from tbl_book_genres where genreId = ?))",
//					new Object[] { a.getGenreId() }));
			genres.add(genre);
		}
		return genres;
	}

	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_Id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}

}


