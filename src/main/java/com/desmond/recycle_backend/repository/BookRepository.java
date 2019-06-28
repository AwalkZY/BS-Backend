package com.desmond.recycle_backend.repository;

import com.desmond.recycle_backend.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
