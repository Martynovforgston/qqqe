package core.utils;

import java.io.File;

import core.settings.Property;
import core.settings.SettingsManager;

public class Helper {

	// Метод для получения коэффициента района
	public static double getRegionCoeff(String name, SettingsManager settings) {
		
		Property coeff = settings.find(name);
		
		if (coeff != null)
			return (double)coeff.getValue();
		
		return 1.0;
	}
	
	public static boolean TryCreateFile(String path)
	{
		try {
			File file = new File(path);
			// Если файл не существует, то создаем
			if (!file.exists())
			{
				file.createNewFile();
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static int parseInt(String text) {
		try {
		    return Integer.parseInt(text);
		  } catch (Exception e) {
		    return 0;
		  }
	}
	
	public static String parseString(String text) {
		if (text != null) {
			return text;
		} else {
			return "";
		}
	}
	
	public static boolean parseBool(String text) {
		try {
			return Boolean.parseBoolean(text);
		} catch(Exception ex) {
			return false;
		}
	}
	
	public static double parseDouble(String text) {
		try {
			return Double.parseDouble(text);
		} catch(Exception ex) {
			return 0.0;
		}
	}
}
