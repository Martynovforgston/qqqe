package view;

import observer.EventManager;
import observer.IEventListener;
import view.actions.*;

import javax.swing.*; 
import java.awt.*; 

public class MainFrame extends JFrame implements IMainFrame {

	public EventManager events;
	
	// Компоненты формы
    private Container container; 
    
    private JMenuBar 	menuBar;
    private JMenu 		menuFile, menuRef;
    private JMenuItem	saveToFileItem, exitItem, aboutItem;
    
    private JLabel regionNameLbl;
    private JLabel count1LeafLbl;
    private JLabel count2LeafLbl;
    private JLabel count3LeafLbl;
    private JLabel countM2FloorLbl;
    private JLabel promoLbl;
    private JLabel resultLbl;
    
    private JTextField promoTxt;
    private JTextField resultTxt;
    
    private JComboBox<String> regionNameCB;
    
    private JSpinner count1LeafSpinner;
    private JSpinner count2LeafSpinner;
    private JSpinner count3LeafSpinner;
    private JSpinner countM2FloorSpinner;
    
    private JCheckBox isBathroomOnCheck;
    
    private JButton calculateBtn;
    
    private String regionNames[] = { "Калининский", "Демский", "Кировский", "Ленинский", "Октябрьский", "Орджоникидзевский", "Советский" }; 
  
