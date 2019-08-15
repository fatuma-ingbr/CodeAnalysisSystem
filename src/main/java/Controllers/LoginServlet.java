package Controllers;

import DB.RegisterDatabase;
import model.LoginObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    HashService service = new HashService();
    private RegisterDatabase  registerDatabase = new RegisterDatabase();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean login = false;

        try{
            login = service.Validate(password,registerDatabase.authenticateUser(new LoginObject(email)));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if(login == true){
            request.getSession().setAttribute("email", email);
            response.sendRedirect("/fileupload.do");
        }else{
            request.setAttribute("loginError", "The password or email you have entered is incorrect. Please try again");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        }
    }
}
