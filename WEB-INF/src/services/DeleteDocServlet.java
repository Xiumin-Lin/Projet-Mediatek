package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Document;
import mediatek2021.Mediatek;
import mediatek2021.SuppressException;
import mediatek2021.Utilisateur;

public class DeleteDocServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Object DELETEKEY = new Object();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Check if user exist & is a admin, else send to index page
		HttpSession session = request.getSession(true);
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		if(user == null) {
			request.setAttribute("serviceNoAllow", "User impossible to identify, please login !");
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		} else if((Boolean) user.data()[4] == false) {
			request.setAttribute("serviceNoAllow", "User not allowed to delete a document, make sure you are an librarian !");
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}

		int docIdToDelete = -1;

		try {
			docIdToDelete = Integer.parseInt(request.getParameter("deleteDocID"));
		} catch (Exception e) {
			System.err.println("Parse Int Error : " + e.getMessage());
		}

		if(docIdToDelete < 0) {
			request.setAttribute("deleteStatus", "The ID should be a positive number");
		} else {
			// Check if the document exists in the database
			Mediatek mediatek = Mediatek.getInstance();
			synchronized (DELETEKEY) {
				Document doc = mediatek.getDocument(docIdToDelete);
				// If the document does not exist then it will set the attribute deleteStatus with a error message, 
				// else it will delete the document using its ID
				if(doc == null) {
					request.setAttribute("deleteStatus", "Document not found for ID : " + docIdToDelete);
				} else {
					//check if the doc is borrowed, if true, admin can't delete the doc
					if((int) doc.data()[3] > 0) {
						request.setAttribute("deleteStatus", "Can't Delete the document " + docIdToDelete + ", it's borrowed by the user ID : " + doc.data()[3]);
					} else {
						try {
							mediatek.suppressDoc(docIdToDelete);
							request.setAttribute("deleteStatus", "Document deleted successfully");
						} catch (SuppressException e) {
							e.printStackTrace();
							request.setAttribute("deleteStatus", "Delete Fail : " + e.getMessage());
						}
					}
				}
			}
		}
		//return to the mediatek page indicating the status of the deletion
		request.getRequestDispatcher("./mediatek.jsp").forward(request, response);
	}
}
