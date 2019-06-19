package Entidades;

public class Pedido {
	private int idPedido;
	private String cpfcliente;
	private String formaPagamento;
	private boolean fecharPedido;
	
	public Pedido() {
		super();
		this.cpfcliente = null;
	}
	
	public Pedido(String cpfcliente, String formaPagamento) {
		super();
		this.cpfcliente = cpfcliente;
		this.formaPagamento = formaPagamento;
		this.fecharPedido = false;
	}
	
	public Pedido(int idPedido, String cpfcliente, String formaPagamento) {
		super();
		this.idPedido = idPedido;
		this.cpfcliente = cpfcliente;
		this.formaPagamento = formaPagamento;
		this.fecharPedido = false;
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

	public boolean isFecharPedido() {
		return fecharPedido;
	}

	public void setFecharPedido(boolean fecharPedido) {
		this.fecharPedido = fecharPedido;
	}
	
	
	
	
}
