package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public abstract class TipoMovimentoAbstrato {
	
	protected ArrayList<Coordenada> coordenadasPossiveis;
	protected String TipoMovimento = "";
	
	protected int linhaSelecionada;
	protected int colunaSelecionada;
	protected String corSelecionada;
	protected VisaoSlot[][] tabuleiro;
	
	public ArrayList<Coordenada> movimentosPossiveis(String cor,VisaoSlot[][] tabuleiro, int linha, int coluna){
		return null;
	}
	
	protected Boolean tentarInserirCoordenadaPossivel(int l, int c){		
		if(tabuleiro[l][c].getControlePeca() == null){
			Coordenada coord = new Coordenada(l,c);
			coordenadasPossiveis.add(coord);	
			return true;
		}
		else{
			//Verificar se e um Inimigo
			if(tabuleiro[l][c].getControlePeca().getModeloPeca().getCor() != corSelecionada ){
				// Esse Slot tambem é um movimento possivel, e eh um inimigo.
				Coordenada coord = new Coordenada(l,c);
				coordenadasPossiveis.add(coord);
			}
		}
		return false;
	}	
	
	protected void resetarMovimentosPossiveis(){
		coordenadasPossiveis.clear();
	}

	protected String getTipoMovimento() {
		return this.TipoMovimento;
	}

	protected void setTipoMovimento(String tipoMovimento) {
		this.TipoMovimento = tipoMovimento;
	}	
}
