package persistencia;

import model.IDado;
import model.NoAVP;
import excessao.CpfNaoEncontrado;



public class ArvoreVermelhoPreto {	
	 
	     private NoAVP raizAVP; 
		 private static final boolean VERMELHO = false; 
	     private static final boolean PRETO    = true;
	    
	     
	     
	     //////////////////// ÃRVORE \\\\\\\\\\\\\\\\\\\\\\\
	 
	 
	    


		/** 
	      * Construtor default ArvoreVermelhoPreto 
	      */ 
	     public ArvoreVermelhoPreto(){ 
	         raizAVP = null; 
	     } 
	 
	     
	     /** 
	      * Nova ArvoreBinariaPesquisa 
	      * Raiz da nova Arvore 
	      */ 
	     public ArvoreVermelhoPreto(NoAVP raiz){ 
	         raizAVP = raiz; 
	     } 
	     
	     public NoAVP getRaizAVP() {
			return raizAVP;
		 }
	 
	     /** 
	      * Imprimir a Arvore 
	      */ 
	     public void exibirArvore(){ 
	         if(this.raizAVP!=null){ 
	             this.raizAVP.exibirNos(); 
	         } 
	     } 
	     
	     public void exibirPrefixado() { 
		    if(this.raizAVP!=null){
		    	raizAVP.prefixado(raizAVP);
		    }		    
	    }
	     public void exibirPosfixado() { 
	    	 if(this.raizAVP!=null){
			    	raizAVP.posfixado(raizAVP);
			    }	
	     }
	     
	     public void exibirEmOrdem() { 
	    	 if(this.raizAVP!=null){
			    	raizAVP.emOrdem(raizAVP);
			    }	
	    	 
	     }
	 
	     /** 
	      * Metodo que apenas troca dois elementos na Ã¡rvore NoAVP b com NoAVP a 
	      * 
	      */ 
	     private void trocaElementosArvore(NoAVP a, NoAVP b){ 
	         // Se a Ã© raiz da Ã�rvore 
	         if( a.getRaizNo() == null ) { 
	             raizAVP = b; 
	         } 
	         // O pai de a passa a apontar para o b (verificar de que lado a Ã© filha) 
	         else if( a == a.getRaizNo().getEsqNo() ) { 
	             a.getRaizNo().setEsqNo(b); 
	         } 
	 
	 
	         else { 
	             a.getRaizNo().setDirNo(b); 
	         } 
	         // atualiza o pai de b ( depois de efetua as trocas) 
	         if( b != null ) { 
	             b.setRaizNo(a.getRaizNo()); 
	         } 
	     } 	 
	 
	     public void insere(IDado dado){
	         if(raizAVP==null){ 
	             raizAVP = new NoAVP(dado); 
	             corrigeArvoreInserir(raizAVP);
	             
	 
	         } 
	         else{ 
	             insereNo(raizAVP,dado); 
	         } 
	     } 	     
	 
	     private void insereNo(NoAVP NoAVP,IDado dado){ 	 
	 
	         if(!dado.equals(NoAVP.getIDado())){             
	        	 if(dado.compareTo(NoAVP.getIDado()) < 0 ){ 
	                 if(NoAVP.getEsqNo()==null){ 
	                     NoAVP.setEsqNo(new NoAVP(NoAVP,dado,VERMELHO)); 
	                     corrigeArvoreInserir(NoAVP.getEsqNo()); 
	                 } 
	                 else{ 
	                     insereNo(NoAVP.getEsqNo(), dado); 
	                 } 
	             } 
	             else{ 
	                 if(NoAVP.getDirNo() == null){ 
	                     NoAVP.setDirNo(new NoAVP(NoAVP,dado,VERMELHO)); 
	                     corrigeArvoreInserir(NoAVP.getDirNo()); 
	 
	 
	                 } 
	                 else{ 
	                     insereNo(NoAVP.getDirNo(), dado); 
	                 } 
	             } 
	         } 
	     } 
	     
	     public void remove(IDado dado) throws CpfNaoEncontrado{
	    	 removeNo(raizAVP, dado);
	     }
	     
