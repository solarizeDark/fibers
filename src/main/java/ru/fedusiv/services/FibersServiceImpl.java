package ru.fedusiv.services;

import com.google.gson.Gson;
import ru.fedusiv.models.Fiber;
import ru.fedusiv.repositories.FibersRepository;
import ru.fedusiv.repositories.FilesRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FibersServiceImpl implements FibersService {

    private FibersRepository fibersRepository;
    private FilesRepository filesRepository;

    public FibersServiceImpl(FibersRepository fibersRepository, FilesRepository filesRepository) {
        this.fibersRepository = fibersRepository;
        this.filesRepository = filesRepository;
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
    public void save(HttpServletRequest request, String storage) throws IOException, ServletException {

        String section = request.getParameter("section");

        List<String> fileNames = new ArrayList<>();

        for (Part part : request.getParts()) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null) {
                fileNames.add(fileName);
                part.write(storage + File.separator + fileName);
            }
        }

        Long fiberId = fibersRepository.save(Fiber.builder().section(section).build());
        filesRepository.save(fiberId, fileNames);
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
