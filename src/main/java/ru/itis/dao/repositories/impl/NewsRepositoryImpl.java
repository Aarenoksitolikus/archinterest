package ru.itis.dao.repositories.impl;

import ru.itis.dao.entities.News;
import ru.itis.dao.entities.User;
import ru.itis.dao.repositories.NewsRepository;
import ru.itis.dao.utils.JdbcUtil;
import ru.itis.dao.utils.RowMapper;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {

    private final Connection connection;
    private final JdbcUtil<News> jdbcUtil;

    private static final String SELECT_ALL = "select n.*, i.file_path from news n left join image i on n.cover_path = i.file_path";
    private static final String LEFT_JOIN = " left join news_tag nt on nt.news_id = n.id";
    private static final String WHERE_BETWEEN = " where n.created_at between %s and %s";
    private static final String WHERE_ID = " where n.id = %s";
    private static final String WHERE_TAG_IN = " and nt.tag_id in (%s)";
    private static final String ORDER_BY = " order by created_at";

    public NewsRepositoryImpl(Connection connection) {
        this.connection = connection;
        this.jdbcUtil = new JdbcUtil<>();
    }

    private final RowMapper<News> newsRowMapper = (row, number) -> News.builder()
            .id(row.getLong("id"))
            .authorId(row.getLong("author_id"))
            .title(row.getString("title"))
            .content(row.getString("content"))
            .createdAt(row.getTimestamp("created_at").toLocalDateTime())
            .coverPath(row.getString(row.getString("cover_path")))
            .build();

    @Override
    public List<News> findAll() {
        String selectSql = SELECT_ALL + ORDER_BY;
        return jdbcUtil.selectList(connection, selectSql, newsRowMapper);
    }

    @Override
    public List<News> findAll(User current) {
        List<Long> userTags = getUserTags(current);
        String selectSql = SELECT_ALL;
        if (!userTags.isEmpty()) {
            selectSql  = selectSql + LEFT_JOIN + WHERE_TAG_IN ;
        }
        selectSql = String.format(selectSql, processUserTags(userTags));
        return jdbcUtil.selectList(connection, selectSql, newsRowMapper);
    }

    @Override
    public List<News> getAllBetween(LocalDateTime from, LocalDateTime to) {
        String selectSql = SELECT_ALL + WHERE_BETWEEN + ORDER_BY;
        return jdbcUtil.selectList(connection, selectSql, newsRowMapper);
    }

    @Override
    public List<News> getAllBetweenByTags(LocalDateTime startOfDay, LocalDateTime endOfDay, User currentUser) {
        List<Long> userTags = getUserTags(currentUser);
        String selectSql = SELECT_ALL;
        if (!userTags.isEmpty()) {
            selectSql  = selectSql + LEFT_JOIN + WHERE_BETWEEN + WHERE_TAG_IN ;
        } else {
            selectSql = selectSql + WHERE_BETWEEN;
        }
        selectSql = String.format(selectSql, startOfDay, endOfDay, processUserTags(userTags));
        return jdbcUtil.selectList(connection, selectSql, newsRowMapper);
    }

    @Override
    public News get(Long id) {
        String selectSql = SELECT_ALL + WHERE_ID;
        selectSql = String.format(selectSql, id);
        return jdbcUtil.selectOne(connection, selectSql, newsRowMapper);
    }

    @Override
    public void save(News news) {
        String createSql = "insert into news (author_id, title, content, created_at, updated_at, cover_path) values (%s, %s, %s, now(), now(), ?))";
        createSql = String.format(createSql, news.getAuthorId(), news.getTitle(), news.getContent(), news.getCoverPath());
        JdbcUtil.execute(connection, createSql);
    }

    private String processUserTags(List<Long> tagIds) {
        if (tagIds.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();

        for (Long tagId : tagIds) {
            sb.append(tagId).append(", ");

        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring;
    }

    private List<Long> getUserTags(User user) {
        String selectSql = "select tag_id from account_tag where account_id = %s";
        JdbcUtil<Long> additionalJdbcUtil = new JdbcUtil<>();
        selectSql = String.format(selectSql, user.getId());
        return additionalJdbcUtil.selectList(connection, selectSql, (row, number) -> row.getLong("tag_id"));
    }
}
