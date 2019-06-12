package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaDeConexao {
	
	public FabricaDeConexao() {
		
	}
	
	public Connection getConnection() {	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(
                    "jdbc:mysql://localhost/restaurante?useTimezone=true&serverTimezone=UTC", "root", "imd@2019");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

/*jdbc:mysql://127.0.0.1:3306/?user=root
Projeto-bd/ProjetoBanco/*/
