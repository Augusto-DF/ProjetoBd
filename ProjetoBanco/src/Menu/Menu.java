package Menu;

import java.util.Scanner;

import DAO.ClienteDAO;
import DAO.CozinheiroDAO;
import DAO.GarcomDAO;
import DAO.GerenteDAO;
import Entidades.Cliente;
import Entidades.Cozinheiro;
import Entidades.Garcom;
import Entidades.Gerente;
import Operacoes.OperacoesCliente;
import Operacoes.OperacoesCozinheiro;
import Operacoes.OperacoesGarcom;
import Operacoes.OperacoesGerente;

public class Menu {
	
	private Scanner entrada;
	
	public Menu() {
		this.entrada = new Scanner(System.in);
	}
	
	public int exibirEscolhadeUsuario() {
		System.out.println("-----------------------------------------");
		System.out.println("Bem-Vindo a nosso servi�o de gerenciamento!");
		System.out.println("Que tipo de usu�rio � voc�?");
		System.out.println("1. Cliente");
		System.out.println("2. Gerente");
		System.out.println("3. Gar�om");
		System.out.println("4. Cozinheiro");
		System.out.println("5. N�o tem cadastro? Cadastre-se.");
		System.out.println("0. Sair do Programa");
		System.out.print("Resposta: ");
		int resposta = entrada.nextInt();
		System.out.println("-----------------------------------------");
		return resposta;
	}
	
	public void exibirMenu(int tipo) throws Exception {
		switch (tipo) {
		case 1:
			exibirMenuCliente();
			break;
		case 2:
			exibirMenuGerente();
			break;
		case 3:
			exibirMenuGarcom();
			break;
		case 4:
			exibirMenuCozinheiro();
			break;
		case 5:
			cadastroCliente();
			break;
		default:
			if(tipo == 0) {
				System.out.println("Bye! :D");
			} else {
				System.out.println("Por favor, insira um valor v�lido!");
				exibirMenu(exibirEscolhadeUsuario());
			}
			break;
		}
	}
	
	public void exibirMenuCliente() throws Exception {
		System.out.println("-----------------------------------------");
		System.out.println("Bem-Vindo, querido cliente!");
		System.out.println("Informe seu cpf (Somente N�meros)");
		System.out.print("Resposta: ");
		this.entrada.nextLine();
		String resposta = this.entrada.nextLine();
		ClienteDAO cdao = new ClienteDAO();
		Cliente c = cdao.buscarCPF(resposta);
		if(c != null) {
			System.out.println("Informe sua senha!");
			System.out.print("Resposta: ");
			String senha = entrada.nextLine();
			if(senha.equals(c.getSenha())) {
				System.out.println("Logado! :D");
				//Lista de opera��es do cliente:
				OperacoesCliente oc = new OperacoesCliente(c);
				oc.acoes(oc.listaOperacoes());
			} else {
				System.out.println("Senha incorreta! Inicie novamente!");
			}
		}
	}
	
	public void exibirMenuGerente() throws Exception {
		System.out.println("-----------------------------------------");
		System.out.println("Bem-Vindo, querido gerente!");
		System.out.println("Informe seu cpf (Somente N�meros)");
		System.out.print("Resposta: ");
		entrada.nextLine();
		String resposta = entrada.nextLine();
		GerenteDAO dao = new GerenteDAO();
		Gerente g = dao.buscarCPF(resposta);
		if(g != null) {
			System.out.println("Informe sua senha!");
			System.out.print("Resposta: ");
			String senha = entrada.nextLine();
			if(senha.equals(g.getSenha())) {
				System.out.println("Logado! :D");
				OperacoesGerente og = new OperacoesGerente(g);
				og.acoes(og.listaOperacoes());
			}else {
				System.out.println("Senha incorreta! Inicie novamente!");
			}
		} else {
			System.out.println("CPF n�o encontrado! Inicie novamente!");
		}		
	}
	
	public void exibirMenuGarcom() throws Exception {
		System.out.println("-----------------------------------------");
		System.out.println("Bem-Vindo, querido gar�om!");
		System.out.println("Informe seu cpf (Somente N�meros)");
		System.out.print("Resposta: ");
		entrada.nextLine();
		String resposta = entrada.nextLine();
		GarcomDAO dao = new GarcomDAO();
		Garcom g = dao.buscarCPF(resposta);
		if(g != null) {
			System.out.println("Informe sua senha!");
			System.out.print("Resposta: ");
			String senha = entrada.nextLine();
			if(senha.equals(g.getSenha())) {
				System.out.println("Logado! :D");
				OperacoesGarcom og = new OperacoesGarcom(g);
				og.acoes(og.listaOperacoes());
			}else {
				System.out.println("Senha incorreta! Inicie novamente!");
			}
		} else {
			System.out.println("CPF n�o encontrado! Inicie novamente!");
		}	
	}
	
	public void exibirMenuCozinheiro() throws Exception {
		System.out.println("-----------------------------------------");
		System.out.println("Bem-Vindo, querido cozinheiro!");
		System.out.println("Informe seu cpf (Somente N�meros)");
		System.out.print("Resposta: ");
		entrada.nextLine();
		String resposta = entrada.nextLine();
		CozinheiroDAO cdao = new CozinheiroDAO();
		Cozinheiro c = cdao.buscarCPF(resposta);
		if(c != null) {
			System.out.println("Informe sua senha!");
			System.out.print("Resposta: ");
			String senha = entrada.nextLine();
			if(senha.equals(c.getSenha())) {
				System.out.println("Logado! :D");
				OperacoesCozinheiro oc = new OperacoesCozinheiro(c);
				oc.acoes(oc.listaOperacoes());
			}else {
				System.out.println("Senha incorreta! Inicie novamente!");
			}
		} else {
			System.out.println("CPF n�o encontrado! Inicie novamente!");
		}		
	}
	
	public void cadastroCliente() throws Exception {
		Cliente c = new Cliente();
		Scanner r0 = new Scanner(System.in);
		Scanner r1 = new Scanner(System.in);
		Scanner r2 = new Scanner(System.in);
		Scanner r3 = new Scanner(System.in);
		System.out.println("Ser� necess�rio alguns dados: ");
		System.out.print("Informe seu cpf: ");
		c.setCpf(r0.nextLine());
		if(!c.getCpf().equals(null)) {
			System.out.print("Informe seu Nome: ");
			c.setNome(r1.nextLine());
			
			System.out.print("Informe sua Data de Nascimento: ");
			c.setDataNasc(r2.nextLine());
			
			System.out.print("Informe sua Senha: ");
			c.setSenha(r3.nextLine());
		}else {
			System.out.println("Cpf nulos s�o inv�lidos. Fazendo Logout...");
			exibirMenu(exibirEscolhadeUsuario());
		}
		
		ClienteDAO cdao = new ClienteDAO();
		if(cdao.buscarCPF(c.getCpf()) != null) {
			System.out.println("Cliente ja existe. Fazendo logout...");
			exibirMenu(exibirEscolhadeUsuario());
		}else {
			cdao.inserir(c);
			System.out.println("Cliente adicionado. Voltando a pagina inicial...");
			exibirMenu(exibirEscolhadeUsuario());
		}
	}
}
