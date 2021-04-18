package pl.coderslab.users;

import pl.coderslab.User;
import pl.coderslab.UserDAO;
import pl.coderslab.utils.InvalidEmailException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adduser")
public class AddUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/users/adduser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUserName(req.getParameter("userName"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        try {UserDAO.create(user);} catch (InvalidEmailException e) {};
     // getServletContext().getRequestDispatcher("/userlist").forward(req,resp);
        resp.sendRedirect("/userlist");

    }
}
