package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Estoque;
import Entidades.Gerente;

public class EstoqueDAO {
	private Connection conn;
	
	public EstoqueDAO() {
		this.conn = new FabricaDeConexao().getConnection();
	}
	
	public void inserir(Estoque etq) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(etq==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO Estoque (idEstoque, CPF_gerente)" + "values (?, ?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, etq.getIdEstoque());
		ps.setString(2, etq.getGerente());
		ps.executeUpdate();	
	}
	
	public void deletar(Estoque etq) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(etq==null)
			throw new Exception("o valor passado nao pode ser nulo");
		
		String SQL = "DELETE FROM Estoque WHERE idEstoque=" + "(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, etq.getIdEstoque());
		ps.executeUpdate();			
	}
	
	public void atualizar(Estoque etq) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(etq == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		String SQL = "UPDATE Estoque SET CPF_gerente=(?) WHERE idEstoque=(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, etq.getGerente());
		ps.setInt(2, etq.getIdEstoque());
		ps.executeUpdate();	
	}
}












