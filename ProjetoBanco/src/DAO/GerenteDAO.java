package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Gerente;

public class GerenteDAO {
	private Connection conn;
	private FuncionarioDAO fd;
	
	public GerenteDAO() {
		this.conn = new FabricaDeConexao().getConnection();
		this.fd = new FuncionarioDAO();
	}
	
	public void inserir(Gerente g) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(g==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO gerente (cpf)" + "values (?)";
		conn = this.conn;
		fd.inserir(g);
		ps = conn.prepareStatement(SQL);
		ps.setString(1, g.getCpf());
		//ps.setFloat(2, f.getSalario());
		ps.executeUpdate();	
	}
	
	public void deletar(Gerente g) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(g==null)
			throw new Exception("o valor passado nao pode ser nulo");
		
		String SQL = "DELETE FROM gerente WHERE cpf=" + "(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, g.getCpf());
		ps.executeUpdate();	
		
		fd.deletar(g);
	}
	
	public void atualizar(Gerente g) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(g == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		conn = this.conn;
		
		fd.atualizar(g);
	}
	
public List<Gerente> listar(){
		
		List<Gerente> gerentes = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT pessoa.cpf, nome, datanasc, senha FROM gerente, pessoa WHERE pessoa.cpf = gerente.cpf;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Gerente g = new Gerente();
				
				g.setCpf(rs.getString(1));
				g.setNome(rs.getString(2));
				g.setDataNasc(rs.getString(3));
				g.setSenha(rs.getString(4));
				
				gerentes.add(g);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível listar!");
		}
		
		return gerentes;
	}

	public Gerente buscarCPF(String resposta) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Gerente g = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT gerente.cpf, nome, datanasc, senha FROM gerente, pessoa WHERE gerente.cpf =?;");
			ps.setString(1, resposta);
			rs = ps.executeQuery();
			if(rs.next()) {
				g = new Gerente();
				g.setCpf(rs.getString(1));
				g.setNome(rs.getString(2));
				g.setDataNasc(rs.getString(3));
				g.setSenha(rs.getString(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Não foi possível encontrar!");
		}
		
		return g;
	}
	
	public void close() throws SQLException {
		this.conn.close();
	}
}
