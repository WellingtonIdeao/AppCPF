package model;

public class Dado implements IDado {
	
	private String nome;
	private String cpf = "";
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
			this.cpf = cpf;
	}

	public boolean equals(Object dado) {
		return this.cpf.equals(((Dado) dado).getCpf());
		
	}

	public int compareTo(Object dado) {
		return this.cpf.compareTo(((Dado) dado).getCpf());
	}
	
	public String toString() {
		return cpf;
	}

	
}
