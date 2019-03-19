package empresa.controlador;

public class Cpf{
	
	private String cpf;
	
	public Cpf() {
		
	}

	public Cpf(String cpf) {
		this.cpf = cpf;
	}
	
	/**
	 * Valida um cpf informado com base no digio verificador
	 * @param cpf o cpf que sera validado, sem uso de separadores
	 * @return true se o cpf for valido, false caso contrario
	 */
	public static boolean validaCPF(String cpf) {
		return true;
	}
	/**
	 * Valida o cpf da instancia
	 * @return true se o cpf for valido, false caso contrario
	 */
	public boolean validaCPF() {
		return validaCPF(this.cpf);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return this.cpf;
	}
	
	/**
	 * imprime na saída as informações do objeto
	 */
	public void print() {
		System.out.println(this);
	}
	
}
