package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Cozinheiro;

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
		//ps.setFloat(2, f.getSalario());
		ps.executeUpdate();
	}
}
