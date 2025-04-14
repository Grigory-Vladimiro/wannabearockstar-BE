package com.rockband;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rockband.model.Album;
import com.rockband.repository.AlbumRepository;

@SpringBootApplication
public class RockbandBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RockbandBackendApplication.class, args);
	}
@Bean
    CommandLineRunner initDatabase(AlbumRepository albumRepository) {
        return args -> {
            albumRepository.save(new Album("Highway to Hell", 1979, "img/albums/highway-to-hell.jpg", "Legendary AC/DC album"));
			albumRepository.save(new Album("Back in Black", 1980, "img/albums/back-in-black.jpg", "One of the best-selling albums ever"));
			albumRepository.save(new Album("The Black Album", 1991, "img/albums/the-black-album.jpg", "Metallica's iconic album"));
        };
    }
}
