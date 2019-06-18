package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Cozinheiro> listar(){
		
		List<Cozinheiro> cozinheiros = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT pessoa.cpf, nome, datanasc, senha, cpf_supervisor FROM cozinheiro, supervisionado, pessoa WHERE pessoa.cpf = cozinheiro.cpf AND cozinheiro.cpf = supervisionado.cpf;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Cozinheiro c = new Cozinheiro();

				c.setCpf(rs.getString(1));
				c.setNome(rs.getString(2));
				c.setDataNasc(rs.getString(3));
				c.setSenha(rs.getString(4));
				c.setCpfGerente(rs.getString(5));
				
				cozinheiros.add(c);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível listar!");
		}
		
		return cozinheiros;
	}	
	
	public Cozinheiro buscarCPF(String resposta) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cozinheiro c = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT cozinheiro.cpf, nome, datanasc, senha, cpf_supervisor FROM cozinheiro, pessoa, supervisionado WHERE cozinheiro.cpf =?;");
			ps.setString(1, resposta);
			rs = ps.executeQuery();
			if(rs.next()) {
				c = new Cozinheiro();
				c.setCpf(rs.getString(1));
				c.setNome(rs.getString(2));
				c.setDataNasc(rs.getString(3));
				c.setSenha(rs.getString(4));
				c.setCpfGerente(rs.getString(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Não foi possível encontrar!");
		}
		
		return c;
	}
	
	public void close() throws SQLException {
		this.conn.close();
	}
}





















