package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public abstract class TipoMovimentoAbstrato {
	
	public ArrayList<Coordenada> movimentosPossiveis(String Cor,VisaoSlot[][] tabuleiro, int linha, int coluna){
		return null;
	}
	
	public void resetarMovimentosPossiveis(){
		
	}	
	
	public String getTipoMovimento(){
		return null;
	}
	
	public void setTipoMovimento(){
		
	}
	
}