	     private void removeNo( NoAVP NoAVP, IDado dado ) throws CpfNaoEncontrado { 	 
	 
	         // Nao encontra o nó a eliminar 
	         if( NoAVP == null ) { 
	             throw new CpfNaoEncontrado(); 
	         } 
	         // Encontramos o nó a eliminar: 
	         else if ( dado.equals(NoAVP.getIDado())) { 
	 
	 
	             // Verificar se tem filho esquerdo 
	             if( NoAVP.getEsqNo() == null ) { 
	                 // Como nÃ£o tem trocamos o filho direito com o prÃ³prio nÃ³ (apagando assim o desejado) 
	                 // Troca simples 
	                 // Se for folha, dirNo vai a null e Ã© eliminado na mesma 	 
	 
	                 trocaElementosArvore( NoAVP, NoAVP.getDirNo() ); 
	                 if(NoAVP.getRaizNo()!=null){ 
	                     corrigeArvoreInserir(NoAVP); 
	                 } 	 
	 
	             } 
	             //Verificar se tem  um filho direito 
	             else if( NoAVP.getDirNo() == null ) { 
	 
	 
	                 trocaElementosArvore( NoAVP, NoAVP.getEsqNo() ); 
	                 if(NoAVP.getRaizNo()!=null){ 
	                     corrigeArvoreInserir(NoAVP); 
	                 } 
	 
	 
	             } 
	           
	             //Caso 3: 
	             //  - O NÃ³ tem 2 filhos -> procurar o sucessor. 
	             //  - O sucessor Ã© o nÃ³ mais Ã  esquerda na subÃ¡rvore direita 
	             //  - Trocamos o nÃ³ encontrado com o a eliminar 	 
	 
	             else { 
	                 // minNoAVP Ã© o filho mais Ã  direita 
	 
	 
	                 NoAVP minNoAVP = NoAVP.getDirNo(); 
	                 // A partir de agora procurar a folha mais Ã  esquerda 
	                 while( minNoAVP.getEsqNo() != null ) { 
	                     minNoAVP = minNoAVP.getEsqNo(); 
	                 } 	 
	 
	                 //Aqui temos em minNoAVP o No a trocar com o que queremos eliminar 
	                 if( minNoAVP.getRaizNo() != NoAVP ) { 
	                     // O nÃ³ encontrado nÃ£o Ã© filho directo do nosso NoAVP 
	                     trocaElementosArvore( minNoAVP, minNoAVP.getDirNo() ); 
	                     // trocar o nosso minNoAVP com o NoAVP a eliminar// ligaÃ§Ãµes do lado direito 
	                     minNoAVP.setDirNo(NoAVP.getDirNo()); 
	                     minNoAVP.getDirNo().setRaizNo(minNoAVP); 
	                 } 	 
	 
	                 // aqui eliminamos o NoAVP 
	                 trocaElementosArvore( NoAVP, minNoAVP ); 
	                 // definir o lado esquerdo do minNoAVP ( que ocupa o lugar de NoAVP) 
	                 minNoAVP.setEsqNo(NoAVP.getEsqNo()); 
	                 minNoAVP.getEsqNo().setRaizNo(minNoAVP); 
	                 corrigeArvoreInserir(minNoAVP); 
	 
	             } 	 
	 
	         } 
	         // Continuar a procurar na subarvore esquerda 
	         else if( dado.compareTo(NoAVP.getIDado()) < 0) { 
	             removeNo( NoAVP.getEsqNo(), dado ); 
	         } 
	         // Continuar a procurar na subarvore direita 
	         else { 
	             removeNo( NoAVP.getDirNo(), dado ); 
	         } 
	     } 
	 
	     public NoAVP procura(IDado dado){
	    	 return procuraElemento(raizAVP, dado);
	     }
	     
	     public boolean altera(IDado dado){
	    	 NoAVP aux = procura(dado);
	    	 if(aux!=null){
	    		 aux.setIDado(dado);	    	 
	    	 }
	    		return false; 
	     }
	     
