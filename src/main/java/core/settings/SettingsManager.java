package core.settings;

import java.io.*;
import core.utils.Helper;

public class SettingsManager extends SettingsBase<Property> {
	
	private String filePath = "settings.txt";
	
	public PropertyGeneric<Float> KalininskyCoeff = new PropertyGeneric<Float>("Kalininsky", 1.1);
	public PropertyGeneric<Float> DemskyCoeff = new PropertyGeneric<Float>("Demsky", 1.2);
	public PropertyGeneric<Float> KirovskyCoeff = new PropertyGeneric<Float>("Kirovsky", 1.1);
	public PropertyGeneric<Float> LeninskyCoeff = new PropertyGeneric<Float>("Leninsky", 1.1);
	public PropertyGeneric<Float> OctoberskyCoeff = new PropertyGeneric<Float>("Octobersky", 1.0);
	public PropertyGeneric<Float> OrdzhonikidzevskiyCoeff = new PropertyGeneric<Float>("Ordzhonikidzevskiy", 1.2);
	public PropertyGeneric<Float> SovetskiyCoeff = new PropertyGeneric<Float>("Sovetsky", 1.1);

	public SettingsManager() {
		// Добавляем свойства в список
		super.properties.add(KalininskyCoeff);
		super.properties.add(DemskyCoeff);
		super.properties.add(KirovskyCoeff);
		super.properties.add(LeninskyCoeff);
		super.properties.add(OctoberskyCoeff);
		super.properties.add(OrdzhonikidzevskiyCoeff);
		super.properties.add(SovetskiyCoeff);
	}

	@Override
	public boolean load() {
		// Файл не существует, загрузка невозможна
		if (!Helper.TryCreateFile(filePath))
			return false;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

			String line;

			while ((line = reader.readLine()) != null) {
				String[] data = line.split("=");

				if (data.length != 2)
					continue;
				
				Property property = super.find(data[0]);

				// Обновление существующей настройки
                if (property != null)
                {
                    if (property.getValue() instanceof Boolean)
                    {
                    	property.setValue(Boolean.parseBoolean(data[1]));
                    }
                    else if (property.getValue() instanceof Integer)
                    {
                    	property.setValue(Integer.parseInt(data[1]));
                    }
                    else if (property.getValue() instanceof Float)
                    {
                    	property.setValue(Float.parseFloat(data[1]));
                    }
                    else if (property.getValue() instanceof Double)
                    {
                    	property.setValue(Double.parseDouble(data[1]));
                    }
                    else if (property.getValue() instanceof String)
                    {
                    	property.setValue(data[1].toString());
                    }
                }
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
		// Пытаемся создать файл, если его не существует
		if (Helper.TryCreateFile(filePath)) {
			
			try (PrintWriter writer = new PrintWriter(filePath, "UTF-8")) {	
				
				for (Property property: super.properties) {
					writer.println(property.getName() + "=" + property.getValue());
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
