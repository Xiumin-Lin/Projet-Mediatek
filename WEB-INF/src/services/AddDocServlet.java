package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDocServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = request.getParameter("label");
		String descrip = request.getParameter("description");
		String type = request.getParameter("type");
		
		switch(type) {
			case "1": 
				String author = request.getParameter("author");
				String nbPages = request.getParameter("pages"); break;
			case "2": 
				String director = request.getParameter("director");
				String release_date = request.getParameter("release_date");
				String duration = request.getParameter("duration"); break;
			case "3": break;
			default:
				request.setAttribute("userNotFound",true);
				request.getRequestDispatcher("./login.jsp").forward(request, response); 
		}
		
		
	}

}
