package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Cliente;
import Entidades.Pedido;

public class PedidoDAO {
private Connection conn;
	
	public PedidoDAO() {
		this.conn = new FabricaDeConexao().getConnection();
	}
	
	public void inserir(Pedido p) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		if(p==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO Pedido" + /*(idPedido,*/ "(cpf_cliente, forma_pagamento, fecharPedido)" + "values (?, ?, ?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		//ps.setInt(1, p.getIdPedido());
		ps.setString(1, p.getCliente());
		ps.setString(2, p.getFormaPagamento());
		ps.setBoolean(3, p.isFecharPedido());
		ps.executeUpdate();	
	}
	
	public void deletar(Pedido p) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(p==null)
			throw new Exception("o valor passado nao pode ser nulo");
		
		String SQL = "DELETE FROM Pedido WHERE idPedido=" + "(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, p.getIdPedido());
		ps.executeUpdate();			
	}
	
	public void atualizar(Pedido p) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(p == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		String SQL = "UPDATE Pedido SET cpf_cliente=(?), forma_pagamento=(?), fecharPedido=(?) WHERE idPedido=(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, p.getCliente());
		ps.setString(2, p.getFormaPagamento());
		ps.setBoolean(3, p.isFecharPedido());
		ps.setInt(4, p.getIdPedido());
		ps.executeUpdate();	
	}
	
	public List<Pedido> listar(){
		
		List<Pedido> pedidos = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT * FROM pedido;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Pedido p = new Pedido();
				
				p.setIdPedido(rs.getInt(1));
				p.setCliente(rs.getString(2));
				p.setFormaPagamento(rs.getString(3));
				
				pedidos.add(p);
			}
			
		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel listar!");
		}
		
		return pedidos;
	}
	
	public Pedido buscarPedido(String cpfCliente) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pedido pd = null;
		String SQL = "SELECT idPedido, cpf_cliente,"
				+ "forma_pagamento FROM pedido  WHERE cpf_cliente =(?) and fecharPedido=0";
		
		try {
			ps = this.conn.prepareStatement(SQL);
			ps.setString(1, cpfCliente);
			rs = ps.executeQuery();
			if(rs.next()) {
				pd = new Pedido();
				pd.setIdPedido(rs.getInt(1));
				pd.setCliente(rs.getString(2)); 
				pd.setFormaPagamento(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("N�o foi poss�vel encontrar!");
			return null;
		}
		return pd;			
	}
	
	public void close() throws SQLException {
		this.conn.close();
	}	
}















