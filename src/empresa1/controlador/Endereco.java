package empresa.controlador;

public class Endereco {

	private int pk;
	private int fk;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;
	
	public Endereco() {
	
	}
	
	public Endereco(int fk, String logadouro, String bairro, String cidade, String estado, String pais, String cep) {
		this.fk = fk;
		this.logradouro = logadouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
	}




	public Endereco(int pk, int fk, String logadouro, String bairro, String cidade, String estado, String pais,
			String cep) {
		super();
		this.pk = pk;
		this.fk = fk;
		this.logradouro = logadouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
	}




	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logadouro) {
		this.logradouro = logadouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "FuncionarioEndereco [pk=" + pk + ", fk_funcionario=" + fk + ", logadouro=" + logradouro
				+ ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", pais=" + pais + ", cep=" + cep
				+ "]";
	}
	
	/**
	 * imprime na saída as informações do objeto
	 */
	public void print() {
		System.out.println(this);
	}

	public int getFk() {
		return fk;
	}

	public void setFk(int fk) {
		this.fk = fk;
	}	
}
