package ru.fedusiv.services;

import ru.fedusiv.models.Fiber;

import java.util.List;

public interface FibersService {

    List<Fiber> findAllOpeningFibers();
    Fiber findById(Long id);
    List<Fiber> findAllComments(Long id);
    void save(Fiber fiber);

}
