package beans;

public class BeanProdutos {
	
	private Long id;
	private String nome;
	private double quantidade;
	private double valor;
	private double valor_total;
	
	private Long categoria_id;
	
	
	

	
	
	public Long getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}

	public double getValor_total() {
		return valor * quantidade;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	// ABAIXO 2 STRINGS AUXILIAR PARA AJUDAR NA HORA DE EDITAR CAMPO DE VALORES EM DINHEIRO:
	
	public String getValorEmTexto() {
		return Double.toString(valor).replace('.', ','); // SUBSTITUI PONTO POR VÍRGULA
	}
	
	
	public String getValorEmTexto2() {
		
		
		return Double.toString(valor_total).replace('.', ','); // SUBSTITUI PONTO POR VÍRGULA
	}
	
	
	

}
