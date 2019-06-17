package Main;

/*import Entidades.Cliente;
import DAO.ClienteDAO;*/

import Menu.Menu;

public class Main {
	public static void main(String[] args) throws Exception {
		
	/*	Cliente c = new Cliente("Mattheus", "08504802485", "19950205", "...");
		ClienteDAO cdao = new ClienteDAO();
		
		cdao.inserir(c);*/
		
		Menu m = new Menu();
		m.exibirMenu(m.exibirEscolhadeUsuario());
		
		
	}
}















