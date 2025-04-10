package com.example.meatwaybackend.dao.ad;

import com.example.meatwaybackend.model.ad.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BirdRepository extends JpaRepository<Bird, Long>, JpaSpecificationExecutor<Bird> {
}
