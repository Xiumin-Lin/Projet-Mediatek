package persistantdata;

public class Book extends AbstractDocument {
	
	public Book(Object[] data) {
		super(DocType.BOOK.getId(), data);
	}
}
