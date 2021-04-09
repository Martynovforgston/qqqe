package core;

import java.util.HashMap;

public class Helper {

	// Метод для получения коэффициента района
	public static double getRegionCoeff(String name) {
		
		HashMap<String, Double> coeffs  = new HashMap<String, Double>() {{
		    put("Калининский", 			1.1);
		    put("Демский", 				1.2);
		    put("Кировский", 			1.1);
		    put("Ленинский", 			1.1);
		    put("Октябрьский", 			1.0);
		    put("Орджоникидзевский", 	1.2);
		    put("Советский", 			1.1);
		}};
		
		try {
			return coeffs.get(name); //действительно есть какой-то из районов
		}
		catch(Exception e) {  //при ошибке
			System.out.println("При получении коэффициента района произошла ошибка: " + e.getMessage());
		}
		
		return 1.0;
	}
}
