package com.mitrais.studycase2.dao;

import java.util.List;

import com.mitrais.studycase2.entity.Book;

public interface BookDAO {

	public List<Book> getBooks();

	public void saveBook(Book book);

	public Book getBook(int theId);

	public void deleteBook(int theId);

	public List<Book> searchBooks(String theSearchName);

}
