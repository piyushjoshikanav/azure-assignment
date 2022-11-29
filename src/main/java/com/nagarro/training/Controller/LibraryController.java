	package com.nagarro.training.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.training.Repository.AuthorRepo;
import com.nagarro.training.Repository.BookRepo;
import com.nagarro.training.model.AllAuthors;
import com.nagarro.training.model.AllBooks;
import com.nagarro.training.model.BookAuthor;
import com.nagarro.training.model.BooksModel;

@Controller
@RequestMapping(path = "/")
public class LibraryController {
//	private static final String GET_ALL_BOOKS = "http://localhost:8090/books";
//	private static final String GET_Author = "http://localhost:8090/add";
//	private static final String POST_BOOK = "http://localhost:8090/add";
//	private static final String GET_EDIT_BOOK = "http://localhost:8090/edit";
//	private static final String DELETE_BOOK = "http://localhost:8090/delete";
//	RestTemplate restTemplate = new RestTemplate();
//	
	@Autowired
	AuthorRepo ar;
	@Autowired
	BookRepo br;

	@GetMapping("add")
	public ModelAndView RedirectAdd() {
		ModelAndView mv = new ModelAndView();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		String currDate = dateFormat.format(date);
		mv.addObject("date", currDate);
//		AllAuthors author = restTemplate.getForObject(GET_Author, AllAuthors.class);
		AllAuthors author = new AllAuthors();
		author.setAuthors((List<BookAuthor>) ar.findAll());
//		return author;
		
		mv.addObject("authors", author);
		mv.setViewName("AddBook");
		return mv;
	}

	@PostMapping("add")
	public ModelAndView AddBook(BooksModel book) {
		boolean check = true;
		ModelAndView mv = new ModelAndView();

//		AllBooks b = restTemplate.getForObject(GET_ALL_BOOKS, AllBooks.class);
		AllBooks b = new AllBooks();
		b.setBooks((List<BooksModel>) br.findAll());

		List<BooksModel> Listbooks = b.getBooks();
		for (BooksModel bm : Listbooks) {
			if (bm.getBookCode() == book.getBookCode()) {
				check = false;
				break;
			}

		}
		if (check) {
//			BooksModel Books = restTemplate.postForObject(POST_BOOK, book, BooksModel.class);
			BooksModel Books = br.save(book);

			mv.setViewName("Index");
//			AllBooks b1 = restTemplate.getForObject(GET_ALL_BOOKS, AllBooks.class);
			AllBooks b1 = new AllBooks();
			b1.setBooks((List<BooksModel>) br.findAll());

			mv.addObject("result", b1);

		} else {
			mv.addObject("error", "Book Code Already Exists");
			mv = RedirectAdd();

		}
		return mv;
	}

	@GetMapping("/edit")
	public ModelAndView EditRedirect(@RequestParam("Id") int id) {
		ModelAndView mv = new ModelAndView();
		BooksModel book = new BooksModel();

		book.setBookCode(id);
//		BooksModel bm = restTemplate.postForObject(GET_EDIT_BOOK, book, BooksModel.class);
		BooksModel bm = br.findById(book.getBookCode()).orElse(new BooksModel());
//		AllAuthors author = restTemplate.getForObject(GET_Author, AllAuthors.class);
		AllAuthors author = new AllAuthors();
		author.setAuthors((List<BookAuthor>) ar.findAll());
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		String currDate = dateFormat.format(date);
		mv.addObject("date", currDate);
		mv.addObject("authors", author);
		mv.addObject("result", bm);
		mv.setViewName("Edit");
		return mv;
	}

	@PostMapping("/edit")
	public ModelAndView EditSubmit(BooksModel book) {
		ModelAndView mv = new ModelAndView();

//		BooksModel Books = restTemplate.postForObject(POST_BOOK, book, BooksModel.class);
		BooksModel Books = br.save(book);
		mv.setViewName("Index");
//		AllBooks b1 = restTemplate.getForObject(GET_ALL_BOOKS, AllBooks.class);
		AllBooks b1 = new AllBooks();
		b1.setBooks((List<BooksModel>) br.findAll());

		mv.addObject("result", b1);

		return mv;
	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam("Id") int code) {
		ModelAndView mv = new ModelAndView();
		BooksModel book = new BooksModel();
		book.setBookCode(code);
		System.out.println(book.getBookCode());
//		restTemplate.postForObject(DELETE_BOOK, book, BooksModel.class);
		br.deleteById(book.getBookCode());

		mv.setViewName("Index");
//		AllBooks b = restTemplate.getForObject(GET_ALL_BOOKS, AllBooks.class);
		AllBooks b = new AllBooks();
		b.setBooks((List<BooksModel>) br.findAll());

		mv.addObject("result", b);
		return mv;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("login");
		return mv;
	}
}
