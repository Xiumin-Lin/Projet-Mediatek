package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mediatek2021.Mediatek;
import mediatek2021.Utilisateur;


public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		String login = request.getParameter("login");
    String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		Mediatek mediatek = Mediatek.getInstance();
		Utilisateur user = mediatek.getUser(login,password); //TODO save in session
		if(user == null) {
			request.setAttribute("userNotFound",true);
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}
		response.sendRedirect("./mediatek.jsp");
	}

}
