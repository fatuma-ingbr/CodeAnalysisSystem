package Controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/login.do")
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(req.getSession().getAttribute("email")!= null){
            filterChain.doFilter(req, res);
        }else {
            req.setAttribute("loginError", "Enter a valid name or password to log in.");
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, res);
        }

    }

    @Override
    public void destroy() {

    }
}
