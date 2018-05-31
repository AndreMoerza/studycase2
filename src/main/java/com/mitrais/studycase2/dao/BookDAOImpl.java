package com.mitrais.studycase2.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mitrais.studycase2.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Book> getBooks() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Book> theQuery = 
				currentSession.createQuery("from Book order by book_name",
											Book.class);
		
		// execute query and get result list
		List<Book> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}
	
	@Override
	public void saveBook(Book book) {
		
		Session currentsession = sessionFactory.getCurrentSession();
		
		currentsession.saveOrUpdate(book);
	}
	
	@Override
	public Book getBook(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Book theBook = currentSession.get(Book.class, theId);
		
		return theBook;
	}

	@Override
	public void deleteBook(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Book where id=:bookId");
		theQuery.setParameter("bookId", theId);
		
		theQuery.executeUpdate();		
	}
	
	@Override
	public List<Book> searchBooks(String theSearchName) {

		// get current hibernate session
		Session currenSession = sessionFactory.getCurrentSession();
		Query theQuery = null;
		 if (theSearchName != null && theSearchName.trim().length() > 0) 
		 {
			// search for firstName or lastName... case insensitive
			theQuery = 
					currenSession.createQuery("from Book where lower(book_name) like :theName",
							Book.class);
			
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
	
		 }
		 else {
			 theQuery = currenSession.createQuery("from Book", Book.class);
		 }
		 
		 List<Book> books = theQuery.getResultList();
		 
		 return books;
	}
}
