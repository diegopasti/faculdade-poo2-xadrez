package br.ifes.poo.modelo.dominio_problema.Jogador;

import java.util.ArrayList;

import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public class EstrategiaDefensiva implements Estrategia{
	
	
	public ControlePeca EscolherPeca(VisaoJogo Jogo){
		return VerificaPecasAmeacadas(Jogo);
	}
	
	private ControlePeca VerificaPecasAmeacadas(VisaoJogo Jogo){
		ArrayList<ControlePeca> PecasAmeacadas = new ArrayList<ControlePeca>();
		for(int g=0; g<Jogo.getTabuleiro().getJogadorAtivo().getPecas().size(); g++){
			if(Jogo.getTabuleiro().getJogadorAtivo().getPecas().get(g).getModeloPeca().getAmeacado()){
				PecasAmeacadas.add(Jogo.getTabuleiro().getJogadorAtivo().getPecas().get(g));
			}
		}		
		return this.EscolherPecaMaisValiosa(PecasAmeacadas);
	}
	
	private ControlePeca EscolherPecaMaisValiosa(ArrayList<ControlePeca> PecasAmeacadas){
		
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
