package com.desmond.recycle_backend.controllers;

import com.desmond.recycle_backend.models.Book;
import com.desmond.recycle_backend.models.Need;
import com.desmond.recycle_backend.models.Response;
import com.desmond.recycle_backend.repository.BookRepository;
import com.desmond.recycle_backend.repository.NeedRepository;
import com.desmond.recycle_backend.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BookController {
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private NeedRepository needRepository;

    BookController(BookRepository bookRepository, UserRepository userRepository, NeedRepository needRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.needRepository = needRepository;
    }

    Map getAllBook(Map<String, String[]> request){
        Map<String, Object> data = new HashMap<>();
        List<Book> ans = bookRepository.findAll();
        data.put("info", ans);
        return new Response(data, "", 200).toMap();
    }
    Map addBook(Map<String, String[]> request) {
        String name = request.get("name")[0];
        String ISBN = request.get("isbn")[0];
        String description = request.get("description")[0];
        String image = request.get("image")[0];
        String tags = request.get("tags")[0];
        double original = Double.valueOf(request.get("original")[0]);
        double current = Double.valueOf(request.get("current")[0]);
        String deliver = request.get("deliver")[0];
        long seller = userRepository.findByToken(request.get("token")[0]).getId();
        long needID = Integer.valueOf(request.getOrDefault("need", new String[]{"0"})[0]);
        Book book = new Book(name,ISBN,description,image,original,current,tags,seller,deliver);
        bookRepository.save(book);
        if (needID != 0) {
            Need need = needRepository.findById(needID).get();
            need.setBook_id(-book.getId());
            book.setBuyer(-need.getBuyer());
            bookRepository.save(book);
        }
        return new Response(null, "", 200).toMap();
    }

    Map buyBook(Map<String, String[]> request) throws Exception {
        long id = Integer.valueOf(request.get("id")[0]);
        long buyer = userRepository.findByToken(request.get("token")[0]).getId();
        Book book = bookRepository.findById(id).orElseThrow(Exception::new);
        book.setBuyer(buyer);
        bookRepository.save(book);
        return new Response(null, "", 200).toMap();
    }
}
