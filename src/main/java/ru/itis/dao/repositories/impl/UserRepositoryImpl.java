package ru.itis.dao.repositories.impl;

import ru.itis.dao.entities.Tag;
import ru.itis.dao.entities.User;
import ru.itis.dao.repositories.UserRepository;
import ru.itis.dao.utils.JdbcUtil;
import ru.itis.dao.utils.RowMapper;

import java.sql.Connection;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;
    private final JdbcUtil<User> jdbcUtil;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
        this.jdbcUtil = new JdbcUtil<>();
    }

    private final RowMapper<User> userRowMapper = (row, number) -> User.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .surname(row.getString("surname"))
            .lastname(row.getString("lastname"))
            .username(row.getString("username"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .registeredAt(row.getTimestamp("registered_at").toLocalDateTime())
            .build();

    @Override
    public User get(Long id) {
        String selectSql = "select * from account where id = %s";
        selectSql = String.format(selectSql, id);
        return jdbcUtil.selectOne(connection, selectSql, userRowMapper);
    }

    @Override
    public User get(String username, String password) {
        String selectSql = "select * from account where username = '%s' and password = '%s'";
        selectSql = String.format(selectSql, username, password);
        return jdbcUtil.selectOne(connection, selectSql, userRowMapper);
    }

    @Override
    public void update(User user) {
        String updateSql = "update account set name = '%s', surname = '%s', last_name = '%s' where id = %s)";
        updateSql = String.format(updateSql, user.getName(), user.getSurname(), user.getLastname(), user.getId());
        JdbcUtil.execute(connection, updateSql);
    }

    @Override
    public void update(User profile, List<Tag> tags) {
        update(profile);
        for (Tag tag : tags) {
            String createSql = "insert into account_tags (account_id, tag_id) values (%s, %s)";
            createSql = String.format(createSql, profile.getId(), tag.getId());
            JdbcUtil.execute(connection, createSql);
        }
    }

    @Override
    public void create(User user) {
        String createSql = "insert into account (username, email, password, registered_at) values ('%s', '%s', '%s', now())";
        createSql = String.format(createSql, user.getUsername(), user.getEmail(), user.getPassword());
        JdbcUtil.execute(connection, createSql);
    }
}
