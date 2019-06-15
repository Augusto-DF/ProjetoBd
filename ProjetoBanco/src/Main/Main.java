package Main;

import DAO.*;
import DAO.PessoaDAO;
import Entidades.*;

public class Main {
	public static void main(String[] args) {
		
		Gerente g = new Gerente("Menino Alfredo", "12345678998", 19800216, "Familiazinha");
		GerenteDAO gdao = new GerenteDAO();
		
		Cozinheiro f = new Cozinheiro(g,"Oswald Cobblepot", "15628479304", 19900915, "Pinguins");
		CozinheiroDAO fdao = new CozinheiroDAO();
		try {
			gdao.atualizar(g);
			//fdao.inserir(f);
		} catch (Exception e) {
			//System.out.println("Erro ao inserir!");
			e.printStackTrace();
		}
	}
}
