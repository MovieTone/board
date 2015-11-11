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
 * Servlet implementation class Edit
 */
@WebServlet(name = "edit", urlPatterns = { "/edit" })
public class Edit extends HttpServlet {
	
	private HttpSession session;

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
        
        //edit.jsp
		int id = 0;
		String idString = request.getParameter("id");
		if (idString != null)
			id = Integer.parseInt(idString);
		
		AdDao adDao;
		Ad ad;
		
		if (currentUser != null) {
			
			adDao = new AdDao();
			ad = adDao.get(id);
			if (id == 0 || ad == null || !currentUser.equals(ad.getName())) {
				request.setAttribute("havingAccess", "false");
				//Вы не можете изменить это объявление.
			} 
			else {
				request.setAttribute("havingAccess", "true");
				request.setAttribute("adId", ad.getId());
				request.setAttribute("currentUser", currentUser);
				request.setAttribute("adHeadline", ad.getHeadline());
				request.setAttribute("adRubric", ad.getRubric());
				request.setAttribute("adText", ad.getText());
				
				String rubricValue = ad.getRubric();
				String[] rubricsDefault = {"<option value = \"Продажа\">Продажа</option>", "<option value = \"Покупка\">Покупка</option>", "<option value = \"Аренда\">Аренда</option>", 
											"<option value = \"Услуги\">Услуги</option>", "<option value = \"Знакомства\">Знакомства</option>", "<option value = \"etc\">etc</option>"};
				
				String[] rubricsSelected = {"<option selected value = \"Продажа\">Продажа</option>", "<option selected value = \"Покупка\">Покупка</option>", "<option selected value = \"Аренда\">Аренда</option>", 
											"<option selected value = \"Услуги\">Услуги</option>", "<option selected value = \"Знакомства\">Знакомства</option>", "<option selected value = \"etc\">etc</option>"};
				
				String[] rubrics = {"Продажа", "Покупка", "Аренда", "Услуги", "Знакомства", "etc"};
				
				int rubricId = 5;
				for (int i = 0; i < 6; i++){
					if (rubrics[i].equals(rubricValue)) {
						rubricId = i;
						break;
					}
				}
				
				for (int i = 0; i < 6; i++){
					if (rubricId == i)
						request.setAttribute("selectedRubric" + i, rubricsSelected[rubricId]);
					else
						request.setAttribute("selectedRubric" + i, rubricsDefault[i]);
				}
			}
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(request, response);
	}

}
