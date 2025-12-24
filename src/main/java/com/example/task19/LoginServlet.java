package com.example.task19;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "password".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("username", username);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);

            response.sendRedirect("protected/home.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
