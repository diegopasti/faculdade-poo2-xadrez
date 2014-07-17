package br.ifes.poo.modelo.gerencia_dados;

import br.ifes.poo.controle.controle_interface.ControleJogo;

public class ContextoJogo implements InterfaceContexto {
	
	private ControleJogo controleJogo;
	private ContextoJogador contextoPrimeiroJogador;
	private ContextoJogador contextoSegundoJogador;
		

	public ContextoJogo(ControleJogo jogo){
		controleJogo = jogo;
		contextoPrimeiroJogador = new ContextoJogador(controleJogo.getControlePrimeiroJogador());
		contextoSegundoJogador = new ContextoJogador(controleJogo.getControleSegundoJogador());
		
		if(controleJogo.getControlePrimeiroJogador()==controleJogo.getJogadorAtivo()){
			System.out.println("Esta na vez do primeiro jogador");
			contextoPrimeiroJogador.setJogadorAtivo(true);
		}
		else{
			System.out.println("Esta na vez do segundo jogador");
			contextoSegundoJogador.setJogadorAtivo(true);
		}
	}

	@Override
	public void salvarContexto() {
		System.out.println("PRIMEIRO JOGADOR");
		contextoPrimeiroJogador.salvarContexto();
		System.out.println("");
		System.out.println("SEGUNDO JOGADOR");
		contextoSegundoJogador.salvarContexto();
		
		
		
	}

	@Override
	public void recuperarContexto() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
