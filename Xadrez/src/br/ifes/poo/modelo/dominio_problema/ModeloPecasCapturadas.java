package br.ifes.poo.modelo.dominio_problema;

import java.util.ArrayList;

import br.ifes.poo.modelo.dominio_problema.pecas.ModeloPeca;

public class ModeloPecasCapturadas {
	
	private ArrayList<Boolean> peaosMortos;
	private ArrayList<Boolean> outrosMortos;
	
	public ModeloPecasCapturadas(){
		peaosMortos = new ArrayList<Boolean>();
		outrosMortos = new ArrayList<Boolean>();
		for(int k=0;k<8;k++){
			peaosMortos.add(false);
			outrosMortos.add(false);
		}
	}
	
	public void capturarPeca(ModeloPeca peca){
		if(peca.getTipo().contains("PEAO")){
			peaosMortos.set(peca.getCoordenadaInicial().getColuna(), true);
		}
		else{
			outrosMortos.set(peca.getCoordenadaInicial().getColuna(), true);
		}
	}
	
	public void exibirPecasCapturadas(){
		System.out.println("PEAOS : "+peaosMortos);
		System.out.println("OUTROS: "+outrosMortos);
	}

}
