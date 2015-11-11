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
 * Servlet implementation class SignUp
 */
@WebServlet(name = "signup", urlPatterns = { "/signup" })
public class SignUp extends HttpServlet {

	private HttpSession session;
	UserDao userDao = null;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
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
        
        //signup.jsp
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		
		
		
		if (!(user == null || password == null)) {
			userDao = new UserDao(); 
			if (userDao.get(user) != null) {
				request.setAttribute("isUsed", "true");
				request.setAttribute("user", user);
				//response.sendRedirect("userads");
			}
			else {
				request.setAttribute("isUsed", "false");
				//storing to Database
				userDao = new UserDao();
				if (userDao.create(user, password) != null)
					request.setAttribute("signedUp", "true");
				else {
					request.setAttribute("signedUp", "false");
				}
			}
		}
		
		if (!response.isCommitted()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
	        dispatcher.forward(request, response);
		}
		
	}
	

}
