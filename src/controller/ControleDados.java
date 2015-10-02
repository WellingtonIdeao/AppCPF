package controller;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import persistencia.ArvoreVermelhoPreto;
import model.Dado;
import model.IDado;
import model.NoAVP;
import excessao.CpfInvalidoException;
import excessao.CpfNaoEncontrado;
import excessao.NomeInvalidoException;


public class ControleDados {
	private ArvoreVermelhoPreto tree = new ArvoreVermelhoPreto(); // arvore iniciada
	public static List<Dado> list = new ArrayList<>(); // arraylist dos dados da arvore
	
	
	public boolean insere(IDado dado) throws CpfInvalidoException, NomeInvalidoException{
		  Dado d = (Dado) dado;						// cast
		  boolean bool = false; 					// confirmação da inserção
		  
		  if(!validarNome(d.getNome()))
			 throw new NomeInvalidoException();
		  if(validarCPF(d.getCpf())){ 				// valida o cpf 		
			  this.tree.insere(dado);				// insere na arvore
			  bool = true;                         
			                             		
		  }else
			  throw new CpfInvalidoException();
		  
    	  return bool; 								// retorna a  confirmação da inserção
	}
	public void AtualizarArvore(){
		ControleDados.list.clear();
		if(this.tree.getRaizAVP()!= null) 												// se existir elementos exibi a arvore
			this.tree.exibirPosfixado();
	}
	
	public void remover(IDado dado) throws CpfNaoEncontrado, CpfInvalidoException{ 										
		Dado d = (Dado)dado;
		if(validarCPF(d.getCpf())){	 		   		// valida o cpf
			this.tree.remove(dado);
					 									
		}else
			throw new CpfInvalidoException(); 		// se o cpf nao for valido lança uma excessao.
	}
	
	public Dado procurarDado(Dado dado) throws CpfNaoEncontrado {
		
		NoAVP no = tree.procura(dado);
		if(no == null){
	        throw new CpfNaoEncontrado();
		}
		Dado d = (Dado)no.getIDado();
	    return d;
	}
	
	public boolean validarCPF(String cpf) throws CpfInvalidoException {
      
		if(cpf.length() == 11){
			for(int i = 0; i<cpf.length();i++){
			    if(!Character.isDigit(cpf.charAt(i)))
					throw new CpfInvalidoException();
			}
		return true;  
	    }
		return false;
	}
	public boolean validarNome(String nome){
		boolean bool = true;
		if(nome.isEmpty())
			return false;
		Pattern pattern = Pattern.compile("[0-9]");  
        Matcher matcher = pattern.matcher(nome);
		if(matcher.find()) { 	
			bool = false;
		}
		return bool;
		
	}
	
	
	public String formatarString(String cpf){
		return null;
		//TODO
		
	}	
}
