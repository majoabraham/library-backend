package com.mario.librarybackend.service;

import com.mario.librarybackend.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book createBook(Book book);

    Book updateBook(Book book);
}
