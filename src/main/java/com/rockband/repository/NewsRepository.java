package com.rockband.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rockband.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {

}
