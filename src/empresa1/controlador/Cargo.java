package empresa.controlador;

public class Cargo {
	
	private int pk;
	private String nome;
	private String descricao;
	
	public Cargo() {
	
	}

	public Cargo(int pk, String nome, String descricao) {
		super();
		this.pk = pk;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	
	public Cargo(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {	
		return nome;
	}
	
	/**
	 * imprime na saída as informações do objeto
	 */
	public void print() {
		System.out.println(this);
	}
	
}
