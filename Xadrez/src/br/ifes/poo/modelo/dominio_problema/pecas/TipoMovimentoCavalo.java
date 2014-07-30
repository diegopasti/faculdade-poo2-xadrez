package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;
import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoCavalo extends TipoMovimentoAbstrato {
	
	public TipoMovimentoCavalo(){ 
		this.TipoMovimento = "CAVALO";
	}

	public ArrayList<Coordenada> movimentosPossiveis(String cor, VisaoSlot[][] tab, int linha, int coluna){
		
		coordenadasPossiveis = new ArrayList<Coordenada>();
		linhaSelecionada  = linha;
		colunaSelecionada = coluna;
		corSelecionada    = cor;
		tabuleiro         = tab;		
		
		if(linhaSelecionada <= 7){
			
			if(colunaSelecionada >= 1 && linhaSelecionada >= 2)	this.tentarInserirCoordenadaPossivel(linhaSelecionada-2, colunaSelecionada-1);
			
			if(colunaSelecionada >= 2 && linhaSelecionada >= 1)	this.tentarInserirCoordenadaPossivel(linhaSelecionada-1, colunaSelecionada-2);
			
			if(colunaSelecionada <= 5 && linhaSelecionada >= 1)	this.tentarInserirCoordenadaPossivel(linhaSelecionada-1, colunaSelecionada+2);
			
			if(colunaSelecionada <= 6 && linhaSelecionada >=2) this.tentarInserirCoordenadaPossivel(linhaSelecionada-2, colunaSelecionada+1);
						
			if(linhaSelecionada <= 6) if(colunaSelecionada <= 5) this.tentarInserirCoordenadaPossivel(linhaSelecionada+1, colunaSelecionada+2);
						
			if(linhaSelecionada <= 5){
				
				if(colunaSelecionada >= 1) this.tentarInserirCoordenadaPossivel(linhaSelecionada+2, colunaSelecionada-1);
				
				if( colunaSelecionada >= 2) this.tentarInserirCoordenadaPossivel(linhaSelecionada+1, colunaSelecionada-2);
				
				if(colunaSelecionada <= 6) this.tentarInserirCoordenadaPossivel(linhaSelecionada+2, colunaSelecionada+1);
				
			}		
		}
		
		return coordenadasPossiveis;
	}
}
