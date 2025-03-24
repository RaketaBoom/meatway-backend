package com.example.meatwaybackend.dao;

import com.example.meatwaybackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
