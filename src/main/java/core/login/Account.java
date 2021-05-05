package core.login;

public class Account {
	
	private String username;
	private String password;
	private boolean isAdmin;
	
	public Account(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean isAdmin() {
		return this.isAdmin;
	}
	
	@Override
	public String toString() {
		return this.username + "/" + this.password + "/" + Boolean.toString(this.isAdmin);
	}
}
