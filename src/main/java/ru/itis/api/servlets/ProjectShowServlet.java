package ru.itis.api.servlets;

import ru.itis.dao.entities.NewsComment;
import ru.itis.dao.entities.Project;
import ru.itis.logic.services.CommentService;
import ru.itis.logic.services.ProjectService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProjectShowServlet", value = "/project")
public class ProjectShowServlet extends HttpServlet {
    private ProjectService projectService;
    private CommentService commentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        projectService = (ProjectService) servletContext.getAttribute("projectsService");
        commentService = (CommentService) servletContext.getAttribute("commentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Long projectId = (Long) req.getAttribute("project");
        Project project = projectService.get(projectId);
        List<NewsComment> comments = commentService.getList(project);
        req.setAttribute("project", project);
        req.setAttribute("comments", comments);
        req.getServletContext().getRequestDispatcher("/WEB-INF/templates/single_project.html").forward(req, resp);
    }
}
