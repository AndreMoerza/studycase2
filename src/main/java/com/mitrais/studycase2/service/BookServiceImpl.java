package com.mitrais.studycase2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.studycase2.dao.BookDAO;
import com.mitrais.studycase2.entity.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;
	
	@Override
	@Transactional
	public List<Book> getBooks() {
		return bookDAO.getBooks();
	}
	
	@Override
	@Transactional
	public void saveBook(Book book) {
		bookDAO.saveBook(book);
	}
	
	@Override
	@Transactional
	public Book getBook(int theId) {
		return bookDAO.getBook(theId);
	}
	
	@Override
	@Transactional
	public void deleteBook(int theId) {
		bookDAO.deleteBook(theId);
	}
	
	@Override
	@Transactional
	public List<Book> searchBooks(String theSearchName) {
		return bookDAO.searchBooks(theSearchName);
	}
}
