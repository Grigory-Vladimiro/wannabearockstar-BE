package com.rockband.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockband.model.Album;
import com.rockband.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanDatabase() {
        albumRepository.deleteAll();
    }

    @Test
    void testCreateAlbumAndGetById() throws Exception {
        Album album = new Album("Appetite for Destruction", 1987, "", "Debut album by Guns N' Roses");

        // Create album
        String response = mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Appetite for Destruction")))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Album created = objectMapper.readValue(response, Album.class);

        // Get album by id
        mockMvc.perform(get("/api/albums/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Appetite for Destruction")));
    }

    @Test
    void testGetAllAlbums() throws Exception {
        albumRepository.save(new Album("Album 1", 2000, "", ""));
        albumRepository.save(new Album("Album 2", 2001, "", ""));

        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdateAlbum() throws Exception {
        Album album = albumRepository.save(new Album("Old Title", 1999, "", ""));

        Album updated = new Album("New Title", 1999, "", "Updated!");

        mockMvc.perform(put("/api/albums/" + album.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New Title")))
                .andExpect(jsonPath("$.description", is("Updated!")));
    }

    @Test
    void testDeleteAlbum() throws Exception {
        Album album = albumRepository.save(new Album("To be deleted", 2005, "", ""));

        mockMvc.perform(delete("/api/albums/" + album.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/albums/" + album.getId()))
                .andExpect(status().isNotFound());
    }
}
