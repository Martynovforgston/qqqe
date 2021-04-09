package view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.AboutFrame;

public class ShowAboutFrameAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Создаем форму "О программе"
		new AboutFrame();
	}
}
