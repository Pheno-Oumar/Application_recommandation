package monActivite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Pheno {
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/boby";
		String username = "root";
		String passwd = "";
		try {
			Connection conn = DriverManager.getConnection(url,username,passwd);
			System.out.println("connexion reussi");
			conn.close();
		}catch(SQLException e) {
			System.out.println("erreur:"+ e.getMessage());

		}
	}

}
