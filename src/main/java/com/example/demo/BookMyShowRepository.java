package com.example.demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface BookMyShowRepository  extends ReactiveMongoRepository<BookRequest,String>{

//	Flux<Book> save(Flux<Book> book);

}
