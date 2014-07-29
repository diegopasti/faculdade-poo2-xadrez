package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoPeao extends TipoMovimentoAbstrato {
	
	public TipoMovimentoPeao(){
		TipoMovimento = "PEAO";
	}

	public ArrayList<Coordenada> movimentosPossiveis(String Cor, VisaoSlot[][] tab, int linha, int coluna){
		coordenadasPossiveis = new ArrayList<Coordenada>();
		linhaSelecionada  = linha;
		colunaSelecionada = coluna;
		corSelecionada    = Cor;
		tabuleiro         = tab;
		
		if(Cor.equals("BRANCA")){
			if (linhaSelecionada > 0){
				tentarInserirCoordenadaPossivel(linhaSelecionada-1, colunaSelecionada);
				if(coluna > 0) if (tabuleiro[linhaSelecionada-1][colunaSelecionada-1].getControlePeca() != null) tentarInserirCoordenadaPossivel(linhaSelecionada-1, colunaSelecionada-1);
				if(coluna < 7) if (tabuleiro[linhaSelecionada-1][colunaSelecionada+1].getControlePeca() != null) tentarInserirCoordenadaPossivel(linhaSelecionada-1, colunaSelecionada+1);
			}
		}
		
		else if(Cor.equals("PRETA")){
			if (linha < 7) {
				tentarInserirCoordenadaPossivel(linhaSelecionada+1, colunaSelecionada);
				if(coluna > 0) if (tabuleiro[linhaSelecionada+1][colunaSelecionada-1].getControlePeca() != null) tentarInserirCoordenadaPossivel(linhaSelecionada+1, colunaSelecionada-1);
				if(coluna < 7) if (tabuleiro[linhaSelecionada+1][colunaSelecionada+1].getControlePeca() != null) tentarInserirCoordenadaPossivel(linhaSelecionada+1, colunaSelecionada+1);
			}		
		}
	return coordenadasPossiveis;
	}
}
