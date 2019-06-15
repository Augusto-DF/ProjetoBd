package Main;

import DAO.*;
import DAO.PessoaDAO;
import Entidades.*;

public class Main {
	public static void main(String[] args) {
		
		Gerente g = new Gerente("Menino Alfredo", "12345678998", 19800216, "Familiazinha");
		Gerente gg = new Gerente("Menino Alfredo2", "12345678997", 19800216, "Familiazinha");
		GerenteDAO gdao = new GerenteDAO();
		
		Garcom f = new Garcom(g,"Oswald Cobblepot2", "15628479310", 19900915, "Pinguins");
		GarcomDAO fdao = new GarcomDAO();
		
		Cliente c = new Cliente("Jorjin2", "79461328505", 19950205, "Treloso");
		ClienteDAO cdao= new ClienteDAO();
		
		Estoque es = new Estoque(123456789, gg);
		EstoqueDAO esdao = new EstoqueDAO();
		try {
			esdao.atualizar(es);
			//cdao.atualizar(c);
			//gdao.inserir(gg);
			//fdao.atualizar(f);
		} catch (Exception e) {
			//System.out.println("Erro ao inserir!");
			e.printStackTrace();
		}
	}
}















