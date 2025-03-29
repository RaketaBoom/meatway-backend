package com.example.meatwaybackend.dao.ad;

import com.example.meatwaybackend.model.ad.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, JpaSpecificationExecutor<Advertisement> {
    Advertisement findByTitle(String testTitle);
}
