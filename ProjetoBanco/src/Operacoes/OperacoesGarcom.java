package Operacoes;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.ItensPedidoDAO;
import Entidades.Garcom;
import Entidades.ItensPedido;
import Menu.Menu;

public class OperacoesGarcom {
	private Scanner entrada;
	private Garcom garcom;
	
	public OperacoesGarcom(Garcom garcom) {
		this.garcom = garcom;
		entrada = new Scanner(System.in);
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
	
	public void confirmaEntrega() throws Exception {
		/* 1 - Selecionar todos os itensPedido que o meu garçom é responsavel
		e que ja foi preparado */
		ItensPedidoDAO ipdao = new ItensPedidoDAO();
		ArrayList<ItensPedido> listaEntrega = new ArrayList();
		listaEntrega = ipdao.listarPorResponsavel(garcom.getCpf());
		
		System.out.println(garcom.getCpf());
		/* 2 - Dos itens Preparados escolher 1 para entregar */
		int target = -1;
		
		if(! listaEntrega.isEmpty()) {
			for(int i=0; i<listaEntrega.size(); ++i) {
				System.out.println("Opção: " + i + "| Item: " + 
				listaEntrega.get(i).getItem() + " x" + 
				listaEntrega.get(i).getQuantidade() + " | Pedido: " + 
				listaEntrega.get(i).getPedido());
			}
			
			Scanner escolha = new Scanner(System.in);
			System.out.println("Escolha o pedido para entregar: ");
			System.out.print("Resposta: ");
			target = escolha.nextInt();
			
			listaEntrega.get(target).setEntregue(true);
			ipdao.atualizar(listaEntrega.get(target));
		}
		
		ipdao.close();
		acoes(listaOperacoes());
	}
}
