package ru.itis.api.servlets;

import ru.itis.dao.entities.Image;
import ru.itis.dao.entities.Project;
import ru.itis.dao.entities.User;
import ru.itis.logic.services.ImageService;
import ru.itis.logic.services.ProjectService;
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

@WebServlet(name = "ProjectCreateServlet", value = "/projects/create")
public class ProjectCreateServlet extends HttpServlet {
    private ProjectService projectService;
    private ImageService imageService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        projectService = (ProjectService) servletContext.getAttribute("projectsService");
        userService = (UserService) servletContext.getAttribute("userService");
        imageService = (ImageService) servletContext.getAttribute("imageService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        User current = (User) session.getAttribute("current");
        req.setAttribute("author", current.getId());
        req.getServletContext().getRequestDispatcher("/WEB-INF/templates/project_create.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Project project = (Project) req.getAttribute("project");
        List<Image> images = (List<Image>) req.getAttribute("images");
        projectService.create(project);
        imageService.create(images);
        resp.sendRedirect("/archinterest/projects");
    }
}
