package com.rockband.backend;

import com.rockband.model.Concert;
import com.rockband.repository.ConcertRepository;
import com.rockband.service.ConcertService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


@SpringBootTest
public class ConcertServiceIntegrationTest {
@Autowired
    private ConcertService concertService;

    @Autowired
    private ConcertRepository concertRepository;

    @BeforeEach
    void setUp() {
        concertRepository.deleteAll();
    }

    @Test
    void testCreateConcert() {
        Concert concert = new Concert("Berlin", "Big Arena", "2025-08-01");
        Concert saved = concertService.createConcert(concert);

        assertNotNull(saved.getId());
        assertEquals("Berlin", saved.getCity());
        assertEquals("Big Arena", saved.getVenue());
    }

    @Test
    void testGetAllConcerts() {
        concertService.createConcert(new Concert("Paris", "Le Zénith", "2025-09-10"));
        concertService.createConcert(new Concert("London", "O2 Arena", "2025-10-12"));

        List<Concert> all = concertService.getAllConcerts();

        assertEquals(2, all.size());
    }

    @Test
    void testUpdateConcert() {
        Concert created = concertService.createConcert(new Concert("Rome", "Stadio", "2025-11-15"));

        Concert updated = new Concert("Rome", "Stadio Olimpico", "2025-11-15");
        Concert result = concertService.updateConcert(created.getId(), updated);

        assertEquals("Stadio Olimpico", result.getVenue());
    }

    @Test
    void testDeleteConcert() {
        Concert created = concertService.createConcert(new Concert("Lisbon", "Altice Arena", "2025-12-20"));

        concertService.deleteConcert(created.getId());

        assertTrue(concertService.getAllConcerts().isEmpty());
    }
}
