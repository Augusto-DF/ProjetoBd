package Main;

import DAO.PessoaDAO;
import Entidades.Pessoa;

public class Main {
	public static void main(String[] args) {
		
		Pessoa p = new Pessoa("Lucas Gabriel", "11858409403", 19981017, "abbdd");
		PessoaDAO pdao = new PessoaDAO();
		try {
			pdao.inserir(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
