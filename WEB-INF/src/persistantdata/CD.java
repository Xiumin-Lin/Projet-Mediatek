package persistantdata;

public class CD extends AbstractDocument {
	
	public CD(Object[] data) {
		super(DocType.CD.getId(), data);
	}
}
