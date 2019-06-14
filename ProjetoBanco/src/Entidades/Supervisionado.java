package Entidades;

public class Supervisionado extends Funcionario {
	private String cpfGerente;
	
	public Supervisionado() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Supervisionado(Gerente _gerente, String nome, String cpf, int dataNasc, String senha) {
		super(nome, cpf, dataNasc, senha);
		this.cpfGerente = _gerente.getCpf();
	}
	
	public String getNome() {
		return super.getNome();
	}

	public void setNome(String nome) {
		super.setNome(nome);
	}

	public String getCpf() {
		return super.getCpf();
	}

	public void setCpf(String cpf) {
		super.setCpf(cpf);
	}

	public int getDatanasc() {
		return super.getDataNasc();
	}

	public void setDataNasc(int datanasc) {
		super.setDataNasc(datanasc);
	}

	public String getSenha() {
		return super.getSenha();
	}

	public void setSenha(String senha) {
		super.setSenha(senha);
	}
	
	public String getCpfGerente() {
		return cpfGerente;
	}
	
	public void setCpfGerente(String cpfGerente) {
		this.cpfGerente = cpfGerente;
	}
}
