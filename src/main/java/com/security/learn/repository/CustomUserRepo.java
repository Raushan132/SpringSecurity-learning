package com.security.learn.repository;

import com.security.learn.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomUserRepo extends JpaRepository<CustomUser, Long> {
    List<CustomUser> findByEmail(String email);
}
