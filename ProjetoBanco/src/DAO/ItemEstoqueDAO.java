package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Cliente;
import Entidades.Estoque;
import Entidades.ItensEstoque;

public class ItemEstoqueDAO {
	private Connection conn;
	
	public ItemEstoqueDAO() {
		this.conn = new FabricaDeConexao().getConnection();
	}
	
	public void inserir(ItensEstoque ie) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(ie==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO itens_do_estoque (id_estoque, Item, quantidade)" + "values (?, ?, ?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, ie.getEstoque());
		ps.setString(2, ie.getProduto());
		ps.setInt(3, ie.getQuantidade());
		ps.executeUpdate();	
	}
	
	public void deletar(ItensEstoque ie) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(ie==null)
			throw new Exception("o valor passado nao pode ser nulo");
		
		String SQL = "DELETE FROM itens_do_estoque WHERE id_estoque=" + "(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, ie.getEstoque());
		ps.executeUpdate();			
	}
	
	public void atualizar(ItensEstoque ie) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(ie == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		String SQL = "UPDATE itens_do_estoque SET Item=(?), quantidade=(?) WHERE id_estoque=(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, ie.getProduto());
		ps.setInt(2, ie.getQuantidade());
		ps.setInt(3, ie.getEstoque());
		ps.executeUpdate();	
	}
	
public List<ItensEstoque> listar(){
		
		List<ItensEstoque> itensEstoque = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT * FROM itens_do_estoque;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ItensEstoque ie = new ItensEstoque();
				
				ie.setEstoque(rs.getInt(1));
				ie.setProduto(rs.getString(2));
				ie.setQuantidade(rs.getInt(3));				
				
				itensEstoque.add(ie);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível listar!");
		}
		
		return itensEstoque;
	}
	
	
	
}



















