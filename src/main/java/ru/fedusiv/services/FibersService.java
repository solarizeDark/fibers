package ru.fedusiv.services;

import ru.fedusiv.models.Fiber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface FibersService {

    List<Fiber> findAllOpeningFibers();
    Fiber findById(Long id);
    List<Fiber> findAllComments(Long id);
    void save(HttpServletRequest request, String storage) throws IOException, ServletException;
    void delete(Fiber fiber);
    List<Fiber> findAll();

}
