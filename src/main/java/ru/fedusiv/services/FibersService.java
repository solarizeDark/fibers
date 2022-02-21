package ru.fedusiv.services;

import ru.fedusiv.models.Fiber;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface FibersService {

    List<Fiber> findAllOpeningFibers();
    Fiber findById(Long id);
    List<Fiber> findAllComments(Long id);
    void save(BufferedReader bufferedReader) throws IOException;
    void delete(Fiber fiber);
    List<Fiber> findAll();

}
