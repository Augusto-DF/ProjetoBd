package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Supervisionado;

public class SupervisionadoDAO {
	private Connection conn;
	private FuncionarioDAO fd;
	
	public SupervisionadoDAO() {
		this.conn = new FabricaDeConexao().getConnection();
		this.fd = new FuncionarioDAO();
	}
	
	public void inserir(Supervisionado s) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(s==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO pessoa (cpf)" + "values (?)";
		conn = this.conn;
		fd.inserir(s);
		ps = conn.prepareStatement(SQL);
		ps.setString(1, s.getCpf());
		//ps.setFloat(2, f.getSalario());
		ps.executeUpdate();	
	}
}
