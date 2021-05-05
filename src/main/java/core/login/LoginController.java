package core.login;

import java.util.ArrayList;
import java.util.List;

public class LoginController {

	private List<Account> accounts;
	
	public LoginController() {
		this.accounts = new ArrayList<Account>();
		this.accounts.add(new Account("user", "user", false));
		this.accounts.add(new Account("admin", "admin", true));
	}
	
	public Account login(String username, String password) {
		for (Account account: this.accounts) {
			if (account.getUsername().equals(username) & account.getPassword().equals(password)) {
				return account;
			}
		}
		
		return null;
	}
	
}
