package Entidades;

public class Pedido {
	private Pedido idPedido;
	private Cliente cliente;
	private String formaPagamento;
	
	public Pedido() {
		super();
	}
	
	public Pedido(Pedido idPedido, Cliente cliente, String formaPagamento) {
		super();
		this.idPedido = idPedido;
		this.cliente = cliente;
		this.formaPagamento = formaPagamento;
	}

	public int getIdPedido() {
		return idPedido.getIdPedido();
	}

	public void setIdPedido(Pedido idPedido) {
		this.idPedido = idPedido;
	}

	public String getCliente() {
		return cliente.getCpf();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
}
