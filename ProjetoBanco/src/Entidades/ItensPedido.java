package Entidades;

public class ItensPedido {
	private Pedido pedido;
	private String item;
	private double valor;
	private boolean preparado;
	private boolean entregue;
	private Garcom responsavel;
	private String detalhes;
	private Estoque estoque;
	
	public ItensPedido(Pedido pedido, String item, double valor, 
			Garcom responsavel, String detalhes, Estoque estoque) {
		super();
		this.pedido = pedido;
		this.item = item;
		this.valor = valor;
		this.preparado = false;
		this.entregue = false;
		this.responsavel = responsavel;
		this.detalhes = detalhes;
		this.estoque = estoque;
	}

	public int getPedido() {
		return pedido.getIdPedido();
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
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
		return responsavel.getCpf();
	}

	public void setResponsavel(Garcom responsavel) {
		this.responsavel = responsavel;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public int getEstoque() {
		return estoque.getIdEstoque();
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}	
}
