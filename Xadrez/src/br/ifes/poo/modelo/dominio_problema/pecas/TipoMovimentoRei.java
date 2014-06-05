package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoRei extends TipoMovimentoAbstrato{

	private ArrayList<Coordenada> CoordenadasPossiveis;
	private String TipoMovimento = "";
	
	private int LinhaSelecionada;
	private int ColunaSelecionada;
	private String CorSelecionada;
	private VisaoSlot[][] Tabuleiro;
	
	public TipoMovimentoRei(){
		this.TipoMovimento = "REI";
	}

	public ArrayList<Coordenada> movimentosPossiveis(String Cor, VisaoSlot[][] tabuleiro, int linha, int coluna){
		
		this.CoordenadasPossiveis = new ArrayList<Coordenada>();
		this.LinhaSelecionada  = linha;
		this.ColunaSelecionada = coluna;
		this.CorSelecionada    = Cor;
		this.Tabuleiro         = tabuleiro;
		
		// Norte
		if(linha > 1){
			this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada-1, this.ColunaSelecionada);
		}
		
		// Nordeste
		if(linha > 1 && coluna < 7){
			this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada-1, this.ColunaSelecionada+1);
		}
		
		// Leste
		if(coluna < 7){
			this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada, this.ColunaSelecionada+1);
		}
		
		// Sudeste
		if(linha < 7 && coluna < 7){
			this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada+1, this.ColunaSelecionada+1);
		}
		
		// Sul
		if(linha < 7){
			this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada+1, this.ColunaSelecionada);
		}
		
		// Suldoeste
		if(linha < 7 && coluna > 0){
			this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada+1, this.ColunaSelecionada-1);
		}
		
		// Oeste
		if(coluna > 1){
			this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada, this.ColunaSelecionada-1);
		}
		
		// Noroeste
		if(coluna > 1 && linha > 1){
			this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada-1, this.ColunaSelecionada-1);
		}
		
				
		return this.CoordenadasPossiveis;
	}
	
	private void tentarInserirCoordenadaPossivel(int l, int c){
		if(this.Tabuleiro[l][c].getControlePeca() == null){
			//System.out.println("SLOT ["+l+","+c+"] : "+tabuleiro[l][c].getControlePeca());
			Coordenada coord = new Coordenada(l,c);
			this.CoordenadasPossiveis.add(coord);	
		}
		else{
			// Verificar se e um Inimigo
			if(this.Tabuleiro[l][c].getControlePeca().getModeloPeca().getCor() != this.CorSelecionada ){
				// Esse Slot tambem é um movimento possivel, e eh um inimigo.
				Coordenada coord = new Coordenada(l,c);
				CoordenadasPossiveis.add(coord);	
			}
			
		}
		
	}
	
	public void resetarMovimentosPossiveis(){
		this.CoordenadasPossiveis.clear();
	}

	public String getTipoMovimento() {
		return this.TipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.TipoMovimento = tipoMovimento;
	}
	
}
