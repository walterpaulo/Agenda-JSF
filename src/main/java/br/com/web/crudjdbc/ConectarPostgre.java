package br.com.web.crudjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarPostgre {
	
	public static Connection conectarNoBancoDeDados() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection conn = null;
		String url = "jdbc:postgresql://localhost/goiania";
		String usuario = "walter";
	    String senha = "abc123";
			
		conn = DriverManager.getConnection(url,usuario,senha);
		return conn;
	}

}
