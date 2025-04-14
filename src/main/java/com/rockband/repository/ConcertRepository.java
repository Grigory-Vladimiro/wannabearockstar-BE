package com.rockband.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rockband.model.Concert;

public interface ConcertRepository extends JpaRepository<Concert, Long> {

}
