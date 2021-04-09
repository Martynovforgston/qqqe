package view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import observer.EventManager;

public class CalculateAction implements ActionListener {

	private EventManager events;
	
	public CalculateAction(EventManager events) {
		this.events = events;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Оповещаем о том, что нажата кнопка расчета
		this.events.notify("calculate");
	}
}
