package com.rockband.service;

import org.springframework.stereotype.Service;
import com.rockband.model.Concert;
import com.rockband.repository.ConcertRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ConcertService {
 private final ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }

    public Optional<Concert> getConcertById(Long id) {
        return concertRepository.findById(id);
    }

    public Concert createConcert(Concert concert) {
        return concertRepository.save(concert);
    }

    public Concert updateConcert(Long id, Concert updatedConcert) {
        return concertRepository.findById(id)
                .map(existingConcert -> {
                    existingConcert.setCity(updatedConcert.getCity());
                    existingConcert.setVenue(updatedConcert.getVenue());
                    existingConcert.setDate(updatedConcert.getDate());
                    return concertRepository.save(existingConcert);
                })
                .orElseThrow(() -> new RuntimeException("Concert not found with id " + id));
    }

    public void deleteConcert(Long id) {
        concertRepository.deleteById(id);
    }
}
