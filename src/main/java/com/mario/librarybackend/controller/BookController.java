package com.mario.librarybackend.controller;

import com.mario.librarybackend.entity.Book;
import com.mario.librarybackend.entity.Borrowing;
import com.mario.librarybackend.entity.Reader;
import com.mario.librarybackend.service.BookService;
import com.mario.librarybackend.service.BorrowingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    private final BookService bookService;
    private final BorrowingService borrowingService;

    public BookController(BookService bookService, BorrowingService borrowingService) {
        this.bookService = bookService;
        this.borrowingService = borrowingService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {

        List<Book> books = bookService.getAllBooks();

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        Book book = bookService.getBookById(id);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {

        Book createdBook = bookService.createBook(book);

        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {

        book.setId(id);
        Book updatedBook = bookService.updateBook(book);

        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}/reader")
    public ResponseEntity<Reader> getReaderByBookId(@PathVariable Long bookId) {

        Borrowing borrowing = borrowingService.getBorrowingByBookId(bookId);

        Reader reader = null;

        if (borrowing != null) {
            reader = borrowing.getReader();
        }

        return new ResponseEntity<>(reader, HttpStatus.OK);
    }
}
