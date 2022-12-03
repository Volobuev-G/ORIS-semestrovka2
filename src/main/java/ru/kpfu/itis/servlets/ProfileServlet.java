package ru.kpfu.itis.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.UserRepositoryImpl;
import ru.kpfu.itis.services.UserService;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/WEB-INF/views/security/profile.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User oldUser = userService.getUser(request,response);
        Long id = userRepository.getUserId(oldUser.getEmail(),oldUser.getUsername(),oldUser.getPassword());

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(email != null && password != null && username != null) {
            if(!email.contains("@")) {
                request.setAttribute("message","Email must contain @");
            } else if (userRepository.emailExist(email) && (!Objects.equals(oldUser.getEmail(), email))) {
                request.setAttribute("message","This email is already taken");
            } else if (userRepository.usernameExist(username) && (!Objects.equals(oldUser.getUsername(), username)))  {
                request.setAttribute("message","This username is already taken");
            } else {
                User user = new User(email,username,password,"client");
                userRepository.update(user,id);
                userService.auth(user,request,response);
                response.sendRedirect("/flats/profile");
                return;
            }
        } else {
            request.setAttribute("message","You have to fill all fields");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/security/profile.jsp").forward(request,response);
    }
}

