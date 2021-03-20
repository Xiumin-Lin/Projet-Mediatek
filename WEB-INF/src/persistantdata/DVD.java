package persistantdata;

public class DVD extends AbstractDocument {
	
	public DVD(Object[] data) {
		super(DocType.DVD.getId(), data);
	}
}
