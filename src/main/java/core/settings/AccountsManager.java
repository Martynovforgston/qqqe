package core.settings;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import core.login.*;
import core.utils.Helper;

public class AccountsManager extends SettingsBase<PropertyGeneric<Account>> {

	private String filePath = "accounts.txt";
	
	private PropertyGeneric<Account> user = new PropertyGeneric<Account>("user", new Account("user", "user", false));
	private PropertyGeneric<Account> admin = new PropertyGeneric<Account>("admin", new Account("admin", "admin", true));
	
	public AccountsManager() {
		// Инициализируем настройки
		super.add(user);
		super.add(admin);
	}
	
	@Override
	public boolean load() {

		if (!Helper.TryCreateFile(filePath))
			return false;
		
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {

			String line;

			while ((line = reader.readLine()) != null) {
				// username/password/accountType
				String[] data = line.split("/");
				
				if (data.length != 3)
					continue;
				
				PropertyGeneric<Account> account = new PropertyGeneric<Account>(data[0], new Account(data[0], data[1], Boolean.getBoolean(data[2])));
				super.add(account);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean save() {
		// Если есть файл для сохранения
		if (Helper.TryCreateFile(filePath)) {
					
			try (PrintWriter writer = new PrintWriter(filePath, "UTF-8")) {	
						
				for (Property property: super.properties) {
					
					Object account = (Account)property.getValue();
					writer.println(account.toString());
				}
						
				writer.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
				
		return false;
	}

}
