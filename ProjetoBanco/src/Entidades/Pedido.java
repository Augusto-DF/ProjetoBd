package Entidades;

public class Pedido {
	private int idPedido;
	private String cpfcliente;
	private String formaPagamento;
	
	public Pedido() {
		super();
	}
	
	public Pedido(int idPedido, String cpfcliente, String formaPagamento) {
		super();
		this.idPedido = idPedido;
		this.cpfcliente = cpfcliente;
		this.formaPagamento = formaPagamento;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getCliente() {
		return cpfcliente;
	}

	public void setCliente(String cpfcliente) {
		this.cpfcliente = cpfcliente;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
}
