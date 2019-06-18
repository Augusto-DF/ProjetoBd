package Operacoes;

import java.awt.List;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.GarcomDAO;
import DAO.ItemEstoqueDAO;
import DAO.ItensPedidoDAO;
import DAO.PedidoDAO;
import Entidades.Cliente;
import Entidades.ItensPedido;
import Entidades.Pedido;
import Entidades.ItensEstoque;
import Menu.Menu;

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
		System.out.println("3. Pedir a conta");
		System.out.println("0. Logout");
		System.out.print("Resposta: ");
		int resposta = entrada.nextInt();
		System.out.println("-----------------------------------------");
		return resposta;
	}
	
	public void acoes(int acao) throws Exception {
		Menu menu = new Menu();
		switch (acao) {
			case 1:
				fazerPedido();
				break;
				
			case 2:
				exibirCardapio();
				acoes(listaOperacoes());
				break;
			
			case 3:
				pedirConta();
				break;
			default:
				if(acao == 0) {
					menu.exibirMenu(menu.exibirEscolhadeUsuario());
				} else {
					System.out.println("Por favor, insira um valor válido!");
					acoes(listaOperacoes());
				}
		}
	}
	
	public void fazerPedido() throws Exception {
		PedidoDAO pedidodao = new PedidoDAO();
		
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
		exibirCardapio();		
		
		while(/*item != "n"*/perguntasPedido(pedido) == true) {
			//perguntasPedido(pedido);			
		}
		
		System.out.println("Pedido indexado.");
		pedidodao.close();
		acoes(listaOperacoes());
	}
	
	public boolean perguntasPedido(Pedido pedido) throws Exception {
		
		ItensPedidoDAO ipdao = new ItensPedidoDAO();
		ItemEstoqueDAO iedao = new ItemEstoqueDAO();
		GarcomDAO gdao = new GarcomDAO();
		
		String item = "";
		String detalhes = "nenhum";
		int quantidade = 0;
		
		Scanner resp1 = new Scanner(System.in);
		Scanner resp2 = new Scanner(System.in);
		Scanner resp3 = new Scanner(System.in);
		
		System.out.println("Qual dos itens acima você deseja? (Digite n para"
				+ " caso não queira adicionar nada ao seu pedido)");
		System.out.print("Resposta: ");
		item = resp1.nextLine();
		System.out.println("---------------------------------");
		
		if(! item.equals("n")) {
			System.out.println("Quantas unidades de " +
					item + " você deseja?");
			System.out.print("Resposta: ");
			quantidade = resp2.nextInt();
			System.out.println("---------------------------------");
			
			System.out.println("Deseja adicionar algum detalhe ao pedido? Qual? (Não digite nada e "
					+ "aperte enter caso não queira adicionar nada ao seu pedido)");
			System.out.print("Resposta: ");
			detalhes = resp3.nextLine();
			System.out.println("---------------------------------");
			
			if(iedao.verificaItem(item)) {
				ItensPedido ip = new ItensPedido(
						pedido.getIdPedido(),
						item,
						gdao.buscaGarcomLivre().getCpf(),
						detalhes,
						iedao.listar().get(0).getEstoque(),
						quantidade);
				
				ipdao.inserir(ip);
				
				ipdao.close();
				iedao.close();
				gdao.close();
				
				return true;
			} else if (item.equals("n")) {
				ipdao.close();
				iedao.close();
				gdao.close();
				return false;
			}
			else {
				System.out.println("Opção não disponível tente novamente: ");
				
				ipdao.close();
				iedao.close();
				gdao.close();
				
				return true;
			}
		}
		
		ipdao.close();
		iedao.close();
		gdao.close();
		
		return false;
	}	
	
	public void exibirCardapio() throws Exception {
		ItemEstoqueDAO iedao = new ItemEstoqueDAO();
		ArrayList<ItensEstoque> cardapio = new ArrayList();
		cardapio = iedao.listar();
		
		System.out.println("---------------------------------");
		System.out.println("CARDÁPIO: ");

		for(int i = 0; i < cardapio.size(); ++i) {
			System.out.println(cardapio.get(i).getProduto() + "....." +
					cardapio.get(i).getValor()+ " R$");
		}
		System.out.println("---------------------------------");
		
		acoes(listaOperacoes());
	}
	
	public void pedirConta() throws Exception {
		/*1 - Selecionar o pedido que não tenha sido fechado (0)
		e referente ao cliente em questão e armazenar em um pedido.*/  
		 PedidoDAO pedidodao = new PedidoDAO();
		 Pedido p = pedidodao.buscarPedido(cliente.getCpf());
		 
		
		/* 2 - Guardar em uma lista de itensPedido todos os pedidos 
		 feitos por aquele id.*/
		ItensPedidoDAO ipdao = new ItensPedidoDAO();
		ArrayList<ItensPedido> listaPedido = new ArrayList();
		listaPedido = ipdao.listarPorId(p.getIdPedido());
		
		
		/* 3 - Lista os itens do estoque*/
		ItemEstoqueDAO iedao = new ItemEstoqueDAO();
		int cont = 0;
		ArrayList<ItensEstoque> listaEstoque = new ArrayList();
		listaEstoque = iedao.listar();
		
		
		/* 4 - Soma a quantidade de itens de cada tipo e multiplica pelo 
		 valor.*/
		double total = 0.0;
		double preco = 0.0;
		int quantidade = 0;
		
		for(int i=0; i<listaEstoque.size(); ++i) {
			preco = listaEstoque.get(i).getValor();
			for(int j=0; j<listaPedido.size(); ++j) {
				if(listaEstoque.get(i).getProduto().equals(listaPedido.get(j).getItem())) {
					quantidade = listaPedido.get(j).getQuantidade();
					total = total + (quantidade * preco);
				}				
			}
		}
		System.out.println("CONTA: ");
		System.out.println("O valor a ser pago é: " + total);
		System.out.println("-----------------------------");
		
		/* 5 - Volta ao menu */
		acoes(listaOperacoes());
	}
}
















