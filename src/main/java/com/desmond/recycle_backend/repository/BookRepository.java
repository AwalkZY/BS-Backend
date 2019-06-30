package com.desmond.recycle_backend.repository;

import com.desmond.recycle_backend.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT book FROM Book book WHERE book.name LIKE CONCAT('%',:bookname,'%')")
    List<Book> findBooksByNameLike(@Param("bookname") String bookname);

    @Query("SELECT book FROM Book book WHERE book.ISBN LIKE CONCAT('%',:ISBN,'%')")
    List<Book> findBooksByISBNLike(@Param("ISBN") String ISBN);

    @Query("SELECT book FROM Book book WHERE book.tags LIKE CONCAT('%',:tags,'%')")
    List<Book> findBooksByTagsLike(@Param("tags") String tags);
}
