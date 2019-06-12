package Main;

import DAO.PessoaDAO;
import Entidades.Pessoa;

public class Main {
	public static void main(String[] args) {
		
		Pessoa p = new Pessoa("Lucas Gabriellll", "11858409403", 19981017, "abbdd");
		PessoaDAO pdao = new PessoaDAO();
		try {
			pdao.atualizar(p);
		} catch (Exception e) {
			System.out.println("Erro ao inserir!");
			e.printStackTrace();
		}
		
	}
}
