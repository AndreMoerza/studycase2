package com.mitrais.studycase2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mitrais.studycase2.entity.Book;
import com.mitrais.studycase2.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/list")
	public String listBooks(Model theModel) {
		
		// get books from the service
		List<Book> theBooks = bookService.getBooks();
				
		// add the books to the model
		theModel.addAttribute("books", theBooks);
		
		return "list-books";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Book theBook = new Book();
		
		theModel.addAttribute("book", theBook);
		
		return "book-form";
	}
	
	@PostMapping("/saveBook")
	public String saveBook(@ModelAttribute("book") Book theBook) {
		
		// save using our service
		bookService.saveBook(theBook);	
		
		return "redirect:/book/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookId") int theId,
									Model theModel) {
		
		// get the book from our service
		Book theBook = bookService.getBook(theId);	
		
		// set book as a model attribute to pre-populate the form
		theModel.addAttribute("book", theBook);
		
		// send over to our form		
		return "book-form";
	}
	
	@GetMapping("/delete")
	public String deleteBook(@RequestParam("bookId") int theId) {
		
		// delete the book
		bookService.deleteBook(theId);
		
		return "redirect:/book/list";
	}
	
	@PostMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel)
	{
		// search customers from the service
		List<Book> theBooks = bookService.searchBooks(theSearchName);
		
		// add the customers to the model
		theModel.addAttribute("books", theBooks);
		
		return "list-books";
	}
}
