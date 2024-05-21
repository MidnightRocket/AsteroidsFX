package com.github.midnightrocket.asteroidsfx.scoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RequestMapping("/score")
@RestController
public class ScoringSystemServer {
	private long score = 0;

	public static void main(final String[] args) {
		System.out.println("Booting server");
		SpringApplication.run(ScoringSystemServer.class, args);
	}


	@GetMapping("")
	public long getScore() {
		return this.score;
	}

	@PutMapping("add/{points}")
	public long add(@PathVariable(value = "points") long points) {
		this.score += points;

		return this.score;
	}
}
