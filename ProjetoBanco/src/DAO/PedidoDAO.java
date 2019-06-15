package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Estoque;
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
		String SQL = "INSERT INTO Pedido (idPedido, cpf_cliente, forma_pagamento)" + "values (?, ?, ?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, p.getIdPedido());
		ps.setString(2, p.getCliente());
		ps.setString(3, p.getFormaPagamento());
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
		
		String SQL = "UPDATE Pedido SET cpf_cliente=(?), forma_pagamento=(?) WHERE idPedido=(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, p.getCliente());
		ps.setString(2, p.getFormaPagamento());
		ps.setInt(3, p.getIdPedido());
		ps.executeUpdate();	
	}
}














