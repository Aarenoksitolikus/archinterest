package ru.itis.dao.repositories.impl;

import ru.itis.dao.entities.Project;
import ru.itis.dao.entities.User;
import ru.itis.dao.repositories.ProjectsRepository;
import ru.itis.dao.utils.JdbcUtil;
import ru.itis.dao.utils.RowMapper;

import java.sql.Connection;
import java.util.List;

public class ProjectsRepositoryImpl implements ProjectsRepository {

    private final Connection connection;
    private final JdbcUtil<Project> jdbcUtil;

    public ProjectsRepositoryImpl(Connection connection) {
        this.connection = connection;
        this.jdbcUtil = new JdbcUtil<>();
    }

    private final RowMapper<Project> projectRowMapper = (row, number) -> Project.builder()
            .id(row.getLong("id"))
            .authorId(row.getLong("author_id"))
            .title(row.getString("title"))
            .content(row.getString("content"))
            .postedAt(row.getTimestamp("created_at"))
            .address(row.getString("address"))
            .area(row.getDouble("area"))
            .year(row.getInt("year"))
            .build();

    @Override
    public List<Project> findAllByUser(User current) {
        String selectSql = String.format("select * from project where author_id = %s", current.getId());
        return jdbcUtil.selectList(connection, selectSql, projectRowMapper);
    }

    @Override
    public List<Project> findAll(User current) {
        String selectSql = String.format("select * from project p join project_tag pt on p.id = pt.project_id join account_tag at on at.account_id = %s", current.getId());
        return jdbcUtil.selectList(connection, selectSql, projectRowMapper);
    }

    @Override
    public void create(Project project) {
        String address = project.getAddress() == null ? "" : project.getAddress();
        String createSql = "insert into project (author_id, title, content, created_at, address, area, year) values (%s, '%s', '%s', now(), '%s', %s, %s))";
        createSql = String.format(createSql, project.getAuthorId(), project.getTitle(), project.getContent(), address, project.getArea(), project.getYear());
        JdbcUtil.execute(connection, createSql);
    }

    @Override
    public Project get(Long projectId) {
        String selectSql = String.format("select * from project p where p.id = %s", projectId);
        return jdbcUtil.selectOne(connection, selectSql, projectRowMapper);
    }
}
