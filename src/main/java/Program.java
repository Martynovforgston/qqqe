import core.Calculator21;
import core.Promo21;
import presenter.MainPresenter;
import view.MainFrame;

public class Program {

	public static void main(String[] args) {
		
		// Конкретные классы с реализациями
		MainFrame frame = new MainFrame();
		Calculator21 calculator = new Calculator21();
		Promo21 promo = new Promo21();
		
		new MainPresenter(frame, calculator, promo);
	}

}
