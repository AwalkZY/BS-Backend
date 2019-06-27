package com.desmond.recycle_backend.repository;

import com.desmond.recycle_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByToken(String token);
    int countByName(String name);
    int countByEmail(String email);
}
