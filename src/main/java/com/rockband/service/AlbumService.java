package com.rockband.service;

import com.rockband.model.Album;
import com.rockband.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final List<String> sampleSongs = List.of(
            "Rock Anthem", "Guitar Hero", "Drum Solo",
            "Stage Dive", "Thunder Riff", "Power Ballad",
            "Metal Groove", "Bassline Magic", "Echoes of Fire"
    );
    private final Random random = new Random();

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    public Album saveAlbum(Album album) {
        if (album.getSongs() == null || album.getSongs().isEmpty()) {
            album.setSongs(generateRandomSongs());
        }
        return albumRepository.save(album);
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    public Album updateAlbum(Long id, Album updatedAlbum) {
        return albumRepository.findById(id)
                .map(album -> {
                    album.setTitle(updatedAlbum.getTitle());
                    album.setReleaseYear(updatedAlbum.getReleaseYear());
                    album.setCoverUrl(updatedAlbum.getCoverUrl());
                    album.setDescription(updatedAlbum.getDescription());
                    album.setSongs(updatedAlbum.getSongs()); // обновляем песни тоже
                    return albumRepository.save(album);
                })
                .orElseGet(() -> {
                    updatedAlbum.setId(id);
                    if (updatedAlbum.getSongs() == null || updatedAlbum.getSongs().isEmpty()) {
                        updatedAlbum.setSongs(generateRandomSongs());
                    }
                    return albumRepository.save(updatedAlbum);
                });
    }

    private List<String> generateRandomSongs() {
        return random.ints(0, sampleSongs.size())
                .distinct()
                .limit(3)
                .mapToObj(sampleSongs::get)
                .toList();
    }
}
