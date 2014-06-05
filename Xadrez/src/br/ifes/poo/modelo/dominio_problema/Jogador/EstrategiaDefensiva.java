package br.ifes.poo.modelo.dominio_problema.Jogador;

import java.util.ArrayList;

import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public class EstrategiaDefensiva implements Estrategia{
	
	
	public ControlePeca escolherPeca(VisaoJogo Jogo){
		return verificaPecasAmeacadas(Jogo);
	}
	
	private ControlePeca verificaPecasAmeacadas(VisaoJogo Jogo){
		ArrayList<ControlePeca> PecasAmeacadas = new ArrayList<ControlePeca>();
		for(int g=0; g<Jogo.getTabuleiro().getJogadorAtivo().getPecas().size(); g++){
			if(Jogo.getTabuleiro().getJogadorAtivo().getPecas().get(g).getModeloPeca().getAmeacado()){
				PecasAmeacadas.add(Jogo.getTabuleiro().getJogadorAtivo().getPecas().get(g));
			}
		}		
		return this.escolherPecaMaisValiosa(PecasAmeacadas);
	}
	
	private ControlePeca escolherPecaMaisValiosa(ArrayList<ControlePeca> PecasAmeacadas){
		
		ControlePeca PecaMaisValiosa = null;
		if(!PecasAmeacadas.isEmpty()){
			PecaMaisValiosa = PecasAmeacadas.get(0);
		}
		
		for(int k=1; k<PecasAmeacadas.size();k++){
			if(PecasAmeacadas.get(k).getModeloPeca().getValor() > PecaMaisValiosa.getModeloPeca().getValor()){
				PecaMaisValiosa = PecasAmeacadas.get(k);
			}
		}
		return PecaMaisValiosa;		
	}	
}
