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
			
			PreparedStatement ps = connect.prepareStatement("SELECT * FROM user WHERE login=?");
			ps.setString(1, login);
			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				userPwd = res.getString(3);
				data.add(res.getString(4)); //[0] => name
				data.add(res.getInt(5));		//[1] => age
				data.add(res.getString(6));	//[2] => adress
				Boolean isAdmin = (res.getInt(7) == 1) ? true : false;
				data.add(isAdmin); //[3] => isAdmin
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
		try {
			Connection connect = DriverManager.getConnection(url, user, pwd);
			//retrieve all doc ID corresponding to the chosen type
			PreparedStatement ps = connect.prepareStatement("SELECT * FROM document WHERE id_doc=?");
			ps.setInt(1, numDocument);
			ResultSet res = ps.executeQuery();
			//retrieve each doc by their ID & add in catalogue
			while(res.next()) {
				doc = DocumentFactory.create(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));
			}
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
		
	}

}
