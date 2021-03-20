package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Mediatek;
import mediatek2021.NewDocException;
import mediatek2021.Utilisateur;

public class AddDocServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//check if user exist & is a admin otherwise send to index page
		HttpSession session = request.getSession(true);
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		if(user == null) {
			request.setAttribute("serviceNoAllow", "User impossible to identify, please login !");
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		} else if((Boolean) user.data()[4] == false) {
			request.setAttribute("serviceNoAllow", "User not allowed to add a document, make sure you are an librarian !");
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}

		List<String> docData = new ArrayList<>();
		
		int docType = -1;

		docData.add(request.getParameter("label"));
		docData.add(request.getParameter("description"));
		
		try {
			docType = Integer.parseInt(request.getParameter("type"));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Parse Int Error : " + e.getMessage());
		}

		Mediatek mediatek = Mediatek.getInstance();
		
		switch(docType) {
			case 1: 
				docData.add(request.getParameter("author"));
				docData.add(request.getParameter("pages")); break;
			case 2: 
				docData.add(request.getParameter("director"));
				docData.add(request.getParameter("release_date"));
				docData.add(request.getParameter("duration")); break;
			case 3: 
				docData.add(request.getParameter("artist")); break;
			default:
				request.setAttribute("createStatus", "Fail : the type ID " + docType + " is invalid");
				request.getRequestDispatcher("./mediatek.jsp").forward(request, response);
		}
		
		try {
			for(int i=0; i<docData.size(); i++){
				String trimStr = docData.get(i).trim();
				docData.set(i, (trimStr.length() > 0) ? trimStr : null);
			}
			mediatek.newDocument(docType, docData.toArray());
			request.setAttribute("createStatus", "Document '" + request.getParameter("label") + "' has been created !");
		} catch (NewDocException e) {
			e.printStackTrace();
			request.setAttribute("createStatus", "Fail to add document : " + e.getMessage());
		}
		request.getRequestDispatcher("./mediatek.jsp").forward(request, response);
	
	}
}
