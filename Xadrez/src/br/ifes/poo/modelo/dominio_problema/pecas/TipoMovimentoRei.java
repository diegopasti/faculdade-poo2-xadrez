package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoRei extends TipoMovimentoAbstrato{
	
	public TipoMovimentoRei(){
		this.TipoMovimento = "REI";
	}

	public ArrayList<Coordenada> movimentosPossiveis(String Cor, VisaoSlot[][] tab, int linha, int coluna){
		
		coordenadasPossiveis = new ArrayList<Coordenada>();
		linhaSelecionada  = linha;
		colunaSelecionada = coluna;
		corSelecionada    = Cor;
		tabuleiro         = tab;
		
		// Norte
		if(linha > 1) tentarInserirCoordenadaPossivel(linhaSelecionada-1, colunaSelecionada);
				
		// Nordeste
		if(linha > 1 && coluna < 7)	tentarInserirCoordenadaPossivel(linhaSelecionada-1, colunaSelecionada+1);
				
		// Leste
		if(coluna < 7) tentarInserirCoordenadaPossivel(linhaSelecionada, colunaSelecionada+1);
			
		// Sudeste
		if(linha < 7 && coluna < 7) tentarInserirCoordenadaPossivel(linhaSelecionada+1, colunaSelecionada+1);
				
		// Sul
		if(linha < 7) tentarInserirCoordenadaPossivel(linhaSelecionada+1, colunaSelecionada);
		
		// Suldoeste 
		if(linha < 7 && coluna > 0) tentarInserirCoordenadaPossivel(linhaSelecionada+1, colunaSelecionada-1);
		
		// Oeste
		if(coluna > 1) tentarInserirCoordenadaPossivel(linhaSelecionada, colunaSelecionada-1);
				
		// Noroeste
		if(coluna > 1 && linha > 1) tentarInserirCoordenadaPossivel(linhaSelecionada-1, colunaSelecionada-1);
		
		return coordenadasPossiveis;
	}
}
