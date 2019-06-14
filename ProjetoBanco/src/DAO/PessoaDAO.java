package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		ps.setInt(3, p.getDataNasc());
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
		ps.setInt(2, p.getDataNasc());
		ps.setString(3, p.getSenha());
		ps.setString(4, p.getCpf());
		ps.executeUpdate();	
	}
} 