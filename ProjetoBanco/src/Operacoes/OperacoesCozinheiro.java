package Operacoes;

import java.util.Scanner;

import DAO.ItensPedidoDAO;
import Entidades.Cozinheiro;
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
		 * Confirma a Entrega
		 * Fecha a conta
		 * */
		
		System.out.println("-----------------------------------------");
		System.out.println("Bem-Vindo a nosso serviço de gerenciamento!");
		System.out.println("O que deseja fazer agora?");
		System.out.println("1. Confirmar Entrega");
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
				confirmaEntrega();
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
	
	public void confirmaEntrega() {
		/* 1 - Selecionar todos os itensPedido que o meu garçom é responsavel
		e que ja foi preparado */
		ItensPedidoDAO ipdao = new ItensPedidoDAO();
		ipdao.listarPorResponsavel(garcom.get);
		/* 2 - Verificar se esses itens ja foram preparados */
		
		/* 3 - Dos itens Preparados escolher 1 para entregar */
	}

}
