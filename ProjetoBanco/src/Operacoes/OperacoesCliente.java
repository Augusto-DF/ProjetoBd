package Operacoes;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.ItemEstoqueDAO;
import DAO.ItensPedidoDAO;
import DAO.PedidoDAO;
import Entidades.Cliente;
import Entidades.ItensPedido;
import Entidades.Pedido;
import Entidades.ItensEstoque;

public class OperacoesCliente {
	
	private Scanner entrada;
	private Cliente cliente;
	
	public OperacoesCliente(Cliente cliente) {
		this.entrada = new Scanner(System.in);
		this.cliente = cliente;
	}
	
	public int listaOperacoes() {
		/*
		 * Fazer um pedido
		 * Acessar o cardapio (Itens do Estoque)
		 * Fechar pedido
		*/
		System.out.println("-----------------------------------------");
		System.out.println("Bem-Vindo a nosso serviço de gerenciamento!");
		System.out.println("O que deseja fazer agora?");
		System.out.println("1. Fazer pedido");
		System.out.println("2. Ver cardapio");
		System.out.println("3. Fechar pedido");
		System.out.println("0. Sair do Programa");
		System.out.print("Resposta: ");
		int resposta = entrada.nextInt();
		System.out.println("-----------------------------------------");
		return resposta;
	}
	
	public void acoes(int acao) throws Exception {
		switch (acao) {
			case 1:
				fazerPedido();
				break;
			default:
				if(acao == 0) {
					System.out.println("Bye! :D");
				} else {
					System.out.println("Por favor, insira um valor válido!");
					acoes(listaOperacoes());
				}
		}
	}
	
	public void fazerPedido() throws Exception {
		PedidoDAO pedidodao = new PedidoDAO();
		ItemEstoqueDAO iedao = new ItemEstoqueDAO();
		ItensPedidoDAO ipdao = new ItensPedidoDAO();
		
		Pedido pedido = null;
		//Se o cpf de cliente não existir na tabela de pedido:
		if(pedidodao.buscarPedido(cliente.getCpf()) == null ){
			Scanner resp = new Scanner(System.in);
			
			System.out.println("Qual a forma do pagamento que deseja? ");
			System.out.print("Resposta: ");
			
			String resposta = resp.nextLine();
			pedido = new Pedido(cliente.getCpf(), resposta);		
			
			pedidodao.inserir(pedido);
			
			/*Antes esse meu pedido não tinha acesso ao idPedido
			porque está em Auto-increment então eu não
			entrava com id nenhum*/
			pedido = pedidodao.buscarPedido(cliente.getCpf());
		} else {
			pedido = pedidodao.buscarPedido(cliente.getCpf());			
		}
				
		ArrayList<ItensEstoque> cardapio = new ArrayList();
		cardapio = iedao.listar();
		
		for(int i = 0; i < cardapio.size(); ++i) {
			System.out.println(cardapio.get(i).getProduto() + "....." +
					cardapio.get(i).getValor()+ " R$");
		}
		
		String item = null;
		String detalhes = "nenhum";
		int quantidade = 0;
		
		while(item != "") {
			System.out.println("Qual dos itens acima você deseja? (Não digite nada e "
					+ "aperte enter caso não queira adicionar nada ao seu pedido)");
			System.out.print("Resposta: ");
			item = entrada.nextLine();
			
			System.out.println("Deseja adicionar algum detalhe ao pedido? Qual? (Não digite nada e "
					+ "aperte enter caso não queira adicionar nada ao seu pedido)");
			System.out.print("Resposta: ");
			detalhes = entrada.nextLine();
			
			System.out.println("Quantas unidades de " +
			item + " você deseja?");
			System.out.print("Resposta: ");
			quantidade = entrada.nextInt();
			
			if(iedao.verificaItem(item)) {
				ItensPedido ip = new ItensPedido(
						pedido.getIdPedido(),
						item,
						detalhes,
						cardapio.get(0).getEstoque(),
						quantidade);
				
				ipdao.inserir(ip);
			} else {
				System.out.println("Opção não disponível tente novamente: ");
			}
			
		}
		
	}
}
















