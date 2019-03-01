package com.example.webclient;
import java.util.Date;


import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Data
public class BookRequest {
	
	private String id;
	
	private String userName;
	private String showName;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "Asia/Delhi")
	private Date bookingTime;
	private int userCount;
	private double price;

}