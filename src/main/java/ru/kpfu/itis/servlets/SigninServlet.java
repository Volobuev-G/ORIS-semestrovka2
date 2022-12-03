package ru.kpfu.itis.servlets;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.UserRepositoryImpl;
import ru.kpfu.itis.services.UserService;

import java.io.IOException;

@WebServlet(name = "SigninServlet", value = "/signin")
public class SigninServlet extends HttpServlet {

    private UserRepositoryImpl userRepository;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRepository = (UserRepositoryImpl) getServletContext().getAttribute("userRepository");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/security/signin.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username != null && password != null) {
            User user = userRepository.getUserByUsernameAndPassword(username,password);
            if(user == null) {
                request.setAttribute("message","Wrong pair username-password");
            } else {
                userService.auth(user,request,response);
//                    response.sendRedirect(getServletContext().getContextPath() + "/");
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/security/signin.jsp").forward(request,response);

    }
}
