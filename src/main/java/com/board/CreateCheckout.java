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
 * Servlet implementation class Publish
 */
@WebServlet(name = "publish", urlPatterns = { "/create_checkout" })
public class CreateCheckout extends HttpServlet {
	
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCheckout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
        
        //create_checkout.jsp		
		String name = request.getParameter("name");
		String headline = request.getParameter("headline");
		String rubric = request.getParameter("rubric");
		String text = request.getParameter("text");
		
		AdDao adDao;
		
		if (name == null || headline == null || rubric == null || text == null) { 
			request.setAttribute("error", "true");
		}
		else {
			
			adDao = new AdDao();
			int genId = adDao.create(name, headline, rubric, text);
			if (genId > 0) {
				request.setAttribute("published", "true");
				request.setAttribute("genId", genId);
				//out.println("<H2>Спасибо, " + name + "! Ваше объявление (№" + genId + ") успешно опубликовано! </H2>");
			}
			else {
				request.setAttribute("published", "false");
				//out.println("<H2>Попробуйте еще раз, " + name + "! К сожалению, Ваше объявление не было опубликовано. <H2>");
			}
				
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create_checkout.jsp");
        dispatcher.forward(request, response);
		
	}

}
