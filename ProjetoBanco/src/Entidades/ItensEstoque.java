package Entidades;

public class ItensEstoque {
	private int estoque;
	private String produto;
	private int quantidade;
	private double valor;
	
	public ItensEstoque() {
		super();
	}
	
	public ItensEstoque(int estoque, String produto, int quantidade, double valor) {
		super();
		this.estoque = estoque;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	
	/*
	 public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	 * */

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
