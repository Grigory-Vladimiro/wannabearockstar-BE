package com.rockband.backend;

import com.rockband.model.Album;
import com.rockband.repository.AlbumRepository;
import com.rockband.service.AlbumService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class AlbumServiceIntegrationTest {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumRepository albumRepository;

    @BeforeEach
    void cleanDatabase() {
        albumRepository.deleteAll();
    }

    @Test
    void testSaveAlbum() {
        Album album = new Album("Master of Puppets", 1986, "https://example.com/mop.jpg", "Metallica's third album");
        Album savedAlbum = albumService.saveAlbum(album);

        assertNotNull(savedAlbum.getId());
        assertEquals("Master of Puppets", savedAlbum.getTitle());
    }

    @Test
    void testGetAllAlbums() {
        albumService.saveAlbum(new Album("Album 1", 2000, "", ""));
        albumService.saveAlbum(new Album("Album 2", 2005, "", ""));

        List<Album> albums = albumService.getAllAlbums();
        assertEquals(2, albums.size());
    }

    @Test
    void testGetAlbumById() {
        Album album = new Album("Ride the Lightning", 1984, "", "");
        Album saved = albumService.saveAlbum(album);

        Optional<Album> result = albumService.getAlbumById(saved.getId());
        assertTrue(result.isPresent());
        assertEquals("Ride the Lightning", result.get().getTitle());
    }

    @Test
    void testDeleteAlbum() {
        Album album = new Album("Kill 'Em All", 1983, "", "");
        Album saved = albumService.saveAlbum(album);

        albumService.deleteAlbum(saved.getId());
        Optional<Album> deleted = albumService.getAlbumById(saved.getId());

        assertTrue(deleted.isEmpty());
    }

    @Test
    void testUpdateAlbum() {
        Album original = new Album("St. Anger", 2003, "", "Original description");
        Album saved = albumService.saveAlbum(original);

        Album updated = new Album("St. Anger (Remastered)", 2003, "", "Now sounds better!");
        Album result = albumService.updateAlbum(saved.getId(), updated);

        assertEquals("St. Anger (Remastered)", result.getTitle());
        assertEquals("Now sounds better!", result.getDescription());
    }
}
