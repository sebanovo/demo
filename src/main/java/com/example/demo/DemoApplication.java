package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return ("<button id='boton'>Precioname</button><script>const button = document.getElementById('boton'); button.addEventListener('click', () => alert('puto'));</script>");
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "Este es el dashboard";
	}

	@GetMapping("/imagen")
	public String imagen() {
		return ("<img src='/images/logo.png' alt='Mi Imagen' />");
	}

}
