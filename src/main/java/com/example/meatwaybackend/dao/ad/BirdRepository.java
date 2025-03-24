package com.example.meatwaybackend.dao.ad;

import com.example.meatwaybackend.model.ad.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdRepository extends JpaRepository<Bird, Long> {
}
