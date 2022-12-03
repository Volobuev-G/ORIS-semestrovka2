package ru.kpfu.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.kpfu.itis.models.Comment;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommentRepositoryImpl implements CommentRepository{

    //language=SQL
    private static final String SQL_SELECT_ALL_COMMENTS = "select * from comment;";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from comment where id = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "DELETE FROM comment WHERE id = ?";

    //language=SQL
    private static final String SQL_UPDATE_USER = "UPDATE users SET email = ?, username = ?, password = ?, role = ? where id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_USERNAME_PASSWORD = "select * from users where username = ? and password = ?";

    //language=SQL
    private static final String SQL_UPDATE_COMMENT = "UPDATE comment SET author = ?, id_flat = ?, date = ?, content = ? where id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM users WHERE username = ?";

    //language=SQL
    private static final String SQL_SELECT_USER_ID = "select * from users where email = ? and username = ? and password = ?";

    private static final RowMapper<Comment> commentMapper = (row, rowNumber) -> {
        Long id = row.getLong("id");
        Long idFlat = row.getLong("id_flat");
        String author = row.getString("author");
        Date date = row.getDate("date");
        String content = row.getString("content");

        return new Comment(id,idFlat,author,date,content);
    };

    private final JdbcTemplate jdbcTemplate;

    public CommentRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Comment> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_COMMENTS,commentMapper);
    }

    @Override
    public void save(Comment comment) {
        Map<String,Object> paramsAsMap = new HashMap<>();
        paramsAsMap.put("idFlat",comment.getIdFlat());
        paramsAsMap.put("author",comment.getAuthor());
        paramsAsMap.put("date",comment.getDate());
        paramsAsMap.put("content",comment.getContent());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        Long id = insert.withTableName("comment")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap)).longValue();
        comment.setId(id);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,commentMapper,id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Comment commentNew, Long id) {
        jdbcTemplate.update(SQL_UPDATE_COMMENT,commentNew.getAuthor(), commentNew.getIdFlat()
                ,commentNew.getDate(),commentNew.getContent(),
                id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,id);
    }
}
