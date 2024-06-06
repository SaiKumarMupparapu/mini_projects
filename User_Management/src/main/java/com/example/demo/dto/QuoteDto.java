package com.example.demo.dto;

import org.springframework.stereotype.Component;

@Component
public class QuoteDto {

	private String text;
	private String author;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
