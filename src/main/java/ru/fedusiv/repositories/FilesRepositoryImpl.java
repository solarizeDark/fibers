package ru.fedusiv.repositories;

import javax.sql.DataSource;
import java.util.List;

public class FilesRepositoryImpl implements FilesRepository {

    private JdbcTemplate template;

    //language=SQL
    private static String SQL_INSERT_FILES = "insert into files(fiber_id, name) values" +
            "(?, ?)";

    public FilesRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Long fiberID, List<String> fileNames) {
        template.updateBatch(SQL_INSERT_FILES, fiberID, fileNames);
    }
}
