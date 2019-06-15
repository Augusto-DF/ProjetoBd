package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Cozinheiro;
import Entidades.Supervisionado;

public class CozinheiroDAO {
	private Connection conn;
	private SupervisionadoDAO sd;
	
	public CozinheiroDAO() {
		this.conn = new FabricaDeConexao().getConnection();
		this.sd = new SupervisionadoDAO();
	}
	
	public void inserir(Cozinheiro c) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		if(c==null)
			throw new Exception("o valor passado nao pode ser nulo");
		
		String SQL = "INSERT INTO cozinheiro (cpf)"/*+ ",(salario)"*/ + "values (?)"/*+ ",(?)"*/;
		conn = this.conn;
		sd.inserir(c);
		ps = conn.prepareStatement(SQL);
		ps.setString(1, c.getCpf());
		ps.executeUpdate();
	}
	
	public void deletar(Cozinheiro c) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(c==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "DELETE FROM Cozinheiro WHERE cpf=" + "(?)";
		
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, c.getCpf());
		ps.executeUpdate();
		
		sd.deletar(c);
	}
	
	public void atualizar(Cozinheiro c) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(c == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		conn = this.conn;
		
		sd.atualizar(c);
	}
}





















