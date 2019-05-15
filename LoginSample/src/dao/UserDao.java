package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.media.jfxmediaimpl.MediaDisposer.Disposable;

import been.User;

public class UserDao implements Disposable {

	private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

	public UserDao() throws SQLException {

        try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
//	        con = DriverManager.getConnection(
//	                "jdbc:mysql://javasystem-demo.cwcncfovruyw.us-east-2.rds.amazonaws.com:3306/javasystem","root","javasystem");
	        con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/javasystem","root","root");

		} catch (InstantiationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	public User getLoginUser(String userId, String password) throws SQLException {
		PreparedStatement ps = con.prepareStatement(
		        "select user_id, user_name, password from user where user_id = ? and password = ?");
        ps.setString(1, userId);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        User loginUser = null;
        while(rs.next()) {
        	loginUser = new User(rs.getString("user_id"), rs.getString("user_name"), rs.getString("password"));
        }

    	return loginUser;
    }

	public List<User> getUsers() throws SQLException {
		List<User> users = new ArrayList<>();

		PreparedStatement ps = con.prepareStatement(
		        "select user_id, user_name, password from user");
		ResultSet rs = ps.executeQuery();

        while(rs.next()) {
        	users.add(new User(rs.getString("user_id"), rs.getString("user_name"), rs.getString("password")));
        }

    	return users;
    }

	public void dispose() {
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
