package com.rockband.service;

import com.rockband.model.Album;
import com.rockband.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

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
                    return albumRepository.save(album);
                })
                .orElseGet(() -> {
                    updatedAlbum.setId(id);
                    return albumRepository.save(updatedAlbum);
                });
    }
}
