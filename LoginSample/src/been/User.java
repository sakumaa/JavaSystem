package been;

import java.io.Serializable;

public class User implements Serializable {
	private String user_id;
	private String user_name;
	private String password;

	public User() {}
	public User(String userId, String userName, String password) {
		this.user_id = userId;
		this.user_name = userName;
		this.password = password;
	}

	public String getId() {return user_id;}
	public String getName() {return user_name;}
	public String getPass() {return password;}

	public void setId(String userId) {this.user_id = userId;}
	public void setName(String userName) {this.user_name = userName;}
	public void setPass(String password) {this.password = password;}
}
