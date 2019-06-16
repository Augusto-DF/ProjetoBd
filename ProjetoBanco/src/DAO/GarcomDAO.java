package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.FabricaDeConexao;
import Entidades.Cozinheiro;
import Entidades.Garcom;

public class GarcomDAO {
	private Connection conn;
	private SupervisionadoDAO sd;
	
	public GarcomDAO() {
		this.conn = new FabricaDeConexao().getConnection();
		this.sd = new SupervisionadoDAO();
	}
	
	public void inserir(Garcom g) throws Exception{
		PreparedStatement ps= null;
		Connection conn = null;
		if(g==null)
			throw new Exception("o valor passado nao pode ser nulo");
		
		String SQL = "INSERT INTO garçom (cpf)"/*+ ",(salario)"*/ + "values (?)"/*+ ",(?)"*/;
		conn = this.conn;
		sd.inserir(g);
		ps = conn.prepareStatement(SQL);
		ps.setString(1, g.getCpf());
		ps.executeUpdate();
	}
	
	public void deletar(Garcom g) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(g==null)
			throw new Exception("o valor passado nao pode ser nulo");
		String SQL = "DELETE FROM Garçom WHERE cpf=" + "(?)";
		
		conn = this.conn;
		
		ps = conn.prepareStatement(SQL);
		ps.setString(1, g.getCpf());
		ps.executeUpdate();
		
		sd.deletar(g);
	}
	
	public void atualizar(Garcom g) throws Exception {
		PreparedStatement ps= null;
		Connection conn = null;
		
		if(g == null) {
			throw new Exception("o valor passado nao pode ser nulo");
		}
		
		conn = this.conn;
		
		sd.atualizar(g);
	}
	
public List<Garcom> listar(){
		
		List<Garcom> garcons = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.conn.prepareStatement("SELECT pessoa.cpf, nome, datanasc, senha, cpf_supervisor FROM garçom, supervisionado, pessoa WHERE pessoa.cpf = garçom.cpf AND garçom.cpf = supervisionado.cpf;");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Garcom g = new Garcom();

				g.setCpf(rs.getString(1));
				g.setNome(rs.getString(2));
				g.setDataNasc(rs.getString(3));
				g.setSenha(rs.getString(4));
				g.setCpfGerente(rs.getString(5));
				
				garcons.add(g);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível listar!");
		}
		
		return garcons;
	}	
}















