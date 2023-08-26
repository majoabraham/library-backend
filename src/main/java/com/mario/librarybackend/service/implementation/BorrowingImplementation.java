package com.mario.librarybackend.service.implementation;

import com.mario.librarybackend.entity.Book;
import com.mario.librarybackend.entity.Borrowing;
import com.mario.librarybackend.entity.Reader;
import com.mario.librarybackend.repository.BookRepository;
import com.mario.librarybackend.repository.BorrowingRepository;
import com.mario.librarybackend.service.BorrowingService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BorrowingImplementation implements BorrowingService {

    private final BorrowingRepository borrowingRepository;

    private final BookRepository bookRepository;

    public BorrowingImplementation(BorrowingRepository borrowingRepository, BookRepository bookRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Borrowing> getAllBorrowings() {

        return borrowingRepository.findAll();
    }

    @Override
    public Borrowing getBorrowingById(Long id) {

        return borrowingRepository.findById(id).orElse(null);
    }

    @Override
    public Borrowing createBorrowing(Borrowing borrowing) {

        return borrowingRepository.save(borrowing);
    }

    @Override
    public Borrowing updateBorrowing(Borrowing borrowing) {

        Borrowing dbBorrowing = borrowingRepository.findById(borrowing.getId()).orElse(null);

        if (dbBorrowing == null) {
            throw new RuntimeException("Borrowing with id '%d' is not in DB.".formatted(borrowing.getId()));
        }

        dbBorrowing.setBook(borrowing.getBook());
        dbBorrowing.setReader(borrowing.getReader());
        dbBorrowing.setCheckoutDate(borrowing.getCheckoutDate());
        dbBorrowing.setCheckinDate(borrowing.getCheckinDate());

        return borrowingRepository.save(dbBorrowing);
    }

    @Override
    public List<Borrowing> getBorrowingsByReader(Reader reader) {

        return borrowingRepository.findByReader(reader);
    }

    @Override
    public Borrowing checkout(Reader reader, Book book, Date checkoutDate) {

        book.setAvailable(false);

        bookRepository.save(book);

        Borrowing borrowing = new Borrowing();

        borrowing.setReader(reader);
        borrowing.setBook(book);
        borrowing.setCheckoutDate(checkoutDate);
        borrowing.setCheckinDate(null);

        return borrowingRepository.save(borrowing);
    }

    @Override
    public Borrowing checkout(Borrowing borrowing) {

        Book book = borrowing.getBook();
        book.setAvailable(false);

        bookRepository.save(book);

        borrowing.setCheckinDate(null);

        return borrowingRepository.save(borrowing);
    }

    @Override
    public Borrowing checkin(Borrowing borrowing) {

        Borrowing dbBorrowing = borrowingRepository.findById(borrowing.getId()).orElse(null);

        if (dbBorrowing == null) {
            throw new RuntimeException("Borrowing with id '%d' is not in DB.".formatted(borrowing.getId()));
        }

        dbBorrowing.setCheckinDate(borrowing.getCheckinDate());

        Book book = borrowing.getBook();

        book.setAvailable(true);

        bookRepository.save(book);

        return borrowingRepository.save(dbBorrowing);
    }

    @Override
    public Borrowing checkin(Long borrowingId, Date checkinDate) {

        Borrowing dbBorrowing = borrowingRepository.findById(borrowingId).orElse(null);

        if (dbBorrowing == null) {
            throw new RuntimeException("Borrowing with id '%d' is not in DB.".formatted(borrowingId));
        }

        dbBorrowing.setCheckinDate(checkinDate);

        Book book = dbBorrowing.getBook();

        book.setAvailable(true);

        bookRepository.save(book);

        return borrowingRepository.save(dbBorrowing);
    }

    @Override
    public Reader getReader(Book book) {
        Borrowing borrowing = borrowingRepository.getFirstByBookAndCheckinDateIsNull(book);

        if (borrowing == null) {
            return null;
        }

        return borrowing.getReader();
    }

    @Override
    public Borrowing getBorrowingByBookId(Long bookId) {

        Book book = bookRepository.findById(bookId).orElse(null);

        return borrowingRepository.getFirstByBookAndCheckinDateIsNull(book);
    }
}
