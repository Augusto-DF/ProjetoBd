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
		
		ItensEstoque ie = new ItensEstoque(es, "Fanta Laranja", 200);
		ItemEstoqueDAO iedao = new ItemEstoqueDAO();
		
		Pedido pd = new Pedido(1, c, "Corpo");
		PedidoDAO pddao = new PedidoDAO();
		
		ItensPedido ip = new ItensPedido(pd, "Cerveja", 13.00, f, "Sem gas, com sabor de fanta mas sem fanta", es);
		ItensPedidoDAO ipdao = new ItensPedidoDAO();
		
		try {
			ipdao.inserir(ip);
			//pddao.atualizar(pd);
			//iedao.atualizar(ie);
			//esdao.atualizar(es);
			//cdao.atualizar(c);
			//gdao.inserir(gg);
			//fdao.atualizar(f);
		} catch (Exception e) {
			//System.out.println("Erro ao inserir!");
			e.printStackTrace();
		}
	}
}















