package com.dibasb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookRepo bookRepo;

	@GetMapping("/books")
	public ResponseEntity<?> listAll() {
		List<Book> listBooks = bookRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listBooks);
	}

}
