package com.mario.librarybackend.service.implementation;

import com.mario.librarybackend.entity.Book;
import com.mario.librarybackend.repository.BookRepository;
import com.mario.librarybackend.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookImplementation implements BookService {
    private final BookRepository bookRepository;

    public BookImplementation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {

        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book createBook(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {

        Book dbBook = bookRepository.findById(book.getId()).orElse(null);

        if (dbBook == null) {
            throw new RuntimeException("Book with id '%d' is not in DB.".formatted(book.getId()));
        }

        dbBook.setAuthor(book.getAuthor());
        dbBook.setTitle(book.getTitle());
        dbBook.setAvailable(book.isAvailable());

        return bookRepository.save(dbBook);
    }
}
