package persistantdata;

import mediatek2021.Document;

public class DocumentFactory {
	public static Document create(int docId, String title, String descrip, int borrowerId, int type) {
		switch(type) {
			case 1: return new Book(docId, title, descrip, borrowerId, DocType.BOOK.getId());
			case 2: return new DVD(docId, title, descrip, borrowerId, DocType.DVD.getId());
			case 3: return new CD(docId, title, descrip, borrowerId, DocType.CD.getId());
		}
		return null;
	}
}
