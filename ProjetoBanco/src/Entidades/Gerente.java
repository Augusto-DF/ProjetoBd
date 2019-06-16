package Entidades;

public class Gerente extends Funcionario{

	public Gerente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gerente(String nome, String cpf, String dataNasc, String senha) {
		super(nome, cpf, dataNasc, senha);
		// TODO Auto-generated constructor stub
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

	public String getDatanasc() {
		return super.getDataNasc();
	}

	public void setDataNasc(String datanasc) {
		super.setDataNasc(datanasc);
	}

	public String getSenha() {
		return super.getSenha();
	}

	public void setSenha(String senha) {
		super.setSenha(senha);
	}
}
