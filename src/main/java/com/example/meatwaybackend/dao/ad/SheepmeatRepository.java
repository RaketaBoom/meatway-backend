package com.example.meatwaybackend.dao.ad;

import com.example.meatwaybackend.model.ad.Advertisement;
import com.example.meatwaybackend.model.ad.Sheepmeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SheepmeatRepository extends JpaRepository<Sheepmeat, Long>, JpaSpecificationExecutor<Sheepmeat> {
}
