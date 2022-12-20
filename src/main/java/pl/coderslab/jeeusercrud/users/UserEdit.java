package pl.coderslab.jeeusercrud.users;

import pl.coderslab.jeeusercrud.dao.UserDao;
import pl.coderslab.jeeusercrud.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/users/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if (Objects.nonNull(id)) {
            int userId = Integer.parseInt(id);
            User user = UserDao.read(userId);
            req.setAttribute("user", user);
        }
        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = UserDao.read(Integer.parseInt(id));
        req.setAttribute("user", user);

        if (Objects.nonNull(id)) {
            if (!username.equals(user.getUserName()) || !email.equals(email)) {
                if (UserDao.checkUserName(username) || UserDao.checkEmail(email)) {
                    if (!username.equals(user.getUserName()) && !email.equals(user.getEmail())) {
                        req.setAttribute("errorName", username + " login is already taken !!!");
                        req.setAttribute("errorEmail", email + "Email is already taken !!!");
                        req.setAttribute("user", user);
                        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(req, resp);
                    } else if (UserDao.checkUserName(username)) {
                        req.setAttribute("errorName", username + " login is already taken !!!");
                        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(req, resp);
                    } else {
                        if (!email.equals(user.getEmail())) {
                            req.setAttribute("errorEmail", email + " Email is already taken !!!");
                            getServletContext().getRequestDispatcher("/users/edit.jsp").forward(req, resp);
                        }
                    }
                }
            } else {
                user.setUserName(username);
                user.setEmail(email);
                user.setPassword(password);
                UserDao.update(user);
                //jak to jest zrobione w coderslab
                resp.sendRedirect("/users/list");
            }
        }
    }
}
