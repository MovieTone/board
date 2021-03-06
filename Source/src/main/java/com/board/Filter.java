package com.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Filter
 */
@WebServlet(name = "filter", urlPatterns = { "/filter" })
public class Filter extends HttpServlet {
	
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filter() {
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
        
        //filter.jsp
		List listAds;
		AdDao adDao;
		
		String rubric = request.getParameter("rubric");
		String author = request.getParameter("author");
		String onlyUser = request.getParameter("onlyUser");
		
		request.setAttribute("currentUser", currentUser);
		
		adDao = new AdDao();
		listAds = adDao.searchList(rubric, author, onlyUser, currentUser);
		List<AdDate> listAdsWithDates = new ArrayList<AdDate>();
		for(Iterator iterator = listAds.iterator(); iterator.hasNext();) {
			Ad ad = (Ad) iterator.next();
			Date date = new Date(ad.getTime() * 1000);
			AdDate adDate = new AdDate(ad.getId(), ad.getName(), ad.getHeadline(), ad.getRubric(), ad.getText(), date);
			listAdsWithDates.add(adDate);
		}
		request.setAttribute("listAdsWithDates", listAdsWithDates);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/filter.jsp");
        dispatcher.forward(request, response);
		
	}

}
