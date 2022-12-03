package ru.kpfu.itis.servlets;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.models.Comment;
import ru.kpfu.itis.models.Flat;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.CommentRepositoryImpl;
import ru.kpfu.itis.repositories.FlatRepositoryImpl;
import ru.kpfu.itis.repositories.UserRepositoryImpl;
import ru.kpfu.itis.services.UserService;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "FlatDetailServlet", value = "/flat/detail")
public class FlatDetailServlet extends HttpServlet {

    private FlatRepositoryImpl flatRepository;
    private CommentRepositoryImpl commentRepository;
    private UserRepositoryImpl userRepository;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        flatRepository = (FlatRepositoryImpl) getServletContext().getAttribute("flatRepository");
        commentRepository = (CommentRepositoryImpl) getServletContext().getAttribute("commentRepository");
        userRepository = (UserRepositoryImpl) getServletContext().getAttribute("userRepository");
        userService = (UserService) getServletContext().getAttribute("userService");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Bad request. No id has been provided");
        }
        Flat flat = flatRepository.findById(Long.valueOf(id)).get();
        if (flat == null) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            getServletContext().getRequestDispatcher("/WEB-INF/views/errors/notfound.jsp").forward(request,response);
        }
        request.setAttribute("flat",flat);
        request.setAttribute("comments",commentRepository.findAll());
        getServletContext().getRequestDispatcher("/WEB-INF/views/flats/detail.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("value");
        Flat flat = flatRepository.findById(Long.valueOf(id)).get();
        User user = userService.getUser(request,response);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        String content = request.getParameter("content");
        String author = user.getUsername();
        Long flatID = Long.valueOf(id);

        if(content != null) {
            Comment comment = new Comment(flatID,author,date,content);
            commentRepository.save(comment);
            response.sendRedirect("/flats/flat/detail?id=" + flat.getId());
            return;
        } else {
            request.setAttribute("message","You should comment something");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/flats/detail.jsp").forward(request,response);
    }
}
