package pl.coderslab.jeeusercrud.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet("/login")
public class AdminLogin extends HttpServlet {

    private final static String USER = "admin";
    private final static String PASSWORD = "coderslab";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object username = req.getParameter("username");
        Object password = req.getParameter("password");

        if (Objects.nonNull(username) && Objects.nonNull(password)) {

            if (username.equals(USER) && password.equals(PASSWORD)) {

                Map<String, String> mapUserPass = new HashMap<>();
                mapUserPass.put((String) username, (String)password);

                HttpSession session = req.getSession();
                session.setAttribute("username", mapUserPass);
                session.setAttribute("user", username);
                resp.sendRedirect("/users/list");

            } else {
                req.setAttribute("error", "Wrong login or password!!!");
//                resp.sendRedirect("/login");
                getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);

            }
        }
    }

}
