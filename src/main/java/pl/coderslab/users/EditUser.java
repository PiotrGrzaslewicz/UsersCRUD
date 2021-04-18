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

@WebServlet("/edituser")
public class EditUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = UserDAO.read(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("user",user);
        getServletContext().getRequestDispatcher("/users/edituser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = UserDAO.read(Integer.parseInt((req.getParameter("id"))));
        user.setUserName(req.getParameter("newUserName"));
        user.setEmail(req.getParameter("newEmail"));
        user.setPassword(UserDAO.hashPassword(req.getParameter("newPassword")));
        try {UserDAO.update(user);} catch (InvalidEmailException e) {}
        resp.sendRedirect("/userlist");
    }
}
