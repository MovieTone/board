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
 * Servlet implementation class EditCheckout
 */
@WebServlet(name = "edit_checkout", urlPatterns = { "/edit_checkout" })
public class EditCheckout extends HttpServlet {
	
	private HttpSession session;
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
        
        //edit_checkout.jsp
		if (currentUser != null) {
			
			int id = 0;
			String idString = request.getParameter("id");
			if (idString != null)
				id = Integer.parseInt(idString);
			String name = request.getParameter("name");
			String headline = request.getParameter("headline");
			String rubric = request.getParameter("rubric");
			String text = request.getParameter("text");
			
			AdDao adDao;
			
			if (id == 0 || name == null || headline == null || rubric == null || text == null) {
				request.setAttribute("error", "true");
				//<H2>Ошибка.</H2> <%	
			}
			else {
				adDao = new AdDao();
				boolean isEdited = adDao.edit(id, name, headline, rubric, text);
				request.setAttribute("adId", id);
				request.setAttribute("adName", name);
				if (isEdited) {
					request.setAttribute("isEdited", "true");
					//out.println("<H2>Спасибо, " + name + "! Ваше объявление (№" + id + ") успешно отредактировано! </H2>");
				}
				else{
					request.setAttribute("isEdited", "false");
					//out.println("<H2>Попробуйте еще раз, " + name + "! К сожалению, Ваше объявление (№" + id + ") не было отредактировано. <H2>");
				}
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_checkout.jsp");
        dispatcher.forward(request, response);
        
	}

}
