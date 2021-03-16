package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
			System.err.println("The class was not found");
			e.printStackTrace();
		}
	}

	// renvoie la liste de tous les documents de la bibliotheque
	@Override
	public List<Document> catalogue(int type) {
		return null;
	}

	// va recuperer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String userName = "";
		String userPwd = "";
		try {
			Connection connect = DriverManager.getConnection(url, user, pwd);
			
			PreparedStatement ps = connect.prepareStatement("SELECT login,pwd,isAdmin FROM user WHERE login=?");
			ps.setString(1, login);
			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				userName = res.getString(1);
				userPwd = res.getString(2);
			}
			connect.close();
		} catch (SQLException e) {
			System.err.println("Error connection in db");
			e.printStackTrace();
		}
		//request success
		if(login.equals(userName) && password.equals(userPwd)) { //TODO no need to check login, the request already did the job
			Object[] data = null; //TODO change null value
			return new User(login,password,data);
		}
		return null;
	}

	// va recuperer le document de numero numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		return null;
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
