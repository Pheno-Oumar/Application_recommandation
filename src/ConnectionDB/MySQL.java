package ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Interface.Database;

public class MySQL implements Database {

	private Connection conn;

	@Override
	public Connection connexion() {

		String url = "jdbc:mysql://localhost:3306/application_recommandation";
		String username = "root";
		String passwd = "";
		try {
			conn = DriverManager.getConnection(url, username, passwd);
			System.out.println("Connexion reussi.");
		} catch (SQLException e) {
			System.out.println("Erreur: " + e.getMessage());
		}
		return conn;
	}

	@Override
	public void deconnexion(Connection conn) {
		if (conn == null) {
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Erreur: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/application_recommandation";
		String username = "root";
		String passwd = "aichatasylla44@gmail.com";
		try {
			DriverManager.getConnection(url, username, passwd);
			System.out.println("Connexion reussi.");
		} catch (SQLException e) {
			System.out.println("Erreur: " + e.getMessage());
		}
	}

}
