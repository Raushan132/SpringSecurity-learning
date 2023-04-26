package com.security.learn.repository;

import com.security.learn.model.CustomUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomUserRepo  extends CrudRepository<CustomUser,Long> {

    List<CustomUser> findByEmail(String email);
}
