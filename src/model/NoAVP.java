package model;
import controller.ControleDados;




public class NoAVP {

	 	
	    private IDado dado; 
	    private NoAVP esqNo; 
	    private NoAVP dirNo; 
	    private NoAVP raizNo; 
	  
	    private boolean cor; 			// false -> Vermelho || true -> Preto
                                    

	    //////////////////  Estrutura da Arvore  \\\\\\\\\\\\\\\\\\\\

	    public  NoAVP ( IDado dado) {        
	        this. dado 			=  dado;
	        this. esqNo        	=  null;
	        this. dirNo       	=  null;
	        this. raizNo        =  null;
	    }

	    public  NoAVP ( NoAVP pai, IDado dado, boolean cor) {   	 
	        this. esqNo        	=  	null;
	        this. dirNo       	=  	null;
	        this. raizNo        = 	pai;
	        this. dado   		=	dado;
	        this.cor            =	cor;
	    }

	   
	    public  NoAVP  getAvo () {
	        if ((this. raizNo != null) && (this. raizNo. raizNo!= null)) {  
	            return  this. raizNo. raizNo;
	        }
	        return  null;
	    }

	    /**
	     *
	     *return No Irmao | nulo
	     */
	    public  NoAVP  getIrmao() {
	        if (this. raizNo != null) { 
	            if (this == this. raizNo. esqNo) {  
	                return  this. raizNo. dirNo;
	            }
	            else {
	                return  this. raizNo. esqNo;
	            }
	        }
	        return  null;
	    }

	    /**
	     *
	     *return No tio | nulo
	     */
	    public  NoAVP  getTio() {

	        if ((this. raizNo!= null) && (this. raizNo. raizNo!= null)) {  
	            return raizNo. getIrmao ();
	        }
	        return  null;
	    }

	    public  void  atualizaCpf (IDado dado) { 
	        this. dado = dado;
	    }

	    /**
	     * Imprimir NIF do NoAVP
	     */
	    public  void  exibirNos () {
	        if (this. esqNo!= null) {
	            this. esqNo. exibirNos ();

	        }
	        StringBuffer dado = new StringBuffer();
	        dado.append("Cpf: ");
	        dado.append(toString());
	        dado.append("\tCor: ");
	        dado.append(exibirCor());
	        System.out.println(dado);

	        if (this. dirNo!= null) {
	            this. dirNo. exibirNos ();
	        }
	    }
	    

	     public void prefixado(NoAVP no) { 
		    	if(no != null){
		    		System.out.print(no.dado + " " + no.exibirCor() +" | ");
		    		prefixado(no.esqNo); 
		    		prefixado(no.dirNo); 		    
		    	} 
		    
	    }
	     public Dado posfixado(NoAVP no) { 
	    	 if(no != null){ 
	    		 posfixado(no.esqNo); 
	    		 posfixado(no.dirNo); 
	    		 ControleDados.list.add((Dado) no.getIDado());
	    	 }
			return null;
	    	 
	     }
	     
	     public void emOrdem(NoAVP no) { 
	    	 if(no != null){ 
	    		 emOrdem(no.esqNo); 
	    		 System.out.print(no.dado + " " + no.exibirCor() +" | "); 
	    		 emOrdem(no.dirNo); 
	    	 } 
	    	 
	     }
	     
	     public boolean equals(String dado){
	    	return raizNo.dado.equals(dado);
	     }


	    public  String  exibirCor () {
	        if (!this. cor) { 
	            return "VERMELHO";
	        }
	        else {
	            return  "PRETO";
	        }
	    }

	    public  String  toString () {
	            return  dado.toString();
	    }

		public IDado getIDado() {
			return dado;
		}

		public void setIDado(IDado dado) {
			this.dado = dado;
		}

		public NoAVP getEsqNo() {
			return esqNo;
		}

		public void setEsqNo(NoAVP esqNo) {
			this.esqNo = esqNo;
		}

		public NoAVP getDirNo() {
			return dirNo;
		}

		public void setDirNo(NoAVP dirNo) {
			this.dirNo = dirNo;
		}

		public NoAVP getRaizNo() {
			return raizNo;
		}

		public void setRaizNo(NoAVP raizNo) {
			this.raizNo = raizNo;
		}	

		public boolean isCor() {
			return cor;
		}

		public void setCor(boolean cor) {
			this.cor = cor;
		}

}
