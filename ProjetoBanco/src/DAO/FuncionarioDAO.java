package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import ConnectionFactory.FabricaDeConexao;
import Entidades.Funcionario;
import Entidades.Pessoa;

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
		pd.deletar(f);
		ps = conn.prepareStatement(SQL);
		ps.setString(1, f.getCpf());
		ps.executeUpdate();	
	}
	
	public void atualizar(Funcionario f) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(f == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		//String SQL = "UPDATE Funcionario SET salario=(?) WHERE cpf=(?)";
		conn = this.conn;
		pd.atualizar(f);
		/*
		ps = conn.prepareStatement(SQL);
		ps.setFloat(1, f.getSalario());
		ps.setString(2, f.getCpf());
		ps.executeUpdate();	
		*/
	}
}
