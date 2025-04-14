package com.rockband.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.rockband.model.Concert;
import com.rockband.service.ConcertService;

@RestController
@RequestMapping("/api/concerts")
@CrossOrigin(origins = "*")
public class ConcertController {
private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping
    public List<Concert> getAllConcerts() {
        return concertService.getAllConcerts();
    }

    @GetMapping("/{id}")
    public Concert getConcertById(@PathVariable Long id) {
        return concertService.getConcertById(id)
                .orElseThrow(() -> new RuntimeException("Concert not found with id " + id));
    }

    @PostMapping
    public Concert createConcert(@RequestBody Concert concert) {
        return concertService.createConcert(concert);
    }

    @PutMapping("/{id}")
    public Concert updateConcert(@PathVariable Long id, @RequestBody Concert concert) {
        return concertService.updateConcert(id, concert);
    }

    @DeleteMapping("/{id}")
    public void deleteConcert(@PathVariable Long id) {
        concertService.deleteConcert(id);
    }
}
