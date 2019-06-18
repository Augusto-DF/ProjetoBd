package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Estoque;

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
	
public List<Estoque> listar(){
		
		List<Estoque> estoques = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT * FROM estoque;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Estoque g = new Estoque();
				
				g.setIdEstoque(rs.getInt(1));
				g.setGerente(rs.getString(2));
				
				estoques.add(g);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível listar!");
		}
		
		return estoques;
	}

	public void close() throws SQLException {
		this.conn.close();
	}
}












