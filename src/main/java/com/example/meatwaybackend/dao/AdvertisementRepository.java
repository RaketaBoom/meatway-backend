package com.example.meatwaybackend.dao;

import com.example.meatwaybackend.model.ad.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
    Advertisement findByTitle(String testTitle);
}
