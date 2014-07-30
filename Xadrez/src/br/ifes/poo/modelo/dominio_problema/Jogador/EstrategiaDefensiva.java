package br.ifes.poo.modelo.dominio_problema.Jogador;

import java.util.ArrayList;

import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public class EstrategiaDefensiva implements Estrategia{
	
	
	public ControlePeca escolherPeca(VisaoJogo jogo){
		return verificaPecasAmeacadas(jogo);
	}
	
	private ControlePeca verificaPecasAmeacadas(VisaoJogo jogo){
		ArrayList<ControlePeca> pecasAmeacadas = new ArrayList<ControlePeca>();
		for(int g=0; g<jogo.getTabuleiro().getJogadorAtivo().getPecas().size(); g++){
			if(jogo.getTabuleiro().getJogadorAtivo().getPecas().get(g).getModeloPeca().getAmeacado()){
				pecasAmeacadas.add(jogo.getTabuleiro().getJogadorAtivo().getPecas().get(g));
			}
		}		
		return this.escolherPecaMaisValiosa(pecasAmeacadas);
	}
	
	private ControlePeca escolherPecaMaisValiosa(ArrayList<ControlePeca> pecasAmeacadas){
		
		ControlePeca pecaMaisValiosa = null;
		if(!pecasAmeacadas.isEmpty()){
			pecaMaisValiosa = pecasAmeacadas.get(0);
		}
		
		for(int k=1; k<pecasAmeacadas.size();k++){
			if(pecasAmeacadas.get(k).getModeloPeca().getValor() > pecaMaisValiosa.getModeloPeca().getValor()){
				pecaMaisValiosa = pecasAmeacadas.get(k);
			}
		}
		return pecaMaisValiosa;		
	}	
}
