package br.ifes.poo.modelo.gerencia_dados;

import java.util.ArrayList;

public class ControleContexto {
	
	private ArrayList<ContextoJogo> contextos;
	
	public ControleContexto(){
		contextos = new ArrayList<ContextoJogo>();
	}
	
	public void adicionarContexto(ContextoJogo c){
		contextos.add(c);
	}
	
	public ContextoJogo getContexto(int i){
		return contextos.get(i);
	}
	
	public ArrayList<ContextoJogo> getAll(){
		return contextos;
	}
	
}
