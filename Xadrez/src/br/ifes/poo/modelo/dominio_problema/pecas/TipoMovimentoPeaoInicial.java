package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoPeaoInicial extends TipoMovimentoAbstrato{

	public TipoMovimentoPeaoInicial(){
		this.TipoMovimento = "PEAO INICIAL";
	}
	
	public ArrayList<Coordenada> movimentosPossiveis(String Cor, VisaoSlot[][] tab, int linha, int coluna){
		coordenadasPossiveis = new ArrayList<Coordenada>();
		linhaSelecionada  = linha;
		colunaSelecionada = coluna;
		corSelecionada    = Cor;
		tabuleiro         = tab;		
		
		if(corSelecionada.equals("BRANCA")){			
			if(linhaSelecionada > 1){
				this.tentarInserirCoordenadaPossivel(linhaSelecionada-2, colunaSelecionada);
			}			
		}
					
		else if (corSelecionada.equals("PRETA")){			
			if(linhaSelecionada < 6){
				this.tentarInserirCoordenadaPossivel(linhaSelecionada+2, colunaSelecionada);
			}
		}				
	
		return coordenadasPossiveis;
	}
}
