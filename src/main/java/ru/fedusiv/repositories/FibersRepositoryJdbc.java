package ru.fedusiv.repositories;

import ru.fedusiv.models.Fiber;

import javax.sql.DataSource;
import java.util.List;

public class FibersRepositoryJdbc implements FibersRepository {

    //language=SQL
    private static String SQL_DELETE_FIBER = "delete from fibers where id = ?";

    //language=SQL
    private static String SQL_FIND_ALL =
            "select id, section, creation_date at time zone 'utc' at time zone 'Europe/Moscow' as cd," +
            "comment_to from fibers";

    //language=SQL
    private static String SQL_FIND_ALL_OF =
            "select id, section, creation_date at time zone 'utc' at time zone 'Europe/Moscow' as cd," +
            "comment_to from fibers where comment_to is null";
    //language=SQL
    private static String SQL_FIND_FIBER_BY_ID =
            "select id, section, creation_date at time zone 'utc' at time zone 'Europe/Moscow' as cd," +
            "comment_to from fibers where id = ?";
    //language=SQL
    private static String SQL_INSERT_FIBER = "insert into fibers(section, creation_date, comment_to) values" +
            "(?, current_timestamp, ?)";
    //language=SQL
    private static String SQL_FIND_ALL_COMMENTS =
            "with recursive comments as (" +
                "select id, section, creation_date at time zone 'utc' at time zone 'Europe/Moscow' as cd, " +
                "comment_to from fibers where comment_to=? " +
                "union " +
                "select f.id, f.section, f.creation_date at time zone 'utc' at time zone 'Europe/Moscow' as cd, " +
                    "f.comment_to from fibers f " +
                "inner join comments c on f.comment_to = c.id)" +
                "select * from comments " +
                "order by cd";

    private JdbcTemplate jdbcTemplate;

    RowMapper<Fiber> mapper = row ->
            Fiber.builder()
                    .id(row.getLong("id"))
                    .section(row.getString("section"))
                    .creationDate(row.getTimestamp("cd").toLocalDateTime())
                    .commentTo(row.getLong("comment_to") == 0 ? null : row.getLong("comment_to"))
                    .build();

    public FibersRepositoryJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Fiber entity) {
        jdbcTemplate.update(SQL_INSERT_FIBER, entity.getSection(), entity.getCommentTo());
    }

    @Override
    public void update(Fiber entity) {

    }

    @Override
    public void delete(Fiber entity) {
        jdbcTemplate.update(SQL_DELETE_FIBER, entity.getId());
    }

    @Override
    public List<Fiber> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, mapper);
    }

    @Override
    public Fiber findById(Long id) {
        List<Fiber> fiber = jdbcTemplate.query(SQL_FIND_FIBER_BY_ID, mapper, id);
        if (fiber.size() != 1) {
            throw new IllegalArgumentException("Fiber not found");
        }
        return fiber.get(0);
    }

    @Override
    public List<Fiber> findAllComments(Long id) {
        return jdbcTemplate.query(SQL_FIND_ALL_COMMENTS, mapper, id);
    }

    @Override
    public List<Fiber> findAllOpeningFibers() {
        return jdbcTemplate.query(SQL_FIND_ALL_OF, mapper);
    }
}
