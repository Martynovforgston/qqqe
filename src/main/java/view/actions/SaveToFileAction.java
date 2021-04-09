package view.actions;
import java.awt.event.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.JOptionPane;
import view.IMainFrame;

public class SaveToFileAction implements ActionListener {

	private IMainFrame frame;
	
	// В качестве конструктора принимаем интерфейс с интересующими нас методами
	public SaveToFileAction(IMainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			// Данные, которые будут занесены в файл
			List<String> info = Arrays.asList(
					"Район города: " + frame.getRegionName(),
					"Кол-во одностворчатых окон: " + frame.getCount1Leaf(),
					"Кол-во двустворчевых окон: " + frame.getCount2Leaf(),
					"Кол-во трехстворченных окон: " + frame.getCount3Leaf(),
					"Кол-во км м пола: " + frame.getCountM2Floor(),
					"Услуга мытья санузла: " + (frame.isBathroomOn() ? "включена" : "отсутствует"),
					"Использованный промокод: " + (frame.getPromo().equals("") ? "отсутствует" : frame.getPromo()),
					"Итого: " + frame.getResult() + " руб"
					);
			// Путь до файла (в данном случае, в той же директории)
			Path file = Paths.get("VARIANT21.txt");
			// Построчная запись данных в файл
			Files.write(file, info);
		} catch (Exception exp) {
			JOptionPane.showMessageDialog(null, "При попытке сохранения данных произошла ошибка " + exp.getLocalizedMessage());
		}
	}
}
