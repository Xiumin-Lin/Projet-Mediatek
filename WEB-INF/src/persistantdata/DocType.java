package persistantdata;

public enum DocType {
	BOOK(1), DVD(2), CD(3);
	
	private int typeId;
	DocType(int typeId) {
		this.typeId = typeId;
	}
	
	public int getId() {
		return typeId;
	}
}
