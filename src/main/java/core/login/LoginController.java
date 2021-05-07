package core.login;

import core.settings.AccountsManager;
import core.settings.PropertyGeneric;

public class LoginController {

	private AccountsManager accounts;
	
	public LoginController() {
		this.accounts = new AccountsManager();
		this.accounts.load();
	}
	
	public Account login(String username, String password) {
		for (PropertyGeneric<Account> account: this.accounts.getAll()) {
			Account instance = account.getValue();
			if (instance.getUsername().equals(username) & instance.getPassword().equals(password)) {
				return instance;
			}
		}
		return null;
	}
	
}
