package com.rockband.service;

import org.springframework.stereotype.Service;

import com.rockband.model.News;
import com.rockband.repository.NewsRepository;

@Service
public class NewsService {
private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }

    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    public News updateNews(Long id, News updatedNews) {
        return newsRepository.findById(id)
            .map(news -> {
                news.setText(updatedNews.getText());
                news.setDate(updatedNews.getDate());
                return newsRepository.save(news);
            })
            .orElseThrow(() -> new RuntimeException("News not found with id " + id));
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
