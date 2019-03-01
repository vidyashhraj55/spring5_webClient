package com.example.webclient;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ClientController {

	WebClient webClient;
	
	public Logger log=LoggerFactory.getLogger(Spring5WebclientApplication.class);
	
	
	
	
	
	@PostConstruct
	public void init() {
		webClient=WebClient.builder().baseUrl("http://localhost:8080/booking")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.filter(logRequest()).filter(logresponse()).build();
		
	}
	
	@GetMapping("/trackAllbooking")
	public Flux<BookRequest> getAllbookings() {
		return webClient.get().uri("/books").retrieve().bodyToFlux(BookRequest.class);
	}
  
	@DeleteMapping("removebooking/{bookingId}")
	public Mono<BookRequest> deleteMethodName(@PathVariable String bookingId) {
		return webClient.delete().uri("/book/"+bookingId).retrieve().bodyToMono(BookRequest.class);
	}

	@GetMapping("/trackbooking/{bookingId}")
	public Mono<BookRequest> getMethodName(@PathVariable String bookingId) {
		return webClient.get().uri("/book/"+bookingId).retrieve().bodyToMono(BookRequest.class);
	}

	
	@PostMapping("/booknow")
	public Mono<BookRequest> bookNow(@RequestBody BookRequest bookrequest){
		return webClient.post().uri("/book").syncBody(bookrequest).retrieve().bodyToMono(BookRequest.class);
		
	}
	
	private ExchangeFilterFunction  logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest->{
		  log.info("request{} {}",clientRequest.method(),clientRequest.url());
		  return Mono.just(clientRequest);
		});
		
	}
	
	private ExchangeFilterFunction logresponse() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse->{
			log.info("response status code {}",clientResponse.statusCode());
			return Mono.just(clientResponse);
		});
	}
}
