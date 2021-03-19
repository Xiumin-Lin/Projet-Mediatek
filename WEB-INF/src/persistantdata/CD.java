package persistantdata;

public class CD extends AbstractDocument {
	
	public CD(int id, String title, String descrip, int borrowerId, int typeId) {
		super(id, title, descrip, borrowerId, typeId);
	}
}
