package com.study.app.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	private final HttpClient httpClient = HttpClient.newBuilder()
			.connectTimeout(Duration.ofSeconds(3))
			.build();

	@GetMapping
	public ResponseEntity<Map<String, String>> test() {
		Map<String, String> status = new HashMap<>();

		status.put("FastAPI (127.0.0.1:8000)", checkConnection("http://127.0.0.1:8000"));
		status.put("Qdrant (127.0.0.1:6333)", checkConnection("http://127.0.0.1:6333"));

		return ResponseEntity.ok(status);
	}

	private String checkConnection(String url) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.timeout(Duration.ofSeconds(3))
					.GET()
					.build();

			HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
			return "Connected (Status: " + response.statusCode() + ")";
		} catch (Exception e) {
			return "Connection Failed: " + e.getMessage();
		}
	}
}
