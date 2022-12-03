package ru.kpfu.itis.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.services.UserService;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    private static final String[] SecuredPaths = new String[] {"/flat/create"};

    private UserService userService;

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean prot = false;
        for (String path : SecuredPaths) {
            if (path.equals(req.getRequestURI().substring(req.getContextPath().length()))) {
                prot = true;
                break;
            }
        }
        if (prot && !userService.isNonAnonymous(req, res)) {
            res.sendRedirect(req.getContextPath() + "/signin");
            return;
        } else {
            if (userService.isNonAnonymous(req, res)) {
                req.setAttribute("user", userService.getUser(req, res));
            }
            chain.doFilter(req, res);
        }
    }
}
