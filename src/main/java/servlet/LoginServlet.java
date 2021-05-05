package servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import core.login.*;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
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
			request.setAttribute("message", "Неправильный логин или пароль");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} 
		// Успешная автоизация
		else {
			request.getSession().setAttribute("logged", true);
			// В зависимости от типа аккаунта получаем нужную панель
			if (account.isAdmin()) {
				request.getRequestDispatcher("/admin").forward(request, response);
			} else {
				request.getRequestDispatcher("/calculator").forward(request, response);
			}
		}
	}
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
