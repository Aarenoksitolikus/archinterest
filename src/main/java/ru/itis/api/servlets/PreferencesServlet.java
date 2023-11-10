package ru.itis.api.servlets;

import ru.itis.dao.entities.Tag;
import ru.itis.dao.entities.User;
import ru.itis.logic.services.TagService;
import ru.itis.logic.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PreferencesServlet", value = "/profile/preferences")
public class PreferencesServlet extends HttpServlet {
    private UserService userService;
    private TagService tagService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
        this.tagService = (TagService) servletContext.getAttribute("tagService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        User current = (User) session.getAttribute("current");
        List<Tag> tags = tagService.getList(current);
        req.setAttribute("profile", current);
        req.setAttribute("tags", tags);
        req.getServletContext().getRequestDispatcher("/WEB-INF/templates/preferences.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User profile = (User) req.getAttribute("profile");
        List<Tag> tags = tagService.getAll();
        userService.update(profile);
        userService.update(profile, tags);
        resp.sendRedirect("/archinterest/profile");
            }
}
