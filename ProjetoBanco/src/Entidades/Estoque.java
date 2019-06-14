package Entidades;

public class Estoque {
	private int idEstoque;
	private Gerente gerente;
	
	public Estoque() {
		
	}
	
	public Estoque(int idEstoque, Gerente gerente) {
		this.idEstoque = idEstoque;
		this.gerente = gerente;
	}
	
	public int getIdEstoque() {
		return this.idEstoque;
	}
	
	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}
	
	public String getGerente() {
		return gerente.getCpf();
	}
	
	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
}
