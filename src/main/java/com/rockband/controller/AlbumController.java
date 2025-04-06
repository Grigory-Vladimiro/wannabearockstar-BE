package com.rockband.controller;

import com.rockband.model.Album;
import com.rockband.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // GET all albums
    @GetMapping
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    // GET album by ID
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create album
    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        return albumService.saveAlbum(album);
    }

    // PUT update album
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album updatedAlbum) {
        return ResponseEntity.ok(albumService.updateAlbum(id, updatedAlbum));
    }

    // DELETE album
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
