package core.calculator;

public class SuperPromo extends Promo {
	// Список промокодов
	private static String[] promos = { "SUPER", "SUPERPI223" };
	
	public SuperPromo() {
		// Вызываем базовый конструктор
		super(promos);
	}
	
    @Override
	public double getCoeff(String promo) {
		// Если промо существует
		if (isValidPromo(promo))
			return 0.5;
		
		// Промо не существует
		return 1;
	}
}
