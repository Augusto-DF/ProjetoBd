package Operacoes;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.ItensPedidoDAO;
import Entidades.Cozinheiro;
import Entidades.ItensPedido;
import Menu.Menu;

public class OperacoesCozinheiro {
	private Cozinheiro cozinheiro;
	private Scanner entrada;
	
	public OperacoesCozinheiro(Cozinheiro cozinheiro) {
		this.cozinheiro = cozinheiro;
		this.entrada = new Scanner(System.in);
	}
	
	public int listaOperacoes() {
		/*
		 * Confirma q o prato ta preparado
		 * Fecha a conta
		 * */
		
		System.out.println("-----------------------------------------");
		System.out.println("Bem-Vindo a nosso serviço de gerenciamento!");
		System.out.println("O que deseja fazer agora?");
		System.out.println("1. Confirmar Preparo do Pedido");
		//System.out.println("2. Fechar conta");
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
				confirmaPreparo();
				break;
				
/*			case 2:
				exibirCardapio();
				acoes(listaOperacoes());
				break;
			*/
			default:
				if(acao == 0) {
					menu.exibirMenu(menu.exibirEscolhadeUsuario());
				} else {
					System.out.println("Por favor, insira um valor válido!");
					acoes(listaOperacoes());
				}
		}
	}
	
	public void confirmaPreparo() throws Exception {
		/* 1 - Selecionar todos os itensPedido que não foram entregues nem preparados */
		ItensPedidoDAO ipdao = new ItensPedidoDAO();
		ArrayList<ItensPedido> listaPreparar = new ArrayList();
		listaPreparar = ipdao.listarPorPreparo();
		
		/* 2 - Dos itens Preparados escolher 1 para entregar */
		int target = -1;
		if(! listaPreparar.isEmpty()) {
			for(int i=0; i<listaPreparar.size(); ++i) {
				System.out.println("Opção: " + i + "| Item: " + 
						listaPreparar.get(i).getItem() + " x" + 
						listaPreparar.get(i).getQuantidade() + " | Pedido: " + 
						listaPreparar.get(i).getPedido());
			}
			Scanner escolha = new Scanner(System.in);
			System.out.println("Escolha o pedido para entregar: ");
			System.out.print("Resposta: ");
			target = escolha.nextInt();
			
			listaPreparar.get(target).setPreparado(true);
			ipdao.atualizar(listaPreparar.get(target));
		}
		ipdao.close();
		acoes(listaOperacoes());
	}

}
