package com.study.app.controllers;

import java.text.SimpleDateFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping
	public ResponseEntity<String> test(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("[yyyy.MM.dd] hh:mm:ss");
		
		return ResponseEntity.ok("Test04_Actions" + sdf.format(System.currentTimeMillis()));
	}
}
