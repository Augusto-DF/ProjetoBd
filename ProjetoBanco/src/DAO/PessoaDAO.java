package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Garcom;
import Entidades.Pessoa;

import ConnectionFactory.FabricaDeConexao;

public class PessoaDAO {
	
	private Connection conn;

	public PessoaDAO() {
		this.conn = new FabricaDeConexao().getConnection();
	}

	public void inserir(Pessoa p) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(p==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO pessoa (cpf, nome, datanasc, senha)" + "values (?,?,?,?)";
		conn = this.conn;
		ps = conn.prepareStatement(SQL);
		ps.setString(1, p.getCpf());
		ps.setString(2, p.getNome());
		ps.setString(3, p.getDataNasc());
		ps.setString(4, p.getSenha());
		ps.executeUpdate();	
	}
	
	public void deletar(Pessoa p) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(p == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		String SQL = "DELETE FROM PESSOA WHERE cpf=" + "(?)";
		conn = this.conn;
		ps = conn.prepareStatement(SQL);
		ps.setString(1, p.getCpf());
		ps.executeUpdate();	
	}
	
	public void atualizar(Pessoa p) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(p == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		String SQL = "UPDATE PESSOA SET nome=(?),  datanasc=(?), senha=(?) WHERE cpf=(?)";
		conn = this.conn;
		ps = conn.prepareStatement(SQL);		
		ps.setString(1, p.getNome());
		ps.setString(2, p.getDataNasc());
		ps.setString(3, p.getSenha());
		ps.setString(4, p.getCpf());
		ps.executeUpdate();	
	}
	
public List<Pessoa> listar(){
		
		List<Pessoa> pessoas = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT * FROM pessoa;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Pessoa p = new Pessoa();

				p.setCpf(rs.getString(1));
				p.setNome(rs.getString(2));
				p.setDataNasc(rs.getString(3));
				p.setSenha(rs.getString(4));
				
				pessoas.add(p);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível listar!");
		}
		
		return pessoas;
	}	

} 