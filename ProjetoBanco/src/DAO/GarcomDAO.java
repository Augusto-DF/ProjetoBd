package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import ConnectionFactory.FabricaDeConexao;
import Entidades.Garcom;

public class GarcomDAO {
	private Connection conn;
	private SupervisionadoDAO sd;
	
	public GarcomDAO() {
		this.conn = new FabricaDeConexao().getConnection();
		this.sd = new SupervisionadoDAO();
	}
	
	public void inserir(Garcom g) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		if(g==null)
			throw new Exception("o valor passado nao pode ser nulo");
		
		String SQL = "INSERT INTO garçom (cpf)"/*+ ",(salario)"*/ + "values (?)"/*+ ",(?)"*/;
		conn = this.conn;
		sd.inserir(g);
		ps = conn.prepareStatement(SQL);
		ps.setString(1, g.getCpf());
		ps.executeUpdate();
	}
	
	public void deletar(Garcom g) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(g==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "DELETE FROM Garçom WHERE cpf=" + "(?)";
		
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, g.getCpf());
		ps.executeUpdate();
		
		sd.deletar(g);
	}
	
	public void atualizar(Garcom g) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(g == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		conn = this.conn;
		
		sd.atualizar(g);
	}
}















