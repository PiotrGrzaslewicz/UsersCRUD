package pl.coderslab.users;

import pl.coderslab.User;
import pl.coderslab.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showuser")
public class ShowUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = UserDAO.read(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("user",user);
        getServletContext().getRequestDispatcher("/users/showuser.jsp").forward(req,resp);
     
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
}
