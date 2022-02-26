package ru.fedusiv.services;

import ru.fedusiv.repositories.FilesRepository;

import java.util.List;

public class FilesServiceImpl implements FilesService {

    private FilesRepository filesRepository;

    public FilesServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public void save(Long fiberId, List<String> fileNames) {
        filesRepository.save(fiberId, fileNames);
    }
}
