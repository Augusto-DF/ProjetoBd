package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Funcionario;

public class FuncionarioDAO {
	private Connection conn;
	private PessoaDAO pd;
	
	public FuncionarioDAO() {
		this.conn = new FabricaDeConexao().getConnection();
		this.pd = new PessoaDAO();
	}
	
	public void inserir(Funcionario f) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(f==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "INSERT INTO funcionario (cpf)"/*+ ",(salario)"*/ + "values (?)"/*+ ",(?)"*/;
		conn = this.conn;
		
		pd.inserir(f);
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, f.getCpf());
		//ps.setFloat(2, f.getSalario());
		ps.executeUpdate();	
	}
	
	public void deletar(Funcionario f) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		if(f==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "DELETE FROM Funcionario WHERE cpf=" + "(?)";
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, f.getCpf());
		ps.executeUpdate();	
		
		pd.deletar(f);
	}
	
	public void atualizar(Funcionario f) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(f == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		//String SQL = "UPDATE Funcionario SET salario=(?) WHERE cpf=(?)";
		conn = this.conn;
		
		/*
		ps = conn.prepareStatement(SQL);
		ps.setFloat(1, f.getSalario());
		ps.setString(2, f.getCpf());
		ps.executeUpdate();	
		*/
		
		pd.atualizar(f);
	}

public List<Funcionario> listar(){
		
		List<Funcionario> funcionarios = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT pessoa.cpf, nome, datanasc, senha FROM funcionario, pessoa WHERE pessoa.cpf = funcionario.cpf;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Funcionario f = new Funcionario();

				f.setCpf(rs.getString(1));
				f.setNome(rs.getString(2));
				f.setDataNasc(rs.getString(3));
				f.setSenha(rs.getString(4));
				
				funcionarios.add(f);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível listar!");
		}
		
		return funcionarios;
	}	

	public void close() throws SQLException {
		this.conn.close();
	}
}
