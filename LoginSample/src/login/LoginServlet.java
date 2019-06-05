package login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import been.User;
import dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = null;
        dispatch = request.getRequestDispatcher("Login.jsp");
        dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("user_id");
        String password = request.getParameter("password");
        String submit = request.getParameter("submit");

        try {
            UserDao dao = new UserDao();
            User loginUser = dao.getLoginUser(userId, password);
            List<User> users = dao.getUsers();

            if(submit.equals("ログイン")) {
                HttpSession session = request.getSession();
                session.setAttribute("login_user", loginUser);
                request.setAttribute("users", users);

                RequestDispatcher dispatch = null;
                if (loginUser != null) {
                    dispatch = request.getRequestDispatcher("LoginOK.jsp");
                    dispatch.forward(request, response);
                } else {
                    dispatch = request.getRequestDispatcher("LoginNG.jsp");
                    dispatch.forward(request, response);
                }
            }
        } catch(SQLException e_sql) {
            e_sql.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {

        }
	}

}
