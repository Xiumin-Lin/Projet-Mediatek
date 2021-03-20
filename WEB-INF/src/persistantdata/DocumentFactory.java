package persistantdata;

import mediatek2021.Document;

public class DocumentFactory {
	public static Document create(int type, Object... data) {
		switch(type) {
			case 1: return new Book(data);
			case 2: return new DVD(data);
			case 3: return new CD(data);
		}
		return null;
	}
}
