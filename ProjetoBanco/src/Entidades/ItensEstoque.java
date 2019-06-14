package Entidades;

public class ItensEstoque {
	private Estoque estoque;
	private String produto;
	private int quantidade;
	
	public ItensEstoque() {
		super();
	}
	
	public ItensEstoque(Estoque estoque, String produto, int quantidade) {
		super();
		this.estoque = estoque;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public int getEstoque() {
		return estoque.getIdEstoque();
	}

	public void setEstoque(int idestoque) {
		estoque.setIdEstoque(idestoque);
	}

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
	
	
}
