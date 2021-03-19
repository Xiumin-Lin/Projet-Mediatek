package persistantdata;

public class DVD extends AbstractDocument {
	
	public DVD(int id, String title, String descrip, int borrowerId, int typeId) {
		super(id, title, descrip, borrowerId, typeId);
	}
}
