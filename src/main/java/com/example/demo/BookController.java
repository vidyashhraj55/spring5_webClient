package com.example.demo;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/booking")
public class BookController {

	
	@Autowired
	BookService bookservice;
	
	@GetMapping("/book/{bookingId}")
public ResponseEntity<Mono<BookRequest>> showBook(@PathVariable String bookingId) {
    return ResponseEntity
            .ok()
            .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
            .body(bookservice.findById(bookingId));
}
	
//	@GetMapping("/book/{id}")
//	public Mono<Book> getBook(@PathVariable String id) {
//		return bookservice.findById(id);
//	}
	@GetMapping("/books")
	public Flux<BookRequest> getBooks() {
		return bookservice.findAll();
	}
 @PostMapping("/book")
 public Mono<BookRequest> saveBook(@RequestBody BookRequest bookRequest) {
     
     return  bookservice.save(bookRequest);
 }
 
 
// @PutMapping("/book/{id}")
// public Mono<BookRequest> updateBook(@PathVariable String id,@RequestBody BookRequest bookrequest){
//	 return bookservice.update(bookrequest,id);
// }
 	
 @DeleteMapping("/book/{bookingId}")
 public Mono<Void>  deleteBook(@PathVariable String bookingId){
	 return bookservice.deleteById(bookingId);
 }
	
}
