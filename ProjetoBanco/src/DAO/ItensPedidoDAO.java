package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.FabricaDeConexao;
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
				+ "(idPedido, item, preparado, entregue, cpfgarcom_resp, id_estoque, quantidade)"
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, ip.getPedido());
		ps.setString(2, ip.getItem());
		ps.setBoolean(3, ip.isPreparado());
		ps.setBoolean(4, ip.isEntregue());
		ps.setString(5, ip.getResponsavel());
		ps.setInt(6, ip.getEstoque());
		ps.setInt(7, ip.getQuantidade());
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
				+ "SET Item=(?), preparado=(?), "
				+ "entregue=(?), cpfgarcom_resp=(?), id_estoque=(?)"
				+ " WHERE idItem=(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, ip.getItem());
		ps.setBoolean(2, ip.isPreparado());
		ps.setBoolean(3, ip.isEntregue());
		ps.setString(4, ip.getResponsavel());
		ps.setInt(5, ip.getEstoque());
		ps.setInt(6, ip.getIdItem());
		ps.executeUpdate();	
	}
	
public ArrayList<ItensPedido> listar(){
		
		ArrayList<ItensPedido> itensPedido = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT * FROM itens_do_pedido;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ItensPedido ip = new ItensPedido();
				ip.setItem(rs.getString(1));
				ip.setPedido(rs.getInt(2));
				ip.setPreparado(rs.getBoolean(3));
				ip.setEntregue(rs.getBoolean(4));
				ip.setResponsavel(rs.getString(5));
				ip.setDetalhes(rs.getString(6));
				ip.setEstoque(rs.getInt(7));
				
				itensPedido.add(ip);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível listar!");
		}
		
		return itensPedido;
	}

public ArrayList<ItensPedido> listarPorId(int id){
	
	ArrayList<ItensPedido> itensPedido = new ArrayList();
	PreparedStatement ps = null;
	ResultSet rs = null;
	ItensPedido ip = null;
	
	try {
		ps = this.conn.prepareStatement("SELECT item, idpedido, preparado, "
				+ "entregue, quantidade, cpfgarcom_resp, detalhes,"
				+ "id_estoque FROM itens_do_pedido "
				+ "WHERE IdPedido =" + id);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			
			ip = new ItensPedido();
			ip.setItem(rs.getString(1));
			ip.setPedido(rs.getInt(2));
			ip.setPreparado(rs.getBoolean(3));
			ip.setEntregue(rs.getBoolean(4));
			ip.setQuantidade(rs.getInt(5));
			ip.setResponsavel(rs.getString(6));
			ip.setDetalhes(rs.getString(7));
			ip.setEstoque(rs.getInt(8));
			
			itensPedido.add(ip);
		}
		
	} catch (SQLException e) {
		System.out.println("Não foi possível listar!");
	}
	
	return itensPedido;
}

public ArrayList<ItensPedido> listarPorResponsavel(String resp){
	
	ArrayList<ItensPedido> itensPedido = new ArrayList();
	PreparedStatement ps = null;
	ResultSet rs = null;
	ItensPedido ip = null;
	
	try {
		ps = this.conn.prepareStatement("SELECT * FROM itens_do_pedido "
				+ "WHERE cpfgarcom_resp =" + resp + " and preparado = 1 and entregue = 0");
		rs = ps.executeQuery();
		
		while(rs.next()) {
			
			ip = new ItensPedido();
			ip.setIdItem(rs.getInt(1));
			ip.setItem(rs.getString(2));
			ip.setPedido(rs.getInt(3));
			ip.setPreparado(rs.getBoolean(4));
			ip.setEntregue(rs.getBoolean(5));
			ip.setQuantidade(rs.getInt(6));
			ip.setResponsavel(rs.getString(7));
			ip.setDetalhes(rs.getString(8));
			ip.setEstoque(rs.getInt(9));
			
			itensPedido.add(ip);
		}
		
	} catch (SQLException e) {
		System.out.println("Não foi possível listar!");
	}
	
	if(itensPedido.isEmpty()) {
		System.out.println("Não ha pedidos preparados para a entrega");
		return null;
	}
	
	return itensPedido;
}


public ArrayList<ItensPedido> listarPorPreparo(){
	
	ArrayList<ItensPedido> itensPedido = new ArrayList();
	PreparedStatement ps = null;
	ResultSet rs = null;
	ItensPedido ip = null;
	
	try {
		ps = this.conn.prepareStatement("SELECT * FROM itens_do_pedido "
				+ "WHERE preparado = 0 and entregue = 0");
		rs = ps.executeQuery();
		
		while(rs.next()) {
			
			ip = new ItensPedido();
			ip.setIdItem(rs.getInt(1));
			ip.setItem(rs.getString(2));
			ip.setPedido(rs.getInt(3));
			ip.setPreparado(rs.getBoolean(4));
			ip.setEntregue(rs.getBoolean(5));
			ip.setQuantidade(rs.getInt(6));
			ip.setResponsavel(rs.getString(7));
			ip.setDetalhes(rs.getString(8));
			ip.setEstoque(rs.getInt(9));
			
			itensPedido.add(ip);
		}
		
	} catch (SQLException e) {
		System.out.println("Não foi possível listar!");
	}
	
	if(itensPedido.isEmpty()) {
		System.out.println("Não ha pedidos preparados para a preparo");
		return null;
	}
	
	return itensPedido;
}

	public void close() throws SQLException {
		this.conn.close();
	}
}


















