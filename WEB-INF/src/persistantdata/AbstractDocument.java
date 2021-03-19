package persistantdata;

import java.util.HashMap;
import java.util.Map;

import mediatek2021.Document;

public abstract class AbstractDocument implements Document {
	private Map<String,Object> data;
	
	protected AbstractDocument(int id, String title, String descrip, int borrowerId, int typeId) {
		data = new HashMap<>();
		data.put("docId", id);
		data.put("title", title);
		data.put("descrip", descrip);
		data.put("borrowerId", borrowerId);
		data.put("typeId", typeId);
	}
	
	@Override
	public Object[] data() {
		return data.entrySet().toArray();
	}
	
	public void addData(String dataName, Object data) {
		this.data.put(dataName, data);
	}
}
