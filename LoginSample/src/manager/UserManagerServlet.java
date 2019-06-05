package manager;

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
 * Servlet implementation class UserManagerServlet
 */
@WebServlet("/UserManagerServlet")
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String baseId = request.getParameter("base_id");
		String baseName = request.getParameter("base_name");
        String submit = request.getParameter("submit");
		String userId = request.getParameter("user_id");
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        boolean isSubmit = false;

        HttpSession session = request.getSession();

        try {
            UserDao dao = new UserDao();
            User loginUser = dao.getLoginUser(userId, password);

            if(submit.equals("登録")) {
            	isSubmit = true;
            	if(userId.length() == 0 || userName.length() == 0 || password.length() == 0) {
            		request.setAttribute("msg", "※ 必要な情報を入力してください！");
            	}else {
            		if(dao.addNewUser(userId, userName, password) > 0) {
            			request.setAttribute("msg", "ユーザーを新規登録しました！");
            		}else {
            			request.setAttribute("msg", "※ ユーザーの新規登録に失敗しました…");
            		}
            	}
            }else if(submit.equals("更新")) {
            	isSubmit = true;
            	if(userId.length() == 0 || userName.length() == 0) {
            		request.setAttribute("msg", "※ ユーザーID・ユーザー名は必須です");
            	}else {
            		if(baseId.equals(userId)) {
            			userId = "";
            		}
            		if(baseName.equals(userName)) {
            			userName = "";
            		}

            		if(dao.updUser(baseId, userId, userName, password) > 0) {
            			request.setAttribute("msg", "ユーザーの更新が完了しました！");
            		}else {
            			request.setAttribute("msg", "※ ユーザーの更新に失敗しました…");
            		}
            	}
            }else if(submit.equals("削除")) {
            	isSubmit = true;
            	if(dao.delUser(baseId) > 0) {
        			request.setAttribute("msg", "ユーザーを削除しました");
        		}else {
        			request.setAttribute("msg", "※ ユーザーが削除できませんでした…");
        		}
            }

            if(isSubmit) {
                List<User> users = dao.getUsers();
                request.setAttribute("users", users);

                RequestDispatcher dispatch = null;
                dispatch = request.getRequestDispatcher("LoginOK.jsp");
                dispatch.forward(request, response);
            }else {
            	session.invalidate();
            	doGet(request, response);
            }


        } catch(SQLException e_sql) {
            e_sql.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {

        }
	}

}
