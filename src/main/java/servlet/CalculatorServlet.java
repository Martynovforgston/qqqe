package servlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.swing.JOptionPane;

import core.calculator.*;
import core.settings.SettingsManager;
import core.utils.Helper;

@WebServlet(name = "CalculatorServlet", urlPatterns = "/calculator")
public class CalculatorServlet extends HttpServlet {
	
	SettingsManager settings;
	ICalculator calculator;
	Promo promo;
	
	@Override
	public void init() throws ServletException {
		calculator = new Calculator21();
		promo = new Promo21();
		settings = new SettingsManager();
		settings.load();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Пользователь не авторизован
		if (request.getSession().getAttribute("logged") == null || !(Boolean)request.getSession().getAttribute("logged")) {
			System.out.println("Not logged (POST)");
			request.removeAttribute("message");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// Пользователь нажал на кнопку расчета
		if (request.getParameter("calculate") != null){
			// Получаем данные из формы
			int count1leaf = Helper.parseInt(request.getParameter("1Leaf"));
			int count2leaf = Helper.parseInt(request.getParameter("2Leaf"));
			int count3leaf = Helper.parseInt(request.getParameter("3Leaf"));
			int countM2 = Helper.parseInt(request.getParameter("Floor"));
			
			boolean isOn = Helper.parseBool(request.getParameter("on"));
			
			String promoValue = Helper.parseString(request.getParameter("promo"));
			String region = Helper.parseString(request.getParameter("district"));
			
			double rcoeff = Helper.getRegionCoeff(region, settings);
			double pcoeff = promo.getCoeff(promoValue);
			// Делаем расчет
			double result = calculator.calculate(rcoeff, pcoeff, count1leaf, count2leaf, count3leaf, countM2, isOn);
			// Обновляем форму
			request.setAttribute("count1leaf", count1leaf);
			request.setAttribute("count2leaf", count2leaf);
			request.setAttribute("count3leaf", count3leaf);
			request.setAttribute("countM2", countM2);
			request.setAttribute("isOn", isOn);
			request.setAttribute("result", (Math.round(result * 100)) / 100);
			// Выводим результат
			request.getRequestDispatcher("calculator.jsp").forward(request, response);
		}
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET");
		// Пользователь не авторизован
		if (request.getSession().getAttribute("logged") == null || !(Boolean)request.getSession().getAttribute("logged")) {
			request.removeAttribute("message");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} 
		else {
			String action = Helper.parseString(request.getParameter("actionToDo"));
			Double result = Helper.parseDouble(request.getParameter("result"));
			System.out.println("Action is " + action + " and result is " + result.toString());
			// Пользователь выбрал пункт меню сохранения файла
			if (action.equals("saveToFile") && result > 0) {
				System.out.println("Saving to file with result = " + result.toString());
				try {
	    			// Данные, которые будут занесены в файл
	    			List<String> info = Arrays.asList(
	    					"Итого: " + result + " руб"
	    					);
	    			// Путь до файла (в данном случае, в той же директории)
	    			Path file = Paths.get("results.txt");
	    			// Построчная запись данных в файл
	    			Files.write(file, info);
	    		} catch (Exception exp) {
	    			System.out.println(exp.getMessage());
	    			request.setAttribute("message", "Не удалось сохранить файл");
	    		}
			}
			
			request.getRequestDispatcher("/calculator.jsp").forward(request, response);
	    }
    }
}
