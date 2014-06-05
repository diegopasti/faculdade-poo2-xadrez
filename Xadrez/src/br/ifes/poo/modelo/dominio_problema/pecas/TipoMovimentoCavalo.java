package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoCavalo extends TipoMovimentoAbstrato {

	private ArrayList<Coordenada> CoordenadasPossiveis;
	private String TipoMovimento = "";
	private int LinhaSelecionada;
	private int ColunaSelecionada;
	private String CorSelecionada;
	private VisaoSlot[][] Tabuleiro;
	
	public TipoMovimentoCavalo(){
		this.TipoMovimento = "CAVALO";
	}

	public ArrayList<Coordenada> movimentosPossiveis(String Cor, VisaoSlot[][] tabuleiro, int linha, int coluna){
		
		this.CoordenadasPossiveis = new ArrayList<Coordenada>();
		this.LinhaSelecionada  = linha;
		this.ColunaSelecionada = coluna;
		this.CorSelecionada    = Cor;
		this.Tabuleiro         = tabuleiro;
		
		
		if(this.LinhaSelecionada <= 7){
			
			if(this.ColunaSelecionada >= 1 && this.LinhaSelecionada >= 2){
				//System.out.println("1 MOVIMENTO");
				this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada-2, this.ColunaSelecionada-1);
			}
			
			if(this.ColunaSelecionada >= 2 && this.LinhaSelecionada >= 1){
				//System.out.println("2 MOVIMENTO");
				this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada-1, this.ColunaSelecionada-2);
			}
			
			if(this.ColunaSelecionada <= 5 && this.LinhaSelecionada >= 1){
				//System.out.println("3 MOVIMENTO");
				this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada-1, this.ColunaSelecionada+2);
			}
			
			if(this.ColunaSelecionada <= 6 && this.LinhaSelecionada >=2){
				//System.out.println("4 MOVIMENTO");
				this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada-2, this.ColunaSelecionada+1);
			}
			
			if(this.LinhaSelecionada <= 6){
				
				if(this.ColunaSelecionada <= 5){
					//System.out.println("5 MOVIMENTO");
					this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada+1, this.ColunaSelecionada+2);
				}
			}
			
			
			if(this.LinhaSelecionada <= 5){
				
				if(this.ColunaSelecionada >= 1){
					//System.out.println("6 MOVIMENTO");
					this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada+2, this.ColunaSelecionada-1);
				}
				
				if( this.ColunaSelecionada >= 2){
					//System.out.println("7 MOVIMENTO");
					this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada+1, this.ColunaSelecionada-2);
				}
				
				if(this.ColunaSelecionada <= 6){
					//System.out.println("8 MOVIMENTO");
					this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada+2, this.ColunaSelecionada+1);
				}
			}		
			
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
