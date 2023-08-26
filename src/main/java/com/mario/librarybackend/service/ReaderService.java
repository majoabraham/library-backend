package com.mario.librarybackend.service;

import com.mario.librarybackend.entity.Reader;

import java.util.List;

public interface ReaderService {
    List<Reader> getAllReaders();

    Reader getReaderById(Long id);

    Reader createReader(Reader reader);

    Reader updateReader(Reader reader);
}
