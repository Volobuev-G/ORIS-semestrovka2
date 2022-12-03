package ru.kpfu.itis.services;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.models.User;

public class UserService {
    public void auth(User user, HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("user",user);
    }

    public boolean isNonAnonymous(HttpServletRequest request, HttpServletResponse response){
        return request.getSession().getAttribute("user") != null;
    }

    public User getUser(HttpServletRequest req, HttpServletResponse res) {
        return (User) req.getSession().getAttribute("user");
    }
}
