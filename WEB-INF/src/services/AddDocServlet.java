package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Mediatek;
import mediatek2021.NewDocException;

public class AddDocServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Object> docData = new ArrayList<>();
		
		int type = -1;
		
		String descrip = request.getParameter("description");
		String title = request.getParameter("label");
		docData.add(descrip);
		docData.add(title);
		
		try {
			type = Integer.parseInt(request.getParameter("type"));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Parse Int Error : " + e.getMessage());
		}

		Mediatek mediatek = Mediatek.getInstance();
		
		switch(type) {
			case 1: 
				String author = request.getParameter("author");
				String nbPages = request.getParameter("pages");
				docData.add(author);
				docData.add(nbPages); break;
			case 2: 
				String director = request.getParameter("director");
				String release_date = request.getParameter("release_date");
				String duration = request.getParameter("duration"); 
				docData.add(director);
				docData.add(release_date);
				docData.add(duration); break;
			case 3: 
				 break;
			default:
				request.setAttribute("docIsCreated", false);
		}
		
		try {
			mediatek.newDocument(type, docData.toArray());
			request.setAttribute("docIsCreated", true);
		} catch (NewDocException e) {
			e.printStackTrace();
			System.err.println("Fail to add document : " + e.getMessage());
		}
		request.getRequestDispatcher("./mediatek.jsp").forward(request, response);
	
	}
}
