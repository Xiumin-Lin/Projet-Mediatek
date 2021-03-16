package persistantdata;

import mediatek2021.Utilisateur;

public class User implements Utilisateur {
	private String login;
	private String pwd;
	private Object[] data;
	
	public User(String login,String pwd, Object[] data) {
		this.login = login;
		this.pwd = pwd;
		this.data = data;
	}
	
	@Override
	public Object[] data() {
		return data;
	}

	@Override
	public String login() {
		return login;
	}

	@Override
	public String password() {
		return pwd;
	}

}
