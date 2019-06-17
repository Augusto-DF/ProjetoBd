package Entidades;

public class ItensEstoque {
	private Estoque estoque;
	private String produto;
	private int quantidade;
	private double valor;
	
	public ItensEstoque() {
		super();
	}
	
	public ItensEstoque(Estoque estoque, String produto, int quantidade, double valor) {
		super();
		this.estoque = estoque;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public int getEstoque() {
		return estoque.getIdEstoque();
	}

	public void setEstoque(int idestoque) {
		estoque.setIdEstoque(idestoque);
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
