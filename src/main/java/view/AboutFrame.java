package view;

import java.awt.Container;
import java.awt.Font;

import javax.swing.*;

public class AboutFrame extends JFrame {
	
	private Container container; 
    private JLabel aboutLbl;
	
	public AboutFrame() {
		
		setTitle("О программе"); 
        setBounds(40, 20, 300, 340); 
        setResizable(false); 
  
        container = getContentPane(); 
        container.setLayout(null); 
        
        String textAbout = 
        		"<html>Калькулятор клининговых услуг для квартир – "
        		+ "это программа, которая может быть "
        		+ "использована заинтересованным лицом для расчета "
        		+ "стоимости разовой уборки квартиры.<br><br>"
        		+ "(C) ООО \"ВАРИАНТ 21\", 2020. Все права защищены.</html>";
        String textAbout1 = 
        		"<html>Информация о команде разработки: <br>"
        		+ "Мартынов Владислав Евгеньевич 0162 <br>"
        		+ "Абизгильдина Диана Равилевна 0641  <br> "
        		+ "Гамбаров Денис Ильдусович 0343  <br>"
        		+ "Латыпова Эльза Мансафовна 0170</html>";
        
        aboutLbl = new JLabel(textAbout);
        aboutLbl.setFont(new Font("Arial", Font.PLAIN, 14)); 
        aboutLbl.setSize(250, 200); 
        aboutLbl.setLocation(15, -10); 
        container.add(aboutLbl); 
        
        aboutLbl = new JLabel(textAbout1);
        aboutLbl.setFont(new Font("Arial", Font.PLAIN, 14)); 
        aboutLbl.setSize(300, 250); 
        aboutLbl.setLocation(15, +100); 
        container.add(aboutLbl); 
        
        setVisible(true);
	}
	
}