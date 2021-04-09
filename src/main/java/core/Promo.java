package core;

public abstract class Promo {
	// Массив промокодов
	private String[] promos;
	
	public Promo(String[] promos) //конструктор
	{
		this.promos = promos;
	}
	
	// Абстрактный метод для получения коэффициента для скидки
	public abstract double getCoeff(String promo);
	
	// Вспомогательный метод для проверки корректности промокода, в интерфейсе нельзя было бы добавить
	protected boolean isValidPromo(String value) {
		// Проверка из массива промокодов
		for (String promo: promos)
			if (promo.equals(value))
				return true;
		
		return false;
	}
}
