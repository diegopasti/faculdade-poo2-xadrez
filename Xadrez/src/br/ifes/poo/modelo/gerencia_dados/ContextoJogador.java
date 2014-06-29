package br.ifes.poo.modelo.gerencia_dados;

import java.util.ArrayList;

import br.ifes.poo.controle.controle_interface.ControleJogador;
import br.ifes.poo.controle.controle_interface.ControlePeca;

public class ContextoJogador implements InterfaceContexto{
	
	private String nome;
	private String cor;
	private String pontos;
	private ArrayList<ControlePeca> pecas;
	private ArrayList<Boolean> capturadas;
	
	
	public ContextoJogador(ControleJogador user){
		nome = user.getNome();
		cor = user.getCor();
		pontos = ""+user.getPontos();
		pecas = user.getPecas();
	}

	@Override
	public void salvarContexto() {
		System.out.println("GERANDO CONTEXTO DO "+nome.toUpperCase());
		System.out.println("COR: "+cor);
		System.out.println("PONTOS: "+pontos);
		System.out.println("PECAS DISPONIVEIS: "+pecas);
		
	}

	@Override
	public void recuperarContexto() {
		// TODO Auto-generated method stub
		
	}

}
