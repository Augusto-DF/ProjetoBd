package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import ConnectionFactory.FabricaDeConexao;
import Entidades.Cliente;
import Entidades.Funcionario;

public class ClienteDAO {
	private Connection conn;
	private PessoaDAO pd;
	
	public ClienteDAO() {
		this.conn = new FabricaDeConexao().getConnection();
		this.pd = new PessoaDAO();
	}
	
	public void inserir(Cliente c) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(c==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO Cliente (cpf)" + "values (?)";
		conn = this.conn;
		
		pd.inserir(c);
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, c.getCpf());

		ps.executeUpdate();	
	}
	
	public void deletar(Cliente c) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(c==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "DELETE FROM Cliente WHERE cpf=" + "(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, c.getCpf());
		ps.executeUpdate();	
		
		pd.deletar(c);
	}
	
	public void atualizar(Cliente c) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(c == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		conn = this.conn;
		
		pd.atualizar(c);
	}
}





















