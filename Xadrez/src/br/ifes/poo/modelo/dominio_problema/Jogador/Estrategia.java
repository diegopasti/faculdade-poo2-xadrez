package br.ifes.poo.modelo.dominio_problema.Jogador;

import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public interface Estrategia {

	public ControlePeca escolherPeca(VisaoJogo Jogo);
	
}
