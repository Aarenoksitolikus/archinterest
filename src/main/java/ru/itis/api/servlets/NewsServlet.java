package ru.itis.api.servlets;

import ru.itis.dao.entities.News;
import ru.itis.dao.entities.NewsComment;
import ru.itis.dao.entities.User;
import ru.itis.logic.services.CommentService;
import ru.itis.logic.services.NewsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "NewsServlet", value = "/news")
public class NewsServlet extends HttpServlet {

    private NewsService newsService;
    private CommentService commentService;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        this.newsService = (NewsService) servletContext.getAttribute("newsService");
        this.commentService = (CommentService) servletContext.getAttribute("commentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        User current = (User) session.getAttribute("current");
        Boolean today = (Boolean) req.getAttribute("today");
        Map<News, List<NewsComment>> newsMap = new HashMap<>();

        if (current != null) {
            req.setAttribute("author", current.getId());

            if (today != null && today.equals(true)) {
                newsMap.keySet().addAll(newsService.getAllToday(current));
            } else {
                newsMap.keySet().addAll(newsService.getAll(current));
            }
        } else {
            if (today != null && today.equals(true)) {
                newsMap.keySet().addAll(newsService.getAllToday());
            } else {
                newsMap.keySet().addAll(newsService.getAll());
            }
        }

        newsMap.replaceAll((news, v) -> commentService.getList(news));

        req.setAttribute("newsMap", newsMap);
        req.getServletContext().getRequestDispatcher("/WEB-INF/templates/news.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User current = (User) session.getAttribute("current");

        if (current != null) {
            News news = (News) req.getAttribute("news");
            newsService.create(news);
        }

        req.getServletContext().getRequestDispatcher("/news").forward(req, resp);
    }
}
