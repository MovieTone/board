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
 * Servlet implementation class Delete
 */
@WebServlet(name = "delete", urlPatterns = { "/delete" })
public class Delete extends HttpServlet {
     
	private HttpSession session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

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
        
        //delete.jsp
		int id = 0;
		String idString = request.getParameter("id");
		if (idString != null)
			id = Integer.parseInt(idString);
		
		AdDao adDao;
		Ad ad;
		
		if(currentUser != null) {
			
			adDao = new AdDao();
			ad = adDao.get(id);
			if (id == 0 || ad == null || !currentUser.equals(ad.getName())) {
				request.setAttribute("havingAccess", "false");
				//Вы не можете удалить это объявление. 
			} 
			else { 
				request.setAttribute("havingAccess", "true");
				if (id == 0) { 
					request.setAttribute("error", "true");
					//<H2>Ошибка.</H2>	
				}
				else { 
					request.setAttribute("error", "false");
					adDao = new AdDao();
					boolean isEdited = adDao.delete(id);
					request.setAttribute("adName", ad.getName());
					if (isEdited) {
						request.setAttribute("isEdited", "true");
						//out.println("<H2>Спасибо, " + ad.getName() + "! Ваше объявление (№" + id + ") успешно удалено! </H2>");
					}
					else{
						request.setAttribute("isEdited", "false");
						//out.println("<H2>Попробуйте еще раз, " + ad.getName() + "! К сожалению, Ваше объявление (№" + id + ") не было удалено. <H2>");
					}
				}
			}
		}
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("/delete.jsp");
        dispatcher.forward(request, response);
        
	}

}
