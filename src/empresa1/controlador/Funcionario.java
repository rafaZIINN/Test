package empresa.controlador;

import java.util.ArrayList;

public class Funcionario {

	private int pk;
	private String nome;

	private Cpf cpf;
	private Cargo cargo;
	private ArrayList<Endereco> enderecos;

	public Funcionario() {
		enderecos = new ArrayList<>();
	}

	public Funcionario(int pk, String nome, Cpf cpf, Cargo cargo, ArrayList<Endereco> enderecos) {
		this.pk = pk;
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.enderecos = enderecos;
                enderecos = new ArrayList<>();
	}

	public Funcionario(String nome, Cpf cpf, Cargo cargo, ArrayList<Endereco> enderecos) {
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.enderecos = enderecos;
                enderecos = new ArrayList<>();
	}

	public Funcionario(String nome, Cpf cpf, Cargo cargo) {
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		enderecos = new ArrayList<>();
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

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public ArrayList<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(ArrayList<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
	/**
	 * imprime na saída as informações do objeto
	 * varre o vetor de endereços
	 */	
	public void print() {
		System.out.println(this);
		
/*		for (int i=0;i<enderecos.size();i++) {
			enderecos.get(i).print();//end[i]
		}
*/		

		for(Endereco aux : enderecos) {
			aux.print();
		}
	}
	
}
