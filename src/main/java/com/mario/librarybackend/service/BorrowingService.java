package com.mario.librarybackend.service;

import com.mario.librarybackend.entity.Book;
import com.mario.librarybackend.entity.Borrowing;
import com.mario.librarybackend.entity.Reader;

import java.util.Date;
import java.util.List;

public interface BorrowingService {
    List<Borrowing> getAllBorrowings();

    Borrowing getBorrowingById(Long id);

    Borrowing createBorrowing(Borrowing borrowing);

    Borrowing updateBorrowing(Borrowing borrowing);

    List<Borrowing> getBorrowingsByReader(Reader reader);

    Borrowing checkout(Reader reader, Book book, Date checkoutDate);

    Borrowing checkout(Borrowing borrowing);

    Borrowing checkin(Borrowing borrowing);

    Borrowing checkin(Long borrowingId, Date checkinDate);

    Reader getReader(Book book);

    Borrowing getBorrowingByBookId(Long bookId);
}
