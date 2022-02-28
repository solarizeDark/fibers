package ru.fedusiv.repositories;

import ru.fedusiv.models.File;

import java.util.List;

public interface FilesRepository {

    void save(Long fiberId, List<String> fileNames);
    File getNameById(Long id);


}
