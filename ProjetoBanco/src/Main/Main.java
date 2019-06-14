package Main;

import DAO.FuncionarioDAO;
import DAO.PessoaDAO;
import Entidades.*;

public class Main {
	public static void main(String[] args) {
		
		Funcionario f = new Funcionario("Oswald Cobblepot", "15628479304", 19900915, "Pinguins");
		FuncionarioDAO fdao = new FuncionarioDAO();
		try {
			fdao.inserir(f);
		} catch (Exception e) {
			//System.out.println("Erro ao inserir!");
			e.printStackTrace();
		}
	}
}
