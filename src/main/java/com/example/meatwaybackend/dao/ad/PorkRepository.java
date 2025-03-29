package com.example.meatwaybackend.dao.ad;

import com.example.meatwaybackend.model.ad.Pork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PorkRepository extends JpaRepository<Pork, Long>, JpaSpecificationExecutor<Pork> {
}