    public MainFrame() 
    {
    	events = new EventManager("calculate");
    	
        setTitle("Калькулятор клининговых услуг для квартир");	// Задаем название формы 
        setBounds(300, 90, 525, 390); 							// Задаем размеры формы
        setDefaultCloseOperation(EXIT_ON_CLOSE); 				// Выход из программы при закрытии формы
        setResizable(false); 									// Размер формы неизменяем
        setLocationRelativeTo(null); 							// Местоположение формы по центру
  
        // Контейнер, в который будут помещены все компоненты формы
        container = getContentPane(); 
        container.setLayout(null); 
  
        // СОЗДАНИЕ МЕНЮ
        // Класс-контейнер для меню
        menuBar = new JMenuBar();  
        // Пункты меню
        menuFile = new JMenu("Файл");  
        menuRef  = new JMenu("Справка");
        // Подпункты пунктов меню
        saveToFileItem = new JMenuItem("Сохранить в файл");
        saveToFileItem.addActionListener(new SaveToFileAction(this));
        exitItem  = new JMenuItem("Выход");  
        exitItem.addActionListener(new ExitAction());
        aboutItem = new JMenuItem("О программе");
        aboutItem.addActionListener(new ShowAboutFrameAction());

        menuFile.add(saveToFileItem); 
        menuFile.add(exitItem); 
        menuRef.add(aboutItem);
        
        menuBar.add(menuFile); 
        menuBar.add(menuRef);
        
        // Задаем меню форме
        this.setJMenuBar(menuBar); 
        
        regionNameLbl = new JLabel("Район города"); 
        regionNameLbl.setBounds(20, 20, 100, 20);
        container.add(regionNameLbl); 
  
        // Создание списка с названиями регионов
        regionNameCB = new JComboBox<String>(regionNames); 
        regionNameCB.setBounds(110, 19, 190, 25);
        container.add(regionNameCB);  
  
        count1LeafLbl = new JLabel("Одностворчатых окон");
        count1LeafLbl.setBounds(20, 60, 150, 20);
        container.add(count1LeafLbl); 
  
        // Создание спиннера для указания кол-ва одностворчатых окон
        count1LeafSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 15, 1));
        count1LeafSpinner.setBounds(20, 80, 150, 25);
        container.add(count1LeafSpinner);
        
        count2LeafLbl = new JLabel("Двустворчатых окон");
        count2LeafLbl.setBounds(180, 60, 150, 20);
        container.add(count2LeafLbl); 
        
        // Создание спиннера для указания кол-ва двустворчатых окон
        count2LeafSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 15, 1));
        count2LeafSpinner.setBounds(180, 80, 150, 25);
        container.add(count2LeafSpinner);
        
        count3LeafLbl = new JLabel("Трехстворчатых окон");
        count3LeafLbl.setBounds(340, 60, 150, 20);
        container.add(count3LeafLbl); 
        
        // Создание спиннера для указания кол-ва трехстворчатых окон
        count3LeafSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 15, 1));
        count3LeafSpinner.setBounds(340, 80, 150, 25);
        container.add(count3LeafSpinner);
        
        countM2FloorLbl = new JLabel("Количество кв м пола");
        countM2FloorLbl.setBounds(20, 121, 170, 20);
        container.add(countM2FloorLbl); 
        
        // Создание спиннера для указания кол-ва кв м пола
        countM2FloorSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 3000, 1));
        countM2FloorSpinner.setBounds(155, 120, 335, 25);
        container.add(countM2FloorSpinner);
  
        // Создание чекбокс для услуги по мытью санузла
        isBathroomOnCheck = new JCheckBox("Включить услугу мытья санузла");
        isBathroomOnCheck.setBounds(20, 160, 250, 20);
        container.add(isBathroomOnCheck); 
        
        promoLbl = new JLabel("Промокод");
        promoLbl.setBounds(20, 200, 75, 20);
        container.add(promoLbl); 
        
        // Создание текстового поля для ввода промокода
        promoTxt = new JTextField();
        promoTxt.setBounds(90, 200, 150, 25);
        container.add(promoTxt);
        
        resultLbl = new JLabel("Стоимость");
        resultLbl.setBounds(20, 240, 75, 20);
        container.add(resultLbl); 
        
        // Создание текстового поля, куда будет помещен результат расчетов
        resultTxt = new JTextField("");
        resultTxt.setBounds(90, 240, 150, 25);
        resultTxt.setEditable(false);				// Запрет на редактирование поля пользователем
        container.add(resultTxt);
        
        // Создание кнопки для расчета стоимости
        calculateBtn = new JButton("Расчитать"); 
        calculateBtn.setBounds(20, 280, 470, 30);
        calculateBtn.addActionListener(new CalculateAction(this.events)); 	// Задаем обработчик события
        container.add(calculateBtn); 
        
        setVisible(true); 
    }
  
    // РЕАЛИЗАЦИЯ ИНТЕРФЕЙСА
    
    // Получение названия региона
	@Override
	public String getRegionName() {		
		return this.regionNameCB.getSelectedItem().toString();
	}
	
	// Получение промокода
	@Override
	public String getPromo() {		
		return this.promoTxt.getText();
	}
	
	// Получение значения количества одностворчатых окон
	@Override
	public int getCount1Leaf() { 		
		return (int)this.count1LeafSpinner.getValue();
	}

	// Получение значения количества двухстворчатых окон
	@Override
	public int getCount2Leaf() {		
		return (int)this.count2LeafSpinner.getValue();
	}

	// Получение значения количества трехстворчатых окон
	@Override
	public int getCount3Leaf() {		
		return (int)this.count3LeafSpinner.getValue();
	}
	
	// Получение значения количества кв.м пола
	@Override
	public int getCountM2Floor() {		
		return (int)this.countM2FloorSpinner.getValue();
	}

	// Включена ли услуга мытья санузла
	@Override
	public boolean isBathroomOn() { 	
		return this.isBathroomOnCheck.isSelected();
	}

	// Подписка на событие
	@Override
	public void subscribe(String eventType, IEventListener listener) { 
		this.events.subscribe(eventType, listener);
	}
	
	// Отписка от события
	@Override
	public void unsubscribe(String eventType, IEventListener listener) { 
		this.events.unsubscribe(eventType, listener);
	}

	@Override
	public void setResult(double price) {		
		// Округление значения
		price = Math.round(price * 100);
		price = price / 100;
		// Задаем значение в поле стоимости
		this.resultTxt.setText(Double.toString(price));
	}

	// Получение стоимости из поля стоимости
	@Override
	public double getResult() { 	
		try { return Double.parseDouble(this.resultTxt.getText()); }
		catch (Exception e) { return 0.0; }
	}
	
}