	     /* 
	      * Procura na Arvore até encontrar o elemento desejado recursivamente 
	      *  getRaizNo()AVP 'raiz' do nÃ³ a procurar 
	      *  dado(String) Ã© a chave para a procura 
	      */ 
	     private NoAVP procuraElemento(NoAVP no, IDado dado){ 
	         if(no==null){ 
	             return null; 
	         } 
	         else{ 
	             if(dado.equals(no.getIDado())){ 
	                 return no; 
	             } 
	             else if(dado.compareTo(no.getIDado()) < 0){ 
	                 return procuraElemento(no.getEsqNo(),dado); 
	             } 
	             else{ 
	                 return procuraElemento(no.getDirNo(), dado); 
	             } 
	 
	 
	         } 
	     } 
	 
	 	
	
	
	/*****************************************************************************/ 
    /**                             MÃ‰TODOS ARVORE VERMELHA PRETA               **/ 
	/*****************************************************************************/ 
	 
	 
	 
	 
	     /** 
	      * 
	      *  NoAVP a fazer rotacao a esquerda 
	      */ 
	     private void rotacaoEsq(NoAVP NoAVP){ 	 
	 
	         NoAVP aux = NoAVP.getDirNo(); 
	         trocaElementosArvore(NoAVP, aux); 		 
	         NoAVP.setDirNo(aux.getEsqNo()); 	 
	 
	         if (aux.getEsqNo() != null) 
	             aux.getEsqNo().setRaizNo(NoAVP); 	 
	 
	         aux.setEsqNo(NoAVP); 
	         NoAVP.setRaizNo(aux); 
	     } 
	 
	 
	     private void rotacaoDir(NoAVP NoAVP){ 	 
	 
	         NoAVP aux = NoAVP.getEsqNo(); 
	         trocaElementosArvore(NoAVP,aux); 
	         NoAVP.setEsqNo(aux.getDirNo()); 	 
	 
	         if (aux.getDirNo() != null){ 
	             aux.getDirNo().setRaizNo(NoAVP); 
	         } 
	         aux.setDirNo(NoAVP); 
	         NoAVP.setRaizNo(aux); 
	     } 
	 
	 
	 
	 
	     private void corrigeArvoreInserir(NoAVP no){ 
	         // Se for a raiz, mudamos a cor para Preto 
	         if(no == raizAVP){ 
	             no.setCor(PRETO); 
	         } 
	         // Se o pai for preto, fica como esta 
	         else if (no.getRaizNo().isCor() == PRETO){ 
	             return; 
	         } 
	         else if( no.getRaizNo().isCor() == VERMELHO){ 
	          
	             if(no.getTio()!= null &&  no.getTio().isCor() == VERMELHO){ 
	                 no.getRaizNo().setCor(PRETO); 
	                 no.getTio().setCor(PRETO); 
	                 no.getAvo().setCor(VERMELHO); 
	                 corrigeArvoreInserir(no.getAvo()); 
	             } 
	 
	 
	             else{ 
	                 
	                 // rotacao a esquerda 
	                 if(no == no.getRaizNo().getDirNo() && (no.getRaizNo() == no.getAvo().getEsqNo())){ 
	                     rotacaoEsq(no.getRaizNo()); 
	                     no = no.getEsqNo(); 
	                 } 
	                 // rotacao na direita 
	                 else if (no == no.getRaizNo().getEsqNo() && (no.getRaizNo() == no.getAvo().getDirNo())){ 
	                     rotacaoDir(no.getRaizNo()); 
	                     no = no.getDirNo(); 
	                 } 
	 
	 
	                 no.getRaizNo().setCor(PRETO); 
	                 if(no.getAvo()!=null){ 
	                     no.getAvo().setCor(VERMELHO); 
	                 } 
	 
	 
	                 if ((no == no.getRaizNo().getEsqNo()) && (no.getRaizNo() == no.getAvo().getEsqNo())) 
	                     rotacaoDir(no.getAvo()); 
	 
	 
	                 else if ((no == no.getRaizNo().getDirNo()) && (no.getRaizNo()== no.getAvo().getDirNo())) 
	                     rotacaoEsq(no.getAvo()); 
	             } 
	 
	 
	         } 
	         if(raizAVP.isCor()!=PRETO){ 
	             corrigeArvoreInserir(no.getRaizNo()); 
	         } 
	     }
	 	
}
