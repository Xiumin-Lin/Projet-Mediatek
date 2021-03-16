package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Mediatek;
import mediatek2021.Utilisateur;

/**
 * Servlet using to check if information entered in the user login form is valid
 */
public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		//get the login & password provided by the user in the login form
		String login = request.getParameter("login");
    String password = request.getParameter("password");
		//check if the user exists in the database
		Mediatek mediatek = Mediatek.getInstance();
		Utilisateur user = mediatek.getUser(login,password);
		if(user == null) { 
			request.setAttribute("userNotFound",true);
			//return to login.jsp and indicates that the user is not valid
			request.getRequestDispatcher("./login.jsp").forward(request, response); 
		}
		//return current session, if it does not exist, then it will create a new session
		HttpSession session = request.getSession(true);
		session.setAttribute("user", user);
		//if user is a not a librarian, redirect to mediatek.jsp
		if(true) {
			request.getRequestDispatcher("./index.jsp").forward(request, response); 
		}
		response.sendRedirect("./mediatek.jsp");
	}

}
