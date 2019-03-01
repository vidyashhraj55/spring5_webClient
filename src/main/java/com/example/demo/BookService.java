package com.example.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
Flux<BookRequest> findAll();
Mono<BookRequest> save(BookRequest bookRequest);
Mono<BookRequest> findById(String bookingId);
Mono<Void> deleteById(String bookingId);
//Mono<BookRequest> update(BookRequest bookrequest);
//Mono<BookRequ/est> update(BookRequest bookrequest, String id);
}
