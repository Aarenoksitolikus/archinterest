package ru.itis.dao.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.dao.entities.User;
import ru.itis.dao.repositories.UserRepository;

import javax.sql.DataSource;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final JdbcTemplate template;

    private static final String SQL_SELECT_BY_ID = "select * from account where id = ?";
    private static final String SQL_SELECT_BY_CREDENTIONALS = "select * from account where username = ? and password = ?";
    private static final String SQL_UPDATE_BY_ID = "update account set name = ?, surname = ?, last_name = ? where id = ?)";
    private static final String SQL_CREATE = "insert into account (username, password, registered_at) values (?, ?, now())";

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = (row, number) -> User.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .surname(row.getString("surname"))
            .lastname(row.getString("lastname"))
            .username(row.getString("username"))
            .password(row.getString("password"))
            .registeredAt(row.getTimestamp("registered_at").toLocalDateTime())
            .build();


    @Override
    public User get(Long id) {
        return template.query(SQL_SELECT_BY_ID, userRowMapper, id).get(0);
    }

    @Override
    public User get(String username, String password) {
        return template.query(SQL_SELECT_BY_CREDENTIONALS, userRowMapper, username, password).get(0);
    }

    @Override
    public void update(User user) {
        template.update(SQL_UPDATE_BY_ID, user.getName(), user.getSurname(), user.getLastname(), user.getId());
    }

    @Override
    public void create(User user) {
        template.update(SQL_CREATE, user.getUsername(), user.getPassword());
    }
}
