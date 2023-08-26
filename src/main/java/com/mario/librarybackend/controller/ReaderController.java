package com.mario.librarybackend.controller;

import com.mario.librarybackend.entity.Book;
import com.mario.librarybackend.entity.Borrowing;
import com.mario.librarybackend.entity.Reader;
import com.mario.librarybackend.service.BookService;
import com.mario.librarybackend.service.BorrowingService;
import com.mario.librarybackend.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReaderController {

    private final ReaderService readerService;
    private final BorrowingService borrowingService;
    private final BookService bookService;

    public ReaderController(ReaderService readerService, BorrowingService borrowingService, BookService bookService) {
        this.readerService = readerService;
        this.borrowingService = borrowingService;
        this.bookService = bookService;
    }

    @GetMapping("/readers")
    public ResponseEntity<List<Reader>> getAllReaders() {

        List<Reader> readers = readerService.getAllReaders();

        return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    @GetMapping("/readers/{readerId}")
    public ResponseEntity<Reader> getReaderById(@PathVariable Long readerId) {

        Reader reader = readerService.getReaderById(readerId);

        return new ResponseEntity<>(reader, HttpStatus.OK);
    }

    @PostMapping("/readers")
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {

        Reader createdReader = readerService.createReader(reader);

        return new ResponseEntity<>(createdReader, HttpStatus.CREATED);
    }

    @PutMapping("/readers/{readerId}")
    public ResponseEntity<Reader> updateReader(@PathVariable Long readerId, @RequestBody Reader reader) {

        reader.setId(readerId);
        Reader updatedReader = readerService.updateReader(reader);

        return new ResponseEntity<>(updatedReader, HttpStatus.OK);
    }

    @GetMapping("/readers/{readerId}/borrowings")
    public ResponseEntity<List<Borrowing>> getReaderBorrowing(@PathVariable Long readerId) {

        Reader reader = readerService.getReaderById(readerId);

        List<Borrowing> borrowings = borrowingService.getBorrowingsByReader(reader);

        return new ResponseEntity<>(borrowings, HttpStatus.OK);
    }

    @PostMapping("/readers/{readerId}/checkout/{bookId}")
    public ResponseEntity<Borrowing> checkoutBook(@PathVariable Long readerId, @PathVariable Long bookId, @RequestBody Date checkoutDate) {

        Reader reader = readerService.getReaderById(readerId);
        Book book = bookService.getBookById(bookId);

        Borrowing borrowing = borrowingService.checkout(reader, book, checkoutDate);

        return new ResponseEntity<>(borrowing, HttpStatus.CREATED);
    }
}
