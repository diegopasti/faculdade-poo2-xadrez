package br.ifes.poo.modelo.gerencia_dados;

import br.ifes.poo.controle.controle_interface.ControleJogo;

public class ContextoJogo implements InterfaceContexto {
	
	private ControleJogo controleJogo;
	private ContextoJogador contextoPrimeiroJogador;
	private ContextoJogador contextoSegundoJogador;
	

	public ContextoJogo(ControleJogo jogo){
		controleJogo = jogo;
	}

	@Override
	public void salvarContexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recuperarContexto() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
