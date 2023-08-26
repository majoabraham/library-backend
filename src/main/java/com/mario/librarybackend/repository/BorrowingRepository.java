package com.mario.librarybackend.repository;

import com.mario.librarybackend.entity.Book;
import com.mario.librarybackend.entity.Borrowing;
import com.mario.librarybackend.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByReader(Reader reader);

    Borrowing getFirstByBookAndCheckinDateIsNull(Book book);
}
