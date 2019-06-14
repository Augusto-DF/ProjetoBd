package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Gerente;

public class GerenteDAO {
	private Connection conn;
	private FuncionarioDAO fd;
	
	public GerenteDAO() {
		this.conn = new FabricaDeConexao().getConnection();
		this.fd = new FuncionarioDAO();
	}
	
	public void inserir(Gerente g) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(g==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO gerente (cpf)" + "values (?)";
		conn = this.conn;
		fd.inserir(g);
		ps = conn.prepareStatement(SQL);
		ps.setString(1, g.getCpf());
		//ps.setFloat(2, f.getSalario());
		ps.executeUpdate();	
	}
}
