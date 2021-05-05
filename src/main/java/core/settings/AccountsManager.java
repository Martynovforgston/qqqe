package core.settings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import core.login.*;
import core.utils.Helper;

public class AccountsManager extends SettingsBase<PropertyGeneric<Account>> {

	private String filePath = "accounts.txt";
	
	@Override
	public boolean load() {

		if (!Helper.TryCreateFile(filePath))
			return false;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

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
		// ѕытаемс€ создать файл, если его не существует
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
