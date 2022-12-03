package ru.kpfu.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.kpfu.itis.models.Flat;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FlatRepositoryImpl implements FlatRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL_FLATS = "select * from flat;";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from flat where id = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "DELETE FROM flat WHERE id = ?";

    //language=SQL
    private static final String SQL_UPDATE_FLAT = "UPDATE flat SET flatName = ?, status = ?, location = ?, cost = ? where id = ?";

    //language=SQL
    private static final String SQL_COUNT_ID = "select count(id) from flat";

    private static final RowMapper<Flat> flatMapper = (row, rowNumber) -> {
        Long id = row.getLong("id");
        String flatName = row.getString("flatName");
        String status = row.getString("status");
        String location = row.getString("location");
        Integer cost = row.getInt("cost");

        return new Flat(id,flatName,status,location,cost);
    };

    private final JdbcTemplate jdbcTemplate;

    public FlatRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Flat> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_FLATS,flatMapper);
    }

    @Override
    public void save(Flat flat) {
        Map<String,Object> paramsAsMap = new HashMap<>();
        paramsAsMap.put("flatName",flat.getFlatName());
        paramsAsMap.put("status",flat.getStatus());
        paramsAsMap.put("location",flat.getLocation());
        paramsAsMap.put("cost",flat.getCost());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        Long id = insert.withTableName("flat")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap)).longValue();
        flat.setId(id);
    }

    @Override
    public Optional<Flat> findById(Long id) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,flatMapper,id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Flat flat,Long id) {
        jdbcTemplate.update(SQL_UPDATE_FLAT,flat.getFlatName(), flat.getStatus()
                ,flat.getLocation(),flat.getCost(),
                id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,id);
    }

    public int getCount() {
        return jdbcTemplate.queryForObject(SQL_COUNT_ID,Integer.class);
    }


}
