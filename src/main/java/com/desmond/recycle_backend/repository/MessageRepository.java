package com.desmond.recycle_backend.repository;

import com.desmond.recycle_backend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
