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
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final BookService bookService;
    private final ReaderService readerService;

    public BorrowingController(BorrowingService borrowingService, BookService bookService, ReaderService readerService) {
        this.borrowingService = borrowingService;
        this.bookService = bookService;
        this.readerService = readerService;
    }

    @GetMapping("/borrowings")
    public ResponseEntity<List<Borrowing>> getAllBorrowings() {

        List<Borrowing> borrowings = borrowingService.getAllBorrowings();

        return new ResponseEntity<>(borrowings, HttpStatus.OK);
    }

    @GetMapping("/borrowings/{borrowingId}")
    public ResponseEntity<Borrowing> getBorrowingById(@PathVariable Long borrowingId) {

        Borrowing borrowing = borrowingService.getBorrowingById(borrowingId);

        return new ResponseEntity<>(borrowing, HttpStatus.OK);
    }

    @GetMapping("/borrowings/books/{bookId}")
    public ResponseEntity<Borrowing> getBorrowingByBookId(@PathVariable Long bookId) {

        Borrowing borrowing = borrowingService.getBorrowingByBookId(bookId);

        return new ResponseEntity<>(borrowing, HttpStatus.OK);
    }

    @PostMapping("/checkout/{bookId}/{readerId}")
    public ResponseEntity<Borrowing> checkoutBook(@PathVariable Long bookId, @PathVariable Long readerId, @RequestBody Date checkoutDate) {

        Reader reader = readerService.getReaderById(readerId);
        Book book = bookService.getBookById(bookId);

        Borrowing borrowing = borrowingService.checkout(reader, book, checkoutDate);

        return new ResponseEntity<>(borrowing, HttpStatus.CREATED);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Borrowing> checkout(@RequestBody Borrowing borrowing) {

        Borrowing createdBorrowing = borrowingService.checkout(borrowing);

        return new ResponseEntity<>(createdBorrowing, HttpStatus.CREATED);
    }

    @PutMapping("/checkin/{borrowingId}")
    public ResponseEntity<Borrowing> checkinBook(@PathVariable Long borrowingId, @RequestBody Date checkinDate) {

        Borrowing borrowing = borrowingService.checkin(borrowingId, checkinDate);

        return new ResponseEntity<>(borrowing, HttpStatus.OK);
    }

    @PutMapping("/checkin")
    public ResponseEntity<Borrowing> checkin(@RequestBody Borrowing borrowing) {

        Borrowing dbBorrowing = borrowingService.checkin(borrowing);

        return new ResponseEntity<>(dbBorrowing, HttpStatus.OK);
    }
}
