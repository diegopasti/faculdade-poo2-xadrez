package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoBispo extends TipoMovimentoAbstrato {
	
	public TipoMovimentoBispo() {
		this.TipoMovimento = "BISPO";
	}
	
	@Override
	public ArrayList<Coordenada> movimentosPossiveis(String cor, VisaoSlot[][] tab, int linha, int coluna) {
		coordenadasPossiveis = new ArrayList<Coordenada>();
		linhaSelecionada  = linha;
		colunaSelecionada = coluna;
		corSelecionada    = cor;
		tabuleiro         = tab;
		int l, c;
		l = linhaSelecionada;
		c = colunaSelecionada;
		
		// DIAGONAL SUPERIORA ESQUERDA
		for(int g=1; g < 8; g++){
			l = this.linhaSelecionada - g;
			c = this.colunaSelecionada - g;
			if(l >= 0 && c>=0) if(!tentarInserirCoordenadaPossivel(l, c)) break;
		}
		
		// DIAGONAL SUPERIORA DIREITA
		for(int g=1; g < 8; g++){
			l = this.linhaSelecionada - g;
			c = this.colunaSelecionada + g;
			if(l>=0 && c<8) if(!this.tentarInserirCoordenadaPossivel(l, c)) break;
		}
		
		
		// DIAGONAL INFERIOR ESQUERDA
		for(int g=1; g < 8; g++){
			l = this.linhaSelecionada + g;
			c = this.colunaSelecionada - g;
			if(l<8 && c>=0) if(!this.tentarInserirCoordenadaPossivel(l, c)) break; 
		}
		
		// DIAGONAL INFERIOR DIREITA
		for(int g=1; g < 8; g++){
			l = this.linhaSelecionada + g;
			c = this.colunaSelecionada + g;
			
			if(l<8 && c<8) if(!this.tentarInserirCoordenadaPossivel(l, c)) break;
		}		
	return coordenadasPossiveis;
	}
}
