package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Supervisionado;

public class SupervisionadoDAO {
	private Connection conn;
	private FuncionarioDAO fd;
	
	public SupervisionadoDAO() {
		this.conn = new FabricaDeConexao().getConnection();
		this.fd = new FuncionarioDAO();
	}
	
	public void inserir(Supervisionado s) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(s==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO supervisionado (cpf, cpf_supervisor)" + "values (?, ?)";
		conn = this.conn;
		fd.inserir(s);
		ps = conn.prepareStatement(SQL);
		ps.setString(1, s.getCpf());
		ps.setString(2, s.getCpfGerente());
		ps.executeUpdate();	
	}
	
	public void deletar(Supervisionado s) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(s==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "DELETE FROM Supervisionado WHERE cpf=" + "(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, s.getCpf());
		ps.executeUpdate();
		
		fd.deletar(s);
	}
	
	public void atualizar(Supervisionado s) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(s == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		String SQL = "UPDATE Supervisionado SET cpf_supervisor=(?)  WHERE cpf=(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, s.getCpfGerente());
		ps.setString(2, s.getCpf());
		ps.executeUpdate();	
		
		fd.atualizar(s);
	}
}