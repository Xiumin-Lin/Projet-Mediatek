package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet used to disconnect the user's current session if it exists
 */
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Return current session if exists, else return null, it will not create a new session
		HttpSession session = request.getSession(false);
		if(session != null)
			session.invalidate(); //delete session
		response.sendRedirect("./index.jsp");
	}
}
