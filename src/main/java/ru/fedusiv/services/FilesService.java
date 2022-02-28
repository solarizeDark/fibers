package ru.fedusiv.services;

import ru.fedusiv.models.File;

import java.util.List;

public interface FilesService {

    void save(Long fiberId, List<String> fileNames);
    File getNameById(Long id);

}
