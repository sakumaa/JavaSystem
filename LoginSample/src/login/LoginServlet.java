package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;


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
		String userId = request.getParameter("user_id");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(
                    "jdbc:mysql://javasystem-demo.cwcncfovruyw.us-east-2.rds.amazonaws.com:3306/javasystem","root","javasystem");
//            con = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/javasystem","root","");
            ps = con.prepareStatement(
                    "select user_name from user where user_id = ? and password = ?");
            ps.setString(1, userId);
            ps.setString(2, password);
            rs = ps.executeQuery();

            String userName = null;
            while(rs.next()) {
                userName = rs.getString("user_name");
            }

            RequestDispatcher dispatch = null;
            if (userName != null) {
                dispatch = request.getRequestDispatcher("LoginOK.jsp");
                dispatch.forward(request, response);
            } else {
                dispatch = request.getRequestDispatcher("LoginNG.jsp");
                dispatch.forward(request, response);
            }
        } catch(SQLException e_sql) {
            e_sql.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

}
