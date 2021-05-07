package servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.settings.Property;
import core.settings.SettingsManager;

import core.login.Account;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
	
	private SettingsManager settings;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Если пользователь не авторизован, отправляем его на страницу авторизацию
		if (request.getSession().getAttribute("logged") == null || !(Boolean)request.getSession().getAttribute("logged")) {
			request.setAttribute("message", "Сначало авторизуйтесь");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// Если пользователь авторизован, но его аккаунт не найден или не имеет админские привилегии
		else if (request.getAttribute("account") == null || ((Account)(request.getAttribute("account"))).isAdmin() == false) {
			request.setAttribute("message", "Недостаточно прав");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		System.out.println("ADMIN POST");
		
		settings = new SettingsManager();
		settings.load();
		// Пользователь нажал на кнопку сохранения файла
		if (request.getParameter("saveChange") != null) {
			for(String parameter : request.getParameterMap().keySet()) {
				// Вывод в консоль
				System.out.println(parameter + "=" + request.getParameter(parameter));
				// Если в настройках есть такой параметр
			    if (settings.find(parameter) != null) {
			    	// Задаем в настройки параметр с новым значением
			    	settings.set(parameter, request.getParameter(parameter));
			    	// Обновляем данные для формы
			    	request.setAttribute(parameter, request.getParameter(parameter));
			    }
			}
			// Сохраняем в файл
			settings.save();
			request.setAttribute("messageSuccess", "Изменения успешно сохранены");
		}
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		// Если пользователь не авторизован, отправляем его на страницу авторизацию
		if (req.getSession().getAttribute("logged") == null || !(Boolean)req.getSession().getAttribute("logged")) {
			req.removeAttribute("message");
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		// Если пользователь авторизован, но его аккаунт не найден или не имеет админские привилегии
		else if (req.getAttribute("account") == null || ((Account)(req.getAttribute("account"))).isAdmin() == false) {
			req.setAttribute("message", "Недостаточно прав");
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		System.out.println("ADMIN GET");
		settings = new SettingsManager();
		settings.load();
		for (Property property: settings.getAll()) {
			System.out.println(property.getName() + "=" + property.getValue());
			// Обновляем данные для формы
			req.setAttribute(property.getName(), property.getValue());
		}
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }
}
