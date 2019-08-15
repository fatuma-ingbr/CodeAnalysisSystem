package Controllers;

import DB.RegisterDatabase;
import model.RegisterObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(urlPatterns = "/register.do")
public class RegisterServlet extends HttpServlet {

    private RegisterDatabase registerDatabase = new RegisterDatabase();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try{
            String hash = HashService.createHash(password);
            registerDatabase.addHashedPass(new RegisterObject(email,hash));

        } catch (InvalidKeySpecException e) {
            System.out.println(e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/login.do");
    }
}
