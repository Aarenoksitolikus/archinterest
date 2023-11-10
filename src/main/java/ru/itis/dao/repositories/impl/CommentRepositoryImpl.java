package ru.itis.dao.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.dao.entities.News;
import ru.itis.dao.entities.NewsComment;
import ru.itis.dao.entities.Project;
import ru.itis.dao.entities.abs.Comment;
import ru.itis.dao.repositories.CommentRepository;

import javax.sql.DataSource;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {

    private static final String SQL_GET = "select c.*, a.username";
    private static final String FROM =" from comment c";
    private static final String SQL_JOIN_PIN = " left join pin_comment pc on pc.pin_id = c.id";
    private static final String SQL_JOIN_NEWS = " left join news_comment nc on nc.news_id = c.id";
    private static final String SQL_JOIN_USER = " left join account a on a.id = c.author_id";
    private static final String SQL_GET_BY_PIN_ID = SQL_GET + FROM + SQL_JOIN_USER + SQL_JOIN_PIN + " where pc.pin_id = ?";
    private static final String SQL_GET_BY_NEWS_ID = SQL_GET + FROM + SQL_JOIN_USER + SQL_JOIN_NEWS + " where nc.pin_id = ?";
    private static final String LIKE = "update comment set likes = likes + 1 where id = ?";
    private static final String DISLIKE = "update comment set dislikes = dislikes + 1 where id = ?";

    public CommentRepositoryImpl(DataSource dataSource) {
    }

//    private final RowMapper<Comment> rowMapper = (row, number) -> Comment.builder()
//            .authorId(row.getLong("author_id"))
//            .authorUsername(row.getString("username"))
//            .content(row.getString("content"))
//            .createdAt(row.getTimestamp("created_at").toLocalDateTime())
//            .likes(row.getInt("likes"))
//            .dislikes(row.getInt("dislikes"))
//            .build();

    @Override
    public List<NewsComment> getList(News news) {
        return null;
    }

    @Override
    public List<NewsComment> getList(Project project) {
        return null;
    }
}
