package persistantdata;

import mediatek2021.Document;

public class DocumentFactory {
	public static Document create(int docId, String title, String descrip, int borrowerId, DocType type) {
		switch(type) {
			case BOOK: return new Book(docId, title, descrip, borrowerId, DocType.BOOK.getId());
			case DVD: return new DVD(docId, title, descrip, borrowerId, DocType.DVD.getId());
			case CD: return new CD(docId, title, descrip, borrowerId, DocType.CD.getId());
		}
		return null;
	}
}
