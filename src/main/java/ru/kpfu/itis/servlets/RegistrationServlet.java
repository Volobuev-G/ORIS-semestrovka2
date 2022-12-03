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
import java.net.URLEncoder;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/WEB-INF/views/security/registration.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(email != null && password != null && username != null) {
            if(!email.contains("@")) {
                request.setAttribute("message","Email must contain @");
            } else if (userRepository.emailExist(email)) {
                request.setAttribute("message","This email is already taken");
            } else if (userRepository.usernameExist(username)) {
                request.setAttribute("message","This username is already taken");
            } else {
                User user = new User(email,username,password,"client");
                userService.auth(user,request,response);
                userRepository.save(user);
                response.sendRedirect("/flats/profile");
                return;
            }
        } else {
            request.setAttribute("message","You have to fill all fields");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/security/registration.jsp").forward(request,response);
    }
}
