package Entidades;

public class ItensPedido {
	private int idItem;
	private int idPedido;
	private String item;
	private boolean preparado;
	private boolean entregue;
	private String cpfresponsavel;
	private String detalhes;
	private int idEstoque;
	private int quantidade;
	
	public ItensPedido(int idPedido, String item, String cpfresponsavel,
			String detalhes, int idEstoque,
			int quantidade) {
		super();
		this.idPedido = idPedido;
		this.item = item;
		this.preparado = false;
		this.entregue = false;
		this.cpfresponsavel = cpfresponsavel;
		this.detalhes = detalhes;
		this.idEstoque = idEstoque;
		this.quantidade = quantidade;
	}

	public ItensPedido() {
		// TODO Auto-generated constructor stub
	}	

	public int getPedido() {
		return idPedido;
	}

	public void setPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	public boolean isPreparado() {
		return preparado;
	}

	public void setPreparado(boolean preparado) {
		this.preparado = preparado;
	}

	public boolean isEntregue() {
		return entregue;
	}

	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}

	public String getResponsavel() {
		return cpfresponsavel;
	}

	public void setResponsavel(String cpfresponsavel) {
		this.cpfresponsavel = cpfresponsavel;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public int getEstoque() {
		return idEstoque;
	}

	public void setEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}	
}
