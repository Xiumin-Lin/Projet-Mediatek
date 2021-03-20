package persistantdata;

import mediatek2021.Document;

public abstract class AbstractDocument implements Document {
	private Object[] data;
	private int type;
	
	protected AbstractDocument(int typeId, Object[] data) {
		this.data = data;
		this.type = typeId;
	}
	
	@Override
	public Object[] data() {
		return data;
	}
	
	public int getType() {
		return type;
	}
}
