package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Cliente;

public class ClienteDAO {
	private Connection conn;
	private PessoaDAO pd;
	
	public ClienteDAO() {
		this.conn = new FabricaDeConexao().getConnection();
		this.pd = new PessoaDAO();
	}
	
	public void inserir(Cliente c) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(c==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO Cliente (cpf)" + "values (?)";
		conn = this.conn;
		
		pd.inserir(c);
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, c.getCpf());

		ps.executeUpdate();	
	}
	
	public void deletar(Cliente c) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(c==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "DELETE FROM Cliente WHERE cpf=" + "(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, c.getCpf());
		ps.executeUpdate();	
		
		pd.deletar(c);
	}
	
	public void atualizar(Cliente c) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(c == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		conn = this.conn;
		
		pd.atualizar(c);
	}

	public List<Cliente> listar(){
		
		List<Cliente> clientes = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT pessoa.cpf, nome, datanasc, senha FROM cliente, pessoa WHERE pessoa.cpf = cliente.cpf");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Cliente c = new Cliente();
				
				c.setCpf(rs.getString(1));
				c.setNome(rs.getString(2));
				c.setDataNasc(rs.getString(3));
				c.setSenha(rs.getString(4));
				
				clientes.add(c);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível listar!");
		}
		
		return clientes;
	}

	public Cliente buscarCPF(String resposta) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cliente c = null;
		String SQL = "SELECT pessoa.cpf, nome, datanasc, senha FROM cliente, pessoa WHERE pessoa.cpf =(?)";
		
		try {
			ps = this.conn.prepareStatement(SQL);
			ps.setString(1, resposta);
			rs = ps.executeQuery();
			if(rs.next()) {
				c = new Cliente();
				c.setCpf(rs.getString(1));
				c.setNome(rs.getString(2));
				c.setDataNasc(rs.getString(3));
				c.setSenha(rs.getString(4));
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





















