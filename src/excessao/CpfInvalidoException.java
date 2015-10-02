package excessao;

public class CpfInvalidoException extends Exception {
	public CpfInvalidoException(){
		super("Valor do cpf invalido");
	}
}
