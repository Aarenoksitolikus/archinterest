package ru.itis.api.servlets;

import ru.itis.dao.entities.Image;
import ru.itis.logic.services.ImageService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ImagesServlet", value = "/images")
public class ImagesServlet extends HttpServlet {

    private ImageService imageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.imageService = (ImageService) servletContext.getAttribute("imageService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Image> images = imageService.findAll();
        req.setAttribute("images", images);
        req.getServletContext().getRequestDispatcher("/WEB-INF/templates/projects.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part file = req.getPart("image");
        imageService.create(file);
        doGet(req, resp);
    }
}
