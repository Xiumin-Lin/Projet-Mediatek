package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Document;
import mediatek2021.Mediatek;
import mediatek2021.SuppressException;

public class DeleteDocServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//get the document id typed by the user to delete
		int docIdToDelete = -1;
		
		
		try {
			docIdToDelete = Integer.parseInt(request.getParameter("deleteDocID"));
		
		} catch (Exception e) {
			System.err.println("blabla");
			// TODO: handle exception
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		request.getRequestDispatcher("./mediatek.jsp").forward(request, response);
			
	}
	


}
