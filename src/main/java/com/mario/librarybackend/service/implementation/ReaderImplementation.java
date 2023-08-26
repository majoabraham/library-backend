package com.mario.librarybackend.service.implementation;

import com.mario.librarybackend.entity.Reader;
import com.mario.librarybackend.repository.ReaderRepository;
import com.mario.librarybackend.service.ReaderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderImplementation implements ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderImplementation(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public List<Reader> getAllReaders() {

        return readerRepository.findAll();
    }

    @Override
    public Reader getReaderById(Long id) {

        return readerRepository.findById(id).orElse(null);
    }

    @Override
    public Reader createReader(Reader reader) {

        return readerRepository.save(reader);
    }

    @Override
    public Reader updateReader(Reader reader) {

        Reader dbReader = readerRepository.findById(reader.getId()).orElse(null);

        if (dbReader == null) {
            throw new RuntimeException("Reader with id '%d' is not in DB.".formatted(reader.getId()));
        }

        dbReader.setFirstName(reader.getFirstName());
        dbReader.setLastName(reader.getLastName());
        dbReader.setIdCard(reader.getIdCard());
        dbReader.setBirthDate(reader.getBirthDate());

        return readerRepository.save(dbReader);
    }
}
