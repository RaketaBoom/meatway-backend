package com.example.meatwaybackend.dao;

import com.example.meatwaybackend.model.ad.Advertisement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdvertisementRepositoryTest {

    @Autowired
    private AdvertisementRepository repo;

    @Test
    void testFindByTitle() {
        Advertisement foundAd = repo.findByTitle("Курица бройлерная");

        assertNotNull(foundAd);
        assertEquals("Курица бройлерная", foundAd.getTitle());
    }
}