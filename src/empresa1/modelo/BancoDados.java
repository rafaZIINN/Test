package empresa1.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BancoDados {
	
	private static Connection conn;
	
	public static Connection crieConexao() {
		
		if (conn!=null) {
			return conn;
		}
		
		try {
			Class.forName(DBConfig.DRIVER);//carrega o drive do postgresql para a memoria, implementando o jdbc (java.sql)
			conn = DriverManager.getConnection(
					DBConfig.URL,
					DBConfig.USER, 
					DBConfig.PWD);		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}

}
