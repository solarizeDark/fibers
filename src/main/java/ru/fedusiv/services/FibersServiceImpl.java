package ru.fedusiv.services;

import ru.fedusiv.models.Fiber;
import ru.fedusiv.repositories.FibersRepository;

import java.util.List;

public class FibersServiceImpl implements FibersService {

    private FibersRepository fibersRepository;

    public FibersServiceImpl(FibersRepository fibersRepository) {
        this.fibersRepository = fibersRepository;
    }

    @Override
    public List<Fiber> findAllOpeningFibers() {
        return fibersRepository.findAllOpeningFibers();
    }

    @Override
    public Fiber findById(Long id) {
        return fibersRepository.findById(id);
    }

    @Override
    public List<Fiber> findAllComments(Long id) {
        return fibersRepository.findAllComments(id);
    }

    @Override
    public void save(Fiber fiber) {
        fibersRepository.save(fiber);
    }

    @Override
    public List<Fiber> findAll() {
        return fibersRepository.findAll();
    }
}
