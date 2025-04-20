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
import java.util.List;
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
        Album album1 = new Album("Highway to Hell", 1979, "img/albums/album1.jpg", "Legendary AC/DC album");
        album1.setSongs(List.of("Highway to Hell", "Girls Got Rhythm", "If You Want Blood"));

        Album album2 = new Album("Back in Black", 1980, "img/albums/album2.jpg", "One of the best-selling albums ever");
        album2.setSongs(List.of("Hells Bells", "Shoot to Thrill", "Back in Black"));

        Album album3 = new Album("The Black Album", 1991, "img/albums/album3.jpg", "Metallica's iconic album");
        album3.setSongs(List.of("Enter Sandman", "Sad but True", "Nothing Else Matters"));

        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        concertRepository.save(new Concert("Madrid", "Rock Arena", "2025-05-10"));
        concertRepository.save(new Concert("Barcelona", "Electric Stage", "2025-06-02"));
        concertRepository.save(new Concert("Valencia", "Sunset Club", "2025-07-15"));

        newsRepository.save(new News("Site launched! Stay tuned for updates.", LocalDate.of(2025, 4, 20)));
    };
	}
}
