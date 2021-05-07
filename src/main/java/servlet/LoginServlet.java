package servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.login.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/", ""})
public class LoginServlet extends HttpServlet {
	
	private LoginController controller;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Инициализируем класс контроллера
		this.controller = new LoginController();
		
		// Получаем параметры из формы
		String username = request.getParameter("login");
		String password = request.getParameter("password");
		
		// Пытаемся авторизоваться
		Account account = controller.login(username, password);
		
		// Не успешная авторизация
		if (account == null) { 
			request.setAttribute("message", "Неверный логин или пароль");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		// Успешная авторизация
		else {
			request.getSession().setAttribute("logged", true);
			request.getSession().setAttribute("account", account);
			// В зависимости от типа аккаунта получаем нужную панель
			if (account.isAdmin()) {
				response.sendRedirect(request.getContextPath() + "/admin");
			} else {
				response.sendRedirect(request.getContextPath() + "/calculator");
			}
		}
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
