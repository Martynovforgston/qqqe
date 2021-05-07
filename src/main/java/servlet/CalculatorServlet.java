package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.calculator.*;
import core.settings.SettingsManager;
import core.utils.Helper;

@WebServlet(name = "CalculatorServlet", urlPatterns = "/calculator")
public class CalculatorServlet extends HttpServlet {
	
	SettingsManager settings;
	ICalculator calculator;
	List<Promo> promos;
	
	@Override
	public void init() throws ServletException {
		calculator = new Calculator21();
		promos = new ArrayList<Promo>();
		promos.add(new Promo21());
		promos.add(new SuperPromo());
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
			
			boolean isOn = request.getParameter("on") != null;
			System.out.println(Boolean.toString(isOn));
			
			String promoValue = Helper.parseString(request.getParameter("promo"));
			String region = Helper.parseString(request.getParameter("district"));
			
			double rcoeff = Helper.getRegionCoeff(region, settings);
			double pcoeff = 1;
			
			// Проходимся по типам промокодов
			for(Promo promo: this.promos) {
				double coeff = promo.getCoeff(promoValue);
				// Если промокод дал скидку, сохраняем и выходим из цикла
				if (coeff < 1) {
					pcoeff = coeff;
					break;
				}
			}
			
			// Делаем расчет
			double result = calculator.calculate(rcoeff, pcoeff, count1leaf, count2leaf, count3leaf, countM2, isOn);
			// Обновляем форму
			request.setAttribute("count1leaf", count1leaf);
			request.setAttribute("count2leaf", count2leaf);
			request.setAttribute("count3leaf", count3leaf);
			request.setAttribute("countM2", countM2);
			request.setAttribute("isOn", isOn);
			request.setAttribute("on", isOn);
			request.setAttribute("promo", promoValue);
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
				// Данные, которые будут занесены в файл
    			List<String> info = Arrays.asList(
    					"Кол-во одностворчатых окон: " + Helper.parseString(request.getParameter("count1leaf")),
    					"Кол-во двустворчевых окон: " + Helper.parseString(request.getParameter("count2leaf")),
    					"Кол-во трехстворченных окон: " + Helper.parseString(request.getParameter("count3leaf")),
    					"Кол-во км м пола: " + Helper.parseString(request.getParameter("countM2")),
    					"Услуга мытья санузла: " + (Helper.parseBool(request.getParameter("isOn")) ? "включена" : "отсутствует"),
    					"Использованный промокод: " + (Helper.parseString(request.getParameter("promo")).equals("") ? "отсутствует" : Helper.parseString(request.getParameter("promo"))),
    					"Итого: " + result + " руб"
    			);
				try (PrintWriter writer = new PrintWriter("results.txt", "UTF-8")) {	
					for (String content: info) {
						writer.println(content);
					}
					writer.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
	    			request.setAttribute("message", "Не удалось сохранить файл");
				}
			}
			
			request.getRequestDispatcher("/calculator.jsp").forward(request, response);
	    }
    }
}
