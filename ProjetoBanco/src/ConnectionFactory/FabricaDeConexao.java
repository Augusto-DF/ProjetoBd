package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaDeConexao {
	
	public FabricaDeConexao() {
		
	}
	
	public Connection getConnection() {	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(
                    "jdbc:mysql://localhost/bar?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "root"/*"imd@2019"*/);
			
		} catch (Exception e) {
			System.out.println("Erro ao conectar no banco");
			return null;
		}
	}

}
