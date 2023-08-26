package com.mario.librarybackend.repository;

import com.mario.librarybackend.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
