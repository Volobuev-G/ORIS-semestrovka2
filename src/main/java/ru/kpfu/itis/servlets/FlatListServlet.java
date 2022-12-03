package ru.kpfu.itis.servlets;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.repositories.FlatRepositoryImpl;

import java.io.IOException;

@WebServlet(name = "FlatListServlet", value = "/flat/list/")
public class FlatListServlet extends HttpServlet {

    private FlatRepositoryImpl flatRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        flatRepository = (FlatRepositoryImpl) getServletContext().getAttribute("flatRepository");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("flats",flatRepository.findAll());
        request.setAttribute("flatCount",flatRepository.getCount());
        getServletContext().getRequestDispatcher("/WEB-INF/views/flats/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
