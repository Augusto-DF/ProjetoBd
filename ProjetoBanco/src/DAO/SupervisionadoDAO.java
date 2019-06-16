package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Supervisionado> listar(){
		
		List<Supervisionado> supervisionados = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT pessoa.cpf, nome, datanasc, senha, cpf_supervisor FROM supervisionado, pessoa WHERE pessoa.cpf = supervisionado.cpf;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Supervisionado s = new Supervisionado();
				
				s.setCpf(rs.getString(1));
				s.setNome(rs.getString(2));
				s.setDataNasc(rs.getString(3));
				s.setSenha(rs.getString(4));
				s.setCpfGerente(rs.getString(5));
				
				supervisionados.add(s);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível listar!");
		}
		
		return supervisionados;
	}
}
