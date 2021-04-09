package core;

public class Calculator_2 implements ICalculator { 
	
	public double calculate(double regionCoeff, double promoCoeff, int count1Leaf, int count2Leaf, int count3Leaf, int countM2Floor, boolean isBathroomOn) {
		
		// Включена ли услуга по мытью санузла
		int bathroomPrice = 0;
		if (isBathroomOn == true) bathroomPrice = 900;
		
		// Расчет стоимости по формуле
		double result = regionCoeff * promoCoeff * 
				(200 * count1Leaf + 
				 450 * count2Leaf + 
				 300 * count3Leaf + 
				 (50 * countM2Floor) + bathroomPrice);
		return result;
	}
}