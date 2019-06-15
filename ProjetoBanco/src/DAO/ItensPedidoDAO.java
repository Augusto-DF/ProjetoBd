package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ConnectionFactory.FabricaDeConexao;
import Entidades.ItensEstoque;
import Entidades.ItensPedido;

public class ItensPedidoDAO {
private Connection conn;
	
	public ItensPedidoDAO() {
		this.conn = new FabricaDeConexao().getConnection();
	}
	
	public void inserir(ItensPedido ip) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(ip==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO itens_do_pedido "
				+ "(idPedido, item, valor, preparado, entregue, cpfgarcom_resp, id_estoque)"
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, ip.getPedido());
		ps.setString(2, ip.getItem());
		ps.setDouble(3, ip.getValor());
		ps.setBoolean(4, ip.isPreparado());
		ps.setBoolean(5, ip.isEntregue());
		ps.setString(6, ip.getResponsavel());
		ps.setInt(7, ip.getEstoque());
		ps.executeUpdate();	
	}
	
	public void deletar(ItensPedido ip) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(ip==null)
			throw new Exception("o valor passado nao pode ser nulo");
		
		String SQL = "DELETE FROM itens_do_pedido WHERE idPedido=" + "(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, ip.getPedido());
		ps.executeUpdate();			
	}
	
	public void atualizar(ItensPedido ip) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(ip == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		//item, valor, preparado, entregue, idgarcom_resp, id_estoque)"
		String SQL = "UPDATE itens_do_pedido "
				+ "SET Item=(?), valor=(?), preparado=(?), "
				+ "entregue=(?), idgarcom_resp=(?), id_estoque=(?)"
				+ " WHERE idPedido=(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, ip.getItem());
		ps.setDouble(2, ip.getValor());
		ps.setBoolean(3, ip.isPreparado());
		ps.setBoolean(4, ip.isEntregue());
		ps.setString(5, ip.getResponsavel());
		ps.setInt(6, ip.getEstoque());
		ps.setInt(7, ip.getPedido());
		ps.executeUpdate();	
	}
}


















