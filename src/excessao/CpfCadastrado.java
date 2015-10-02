package excessao;

public class CpfCadastrado extends Exception {
	public CpfCadastrado(){
		super("CPF já cadastrado");
	}

}
