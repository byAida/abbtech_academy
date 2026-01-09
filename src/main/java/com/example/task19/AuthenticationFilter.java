package com.example.task19;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/protected/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            String usernameFromCookie = null;
            if (httpRequest.getCookies() != null) {
                for (javax.servlet.http.Cookie cookie : httpRequest.getCookies()) {
                    if ("username".equals(cookie.getName())) {
                        usernameFromCookie = cookie.getValue();
                        break;
                    }
                }
            }

            if (usernameFromCookie != null) {
                session = httpRequest.getSession(true);
                session.setAttribute("user", usernameFromCookie);
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}