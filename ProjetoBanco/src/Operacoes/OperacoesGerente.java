package Operacoes;

import java.util.Scanner;

import DAO.EstoqueDAO;
import DAO.ItemEstoqueDAO;
import Entidades.Estoque;
import Entidades.Gerente;
import Entidades.ItensEstoque;
import Menu.Menu;

public class OperacoesGerente {
	private Gerente gerente;
	private Scanner entrada;
	
	public OperacoesGerente(Gerente gerente) {
		this.gerente = gerente;
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
		System.out.println("1. Adicionar item no estoque");
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
				inserirItem();
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
	
	public void inserirItem() throws Exception {
		ItemEstoqueDAO iedao = new ItemEstoqueDAO();
		//Veririfica se o estoque existe
		int idEstoque = 0;
		if(iedao.listar().isEmpty()) {
			EstoqueDAO edao = new EstoqueDAO();
			Estoque e = new Estoque(1, gerente.getCpf());
			edao.inserir(e);
			idEstoque = iedao.listar().get(0).getEstoque();
		}else {
			idEstoque = iedao.listar().get(0).getEstoque();
		}
		
		String resp = "";
		Scanner r = new Scanner(System.in);
		
		Scanner r1 = new Scanner(System.in);
		Scanner r2 = new Scanner(System.in);
		Scanner r3 = new Scanner(System.in);
		
		String nome ="";
		int qtd =0;
		double preco=0.0;
		
		while(!resp.equals("n")) {
			System.out.println("Deseja adicionar um item? (Digite 'n' caso não deseje)");
			resp = r.nextLine();
			
			if(!resp.equals("n")) {
				System.out.print("Digite o nome do item: ");
				nome = r1.nextLine();
				System.out.print("Digite a quantidade do item: ");
				qtd = r2.nextInt();
				System.out.print("Digite o valor do item: (use virgula caso tenha casas decimais)");
				preco = r3.nextDouble();
				
				ItensEstoque ie = new ItensEstoque(idEstoque,nome,qtd,preco);
				iedao.inserir(ie);
				System.out.println("Item inserido no estoque");
			}
		}
	}
}
