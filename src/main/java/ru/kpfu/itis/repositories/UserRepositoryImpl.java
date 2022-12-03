package ru.kpfu.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.kpfu.itis.exceptions.DbException;
import ru.kpfu.itis.jdbc.SimpleDataSource;
import ru.kpfu.itis.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{

    //language=SQL
    private static final String SQL_SELECT_ALL_USERS = "select * from users;";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE id = ?";

    //language=SQL
    private static final String SQL_UPDATE_USER = "UPDATE users SET email = ?, username = ?, password = ?, role = ? where id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_USERNAME_PASSWORD = "select * from users where username = ? and password = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM users WHERE username = ?";

    //language=SQL
    private static final String SQL_SELECT_USER_ID = "select * from users where email = ? and username = ? and password = ?";

    private static final RowMapper<User> userMapper = (row, rowNumber) -> {
        Long id = row.getLong("id");
        String email = row.getString("email");
        String username = row.getString("username");
        String password = row.getString("password");
        String role = row.getString("role");

        return new User(id,email,username,password,role);
    };

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS,userMapper);
    }

    @Override
    public void save(User user) {
        Map<String,Object> paramsAsMap = new HashMap<>();
        paramsAsMap.put("email",user.getEmail());
        paramsAsMap.put("userName",user.getUsername());
        paramsAsMap.put("password",user.getPassword());
        paramsAsMap.put("role",user.getRole());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        Long id = insert.withTableName("users")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap)).longValue();
        user.setId(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,userMapper,id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(User user,Long id) {
        jdbcTemplate.update(SQL_UPDATE_USER,user.getEmail(), user.getUsername()
                ,user.getPassword(),user.getRole(),
                id);

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,id);
    }

    public User getUserByUsernameAndPassword(String username,String password){
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME_PASSWORD,userMapper,username,password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean emailExist(String email) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL,userMapper,email) != null;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean usernameExist(String username) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME,userMapper,username) != null;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public Long getUserId(String email, String username, String password) {
//        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
//        insert.withTableName("users").usingGeneratedKeyColumns("id").executeAndReturnKey();
        User user = jdbcTemplate.queryForObject(SQL_SELECT_USER_ID,userMapper,email,username,password);
        return user.getId();

    }
}