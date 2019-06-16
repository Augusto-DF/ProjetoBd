package Entidades;

public class Estoque {
	private int idEstoque;
	private String cpfgerente;
	
	public Estoque() {
		
	}
	
	public Estoque(int idEstoque, String cpfgerente) {
		this.idEstoque = idEstoque;
		this.cpfgerente = cpfgerente;
	}
	
	public int getIdEstoque() {
		return this.idEstoque;
	}
	
	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}
	
	public String getGerente() {
		return cpfgerente;
	}
	
	public void setGerente(String cpfgerente) {
		this.cpfgerente = cpfgerente;
	}
}
