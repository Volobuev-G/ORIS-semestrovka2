package ru.kpfu.itis.servlets;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.models.Flat;
import ru.kpfu.itis.repositories.FlatRepositoryImpl;

import java.io.IOException;

@WebServlet(name = "FlatCreateServlet", value = "/flat/create")
public class FlatCreateServlet extends HttpServlet {

    private FlatRepositoryImpl flatRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        flatRepository = (FlatRepositoryImpl) getServletContext().getAttribute("flatRepository");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/flats/create.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String location = request.getParameter("location");
        String cost = request.getParameter("cost");

        if(name != null && status != null && location != null && cost != null) {
            Flat flat = new Flat(name,status,location,Integer.parseInt(cost));
            flatRepository.save(flat);
            response.sendRedirect("/flats/flat/list/");
            return;
        } else {
            request.setAttribute("message","You have to fill all fields");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/flats/create.jsp").forward(request,response);
    }
}
