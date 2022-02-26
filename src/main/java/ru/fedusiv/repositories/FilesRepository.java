package ru.fedusiv.repositories;

import java.util.List;

public interface FilesRepository {

    void save(Long fiberId, List<String> fileNames);

}
