package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;
import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoTorre extends TipoMovimentoAbstrato {
	
	public TipoMovimentoTorre() {
		this.TipoMovimento = "TORRE";
	}
	
	@Override
	public ArrayList<Coordenada> movimentosPossiveis(String Cor, VisaoSlot[][] tab, int linha, int coluna) {

		coordenadasPossiveis = new ArrayList<Coordenada>();
		linhaSelecionada  = linha;
		colunaSelecionada = coluna;
		corSelecionada    = Cor;
		tabuleiro         = tab;
	 		
		// DA POSICAO DA PECA PRA BAIXO
		for (int g=linhaSelecionada+1; g<8; g++){
			if (!tentarInserirCoordenadaPossivel(g, colunaSelecionada)) break;
		}
		
		// DA POSICAO DA PECA PRA CIMA
		for (int g=linhaSelecionada-1; g>=0; g--){			
			if(!tentarInserirCoordenadaPossivel(g, colunaSelecionada))	break;
		}
		
		// DA POSICAO DA PECA PRA ESQUERDA
		for (int g=colunaSelecionada-1; g>=0; g--){			
			if(!tentarInserirCoordenadaPossivel(linhaSelecionada, g)) break;
		}
		
		// DA POSICAO DA PECA PRA DIREITA
		for (int g=colunaSelecionada+1; g<8; g++){
			if(!tentarInserirCoordenadaPossivel(linhaSelecionada, g)) break;
		}
		
	return coordenadasPossiveis;
	}

}
