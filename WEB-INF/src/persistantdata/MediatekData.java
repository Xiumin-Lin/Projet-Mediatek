package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import mediatek2021.*;

// classe mono-instance : l'unique instance est connue de la bibliotheque
// via une injection de dependance dans son bloc static

public class MediatekData implements PersistentMediatek {
// Jean-François Brette 01/01/2018
	static {
		// injection dynamique de la dependance dans le package stable mediatek2021
		Mediatek.getInstance().setData(new MediatekData());
	}

	private static String url = "jdbc:mysql://localhost:3306/mediatek";
	private static String user = "root";
	private static String pwd = "";
	
	private MediatekData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			System.err.println("MySQL JDBC Driver was not found");
			e.printStackTrace();
		}
	}

	// renvoie la liste de tous les documents de la bibliotheque
	@Override
	public List<Document> catalogue(int type) {
		List<Document> catalogue = new ArrayList<>();
		try {
			Connection connect = DriverManager.getConnection(url, user, pwd);
			//retrieve all doc ID corresponding to the chosen type
			PreparedStatement ps = connect.prepareStatement("SELECT id_doc FROM document WHERE id_type=?");
			ps.setInt(1, type);
			ResultSet res = ps.executeQuery();
			//retrieve each doc by their ID & add in catalogue
			while(res.next()) {
				Document doc = getDocument(res.getInt(1));
				if(doc != null)
					catalogue.add(doc);
			}
			connect.close();
		} catch (SQLException e) {
			System.err.println("Error connection in db : " + e.getMessage());
			e.printStackTrace();
		}
		//return catalogue if there a one doc or more, otherwise return null
		return (catalogue.size() > 0) ? catalogue : null;
	}

	// va recuperer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String userPwd = "";
		List<Object> data = new ArrayList<>();
		try {
			Connection connect = DriverManager.getConnection(url, user, pwd);
			
			PreparedStatement ps = connect.prepareStatement("SELECT id_user, pwd, name, age, address, isAdmin FROM user WHERE login=?");
			ps.setString(1, login);
			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				userPwd = res.getString(2);
				data.add(res.getInt(1)); 		//[0] --> user id
				data.add(res.getString(3)); //[1] --> name
				data.add(res.getInt(4));		//[2] --> age
				data.add(res.getString(5));	//[3] --> address
				Boolean isAdmin = (res.getInt(6) == 1) ? true : false;
				data.add(isAdmin); //[4] => isAdmin
			}
			connect.close();
		} catch (SQLException e) {
			System.err.println("Error connection in db : " + e.getMessage());
			e.printStackTrace();
		}
		//check pwd
		if(password.equals(userPwd)) {
			return new User(login,password,data.toArray());
		}
		//wrong pwd
		return null;
	}

	// va recuperer le document de numero numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		Document doc = null;
		List<Object> data = new ArrayList<>();
		try {
			Connection connect = DriverManager.getConnection(url, user, pwd); //TODO
			//retrieve all doc ID corresponding to the chosen type
			String sql = 	"SELECT doc.title, doc.description, doc.id_borrower, doc.id_type "
									+ "FROM document doc "
									+ "WHERE doc.id_doc=?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, numDocument);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				int type = res.getInt("id_type");
				data.add(numDocument);			//[0] --> doc id
				data.add(res.getString(1));	//[1] --> title
				data.add(res.getString(2));	//[2] --> description
				data.add(res.getInt(3));		//[3] --> id_borrower
				data.add(type);							//[5] --> type
				doc = DocumentFactory.create(type, data.toArray());
			}
			ps.close();
			connect.close();
		} catch (SQLException e) {
			System.err.println("Error connection in db : " + e.getMessage());
			e.printStackTrace();
		}
		//return catalogue if there a one doc or more, otherwise return null
		return doc;
	}

	// ajoute un nouveau document - exception a definir
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc en fonction du type et des infos optionnelles
		
	}

	// supprime un document - exception a definir
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		try {
			Connection connect = DriverManager.getConnection(url, user, pwd);
			PreparedStatement ps = connect.prepareStatement("DELETE FROM document WHERE id_doc=?");
			ps.setInt(1, numDoc);
			ps.executeUpdate();
			connect.close();
		} catch (SQLException e) {
			throw new SuppressException(e.getMessage());
		}
	}
}
