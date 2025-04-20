package com.rockband.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rockband.model.News;
import com.rockband.repository.NewsRepository;
import com.rockband.service.NewsService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NewsServiceTest {
private NewsRepository newsRepository;
    private NewsService newsService;

    @BeforeEach
    void setUp() {
        newsRepository = mock(NewsRepository.class);
        newsService = new NewsService(newsRepository);
    }

    @Test
    void testGetAllNews() {
        List<News> mockNews = Arrays.asList(
                new News("First news", LocalDate.now()),
                new News("Second news", LocalDate.now())
        );

        when(newsRepository.findAll()).thenReturn(mockNews);

        List<News> result = newsService.getAllNews();
        assertEquals(2, result.size());
        verify(newsRepository, times(1)).findAll();
    }

    @Test
    void testGetNewsById() {
        News news = new News("Some news", LocalDate.now());
        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));

        Optional<News> result = newsService.getNewsById(1L);
        assertTrue(result.isPresent());
        assertEquals("Some news", result.get().getText());
        verify(newsRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveNews() {
        News news = new News("Test news", LocalDate.now());

        when(newsRepository.save(news)).thenReturn(news);

        News result = newsService.saveNews(news);
        assertEquals("Test news", result.getText());
        verify(newsRepository, times(1)).save(news);
    }

    @Test
    void testUpdateNews() {
        News existing = new News("Old text", LocalDate.of(2023, 1, 1));
        News updated = new News("Updated text", LocalDate.of(2024, 4, 1));

        when(newsRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(newsRepository.save(any(News.class))).thenAnswer(i -> i.getArgument(0));

        News result = newsService.updateNews(1L, updated);

        assertEquals("Updated text", result.getText());
        assertEquals(LocalDate.of(2024, 4, 1), result.getDate());
        verify(newsRepository).findById(1L);
        verify(newsRepository).save(existing);
    }

    @Test
    void testDeleteNews() {
        doNothing().when(newsRepository).deleteById(1L);

        newsService.deleteNews(1L);

        verify(newsRepository, times(1)).deleteById(1L);
    }
}
