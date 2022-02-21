package ru.fedusiv.services;

import com.google.gson.Gson;
import ru.fedusiv.models.Fiber;
import ru.fedusiv.repositories.FibersRepository;

import java.io.BufferedReader;
import java.io.IOException;
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
    public void save(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String str;
        while( (str = br.readLine()) != null ){
            sb.append(str);
        }
        Gson gson = new Gson();
        Fiber fiber = gson.fromJson(sb.toString(), Fiber.class);

        fibersRepository.save(fiber);
    }

    @Override
    public void delete(Fiber fiber) {
        fibersRepository.delete(fiber);
    }

    @Override
    public List<Fiber> findAll() {
        return fibersRepository.findAll();
    }
}
