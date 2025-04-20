package com.rockband;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.rockband.model.Album;
import com.rockband.model.Concert;
import com.rockband.model.News;
import com.rockband.repository.AlbumRepository;
import com.rockband.repository.ConcertRepository;
import com.rockband.repository.NewsRepository;

import java.time.LocalDate;

@SpringBootApplication
@ComponentScan(basePackages = "com.rockband")  // additional scan for components
public class RockbandBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RockbandBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(
		AlbumRepository albumRepository,
		ConcertRepository concertRepository,
		NewsRepository newsRepository
	) {
		return args -> {

			albumRepository.save(new Album("Highway to Hell", 1979, "img/albums/album1.jpg", "Legendary AC/DC album"));
			albumRepository.save(new Album("Back in Black", 1980, "img/albums/album2.jpg", "One of the best-selling albums ever"));
			albumRepository.save(new Album("The Black Album", 1991, "img/albums/album3.jpg", "Metallica's iconic album"));

			concertRepository.save(new Concert("Madrid", "Rock Arena", "2025-05-10"));
			concertRepository.save(new Concert("Barcelona", "Electric Stage", "2025-06-02"));
			concertRepository.save(new Concert("Valencia", "Sunset Club", "2025-07-15"));

			newsRepository.save(new News("Site launched! Stay tuned for updates.", LocalDate.of(2025, 4, 20)));
		};
	}
}
