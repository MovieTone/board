package com.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	
	private HttpSession session;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Session initialization
		session = request.getSession();
		
		//Initialization of currentUser and loggedIn variables
		String currentUser = null;
		try { 
			currentUser = session.getAttribute("user").toString();
		}
		catch(NullPointerException e) {
			currentUser = null;
		}
		if (currentUser == null) {
			request.setAttribute("loggedIn", "false");
		}
		else { 
			request.setAttribute("loggedIn", "true");
		} 
        
        //login.jsp
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		
		UserDao userDao;
		
		userDao = new UserDao(); 
		if (userDao.isValid(user, password)) {
			session.setAttribute("user", user);
			response.sendRedirect("userads");
		}
		
		if (!(user == null || password == null))
		{
			userDao = new UserDao();
			if (userDao.isValid(user, password)) {
				request.setAttribute("isValid", "true");
				//<div><%= user %>, добро пожаловать!</div> 
			}
			else {
				request.setAttribute("isValid", "false");
				//<div class="error">Неверное имя пользователя или пароль. Проверьте правильность введенных данных.</div>
			} 
		}
		if (!response.isCommitted()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
	        dispatcher.forward(request, response);
		}
		
	}

}
