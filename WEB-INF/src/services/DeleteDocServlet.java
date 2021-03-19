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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int docIdToDelete = -1;
		
		try {
			docIdToDelete = Integer.parseInt(request.getParameter("deleteDocID"));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Parse Int Error : " + e.getMessage());
		}

		if(docIdToDelete < 0) {
			request.setAttribute("deleteDocIdError", true);
		} else {
			//check if the document exists in the database
			Mediatek mediatek = Mediatek.getInstance();
			Document doc = mediatek.getDocument(docIdToDelete);
			// if the document does not exist then it well set the attribute docNotFound to true, 
			// else it will delete the document using its ID
			if(doc == null) {
				request.setAttribute("deleteDocIdFail", true);
			} else {
				request.setAttribute("deleteDocIdFail", false);
				try {
					mediatek.suppressDoc(docIdToDelete);
				} catch (SuppressException e) {
					e.printStackTrace();
					System.err.println("Fail to suppress document : " + e.getMessage());
				}
			}
		}
		request.getRequestDispatcher("./mediatek.jsp").forward(request, response);
	}
}
