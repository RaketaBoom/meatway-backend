package com.example.meatwaybackend.dao.ad;

import com.example.meatwaybackend.model.ad.Advertisement;
import com.example.meatwaybackend.model.ad.Specialmeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpecialmeatRepository extends JpaRepository<Specialmeat, Long>, JpaSpecificationExecutor<Specialmeat> {
}
