package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher>{

	public PublisherDAO(Connection conn) {
		super(conn);
	}

	public void savePublisher(Publisher publisher) throws SQLException, ClassNotFoundException{
		save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?)", new Object[]{publisher.getName(), publisher.getAddress(), publisher.getPhone()});
	}
	
	public Integer addPublisherGetPK(Publisher publisher) throws SQLException, ClassNotFoundException {
		return saveWithID("INSERT INTO tbl_publisher (publisherName) VALUES (?)", new Object[] {publisher.getName()});
	}
	
	
	public Integer savePublisherWithID(Publisher publisher) throws SQLException, ClassNotFoundException{
		return saveWithID("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?)", new Object[]{publisher.getName(), publisher.getAddress(), publisher.getPhone()});
	}
	
	public void updatePublisher(Publisher publisher) throws SQLException, ClassNotFoundException{
		save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?", new Object[]{publisher.getName(), publisher.getAddress(), publisher.getPhone(), publisher.getPubId()});
	}
	
	public void deletePublisher(Publisher publisher) throws SQLException, ClassNotFoundException{
		save("delete from tbl_publisher where publisherId = ?", new Object[]{publisher.getPubId()});
	}
	
	public List<Publisher> readAllPublishers() throws SQLException, ClassNotFoundException{
		return read("select * from tbl_publisher", null);
	}
	
	public List<Publisher> readPublisherById(Integer id) throws SQLException, ClassNotFoundException{
		return read("select * from tbl_publisher where publisherId = ?", new Object[]{id});
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Publisher> publishers = new ArrayList<>();
		
//		AuthorDAO adao = new AuthorDAO(conn);
//		GenreDAO gdao = new GenreDAO(conn);
		
			while(rs.next()){
				Publisher publisher = new Publisher();
				publisher.setPubId(rs.getInt("publisherId"));
				publisher.setName(rs.getString("publisherName"));
				publisher.setAddress(rs.getString("publisherAddress"));
				publisher.setPhone(rs.getString("publisherPhone"));
//				publisher.setAuthors(adao.readFirstLevel("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?))", new Object[]{publisher.getBookId()}));
				//pdao.getPublosher
				//gdao.read(get all genres)
				//br, copies
//				b.setGenres(adao.readFirstLevel("select * from tbl_genre where genreId IN (select genreId from tbl_book_genres where bookId = ?))", new Object[]{b.getBookId()}));

				publishers.add(publisher);
			}		
		return publishers;
	}
	
	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Publisher> publishers = new ArrayList<>();
		
			while(rs.next()){
				Publisher publisher = new Publisher();
				publisher.setPubId(rs.getInt("publisherId"));
				publisher.setName(rs.getString("publisherName"));
				publisher.setAddress(rs.getString("publisherAddress"));
				publisher.setPhone(rs.getString("publisherPhone"));
				publishers.add(publisher);
			}
		
		return publishers;
	}
}

