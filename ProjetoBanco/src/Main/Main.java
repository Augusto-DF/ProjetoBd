package Main;

import java.util.List;

import DAO.*;
import DAO.PessoaDAO;
import Entidades.*;

public class Main {
	public static void main(String[] args) {
		
		Gerente g = new Gerente("Menino Alfredo", "12345678998", "16/02/1980", "Familiazinha");
		Gerente gg = new Gerente("Menino Alfredo2", "12345678997", "16/02/1980", "Familiazinha");
		GerenteDAO gdao = new GerenteDAO();
		
		Garcom f = new Garcom(g,"Oswald Cobblepot2", "15628479310", "15/09/1990", "Pinguins");
		GarcomDAO fdao = new GarcomDAO();
		
		Cliente c = new Cliente("Jorjin2", "794613285305", "05/02/1995", "Treloso");
		ClienteDAO cdao= new ClienteDAO();
		
		Estoque es = new Estoque(123456789, gg.getCpf());
		EstoqueDAO esdao = new EstoqueDAO();
		
		ItensEstoque ie = new ItensEstoque(es, "Fanta Laranja", 200);
		ItemEstoqueDAO iedao = new ItemEstoqueDAO();
		
		Pedido pd = new Pedido(1, c.getCpf(), "Corpo");
		PedidoDAO pddao = new PedidoDAO();
		
		ItensPedido ip = new ItensPedido(pd.getIdPedido(), "Cerveja", 13.00, f.getCpf(), "Sem gas, com sabor de fanta mas sem fanta", es.getIdEstoque());
		ItensPedidoDAO ipdao = new ItensPedidoDAO();
		
		try {
			List<Cliente> clientes = cdao.listar();
			System.out.println(clientes);
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















