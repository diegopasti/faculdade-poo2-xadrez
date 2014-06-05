package br.ifes.poo.modelo.dominio_problema.Jogador;

import br.ifes.poo.controle.controle_interface.ControleJogador;
import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public interface Inteligencia {
	
	public ControlePeca escolherPeca(ControleJogador Jogador, VisaoJogo visaoJogo);
	public Coordenada escolherMovimento(ControlePeca PecaEscolhida);
}
