package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoPeao extends TipoMovimentoAbstrato {
	
	private ArrayList<Coordenada> CoordenadasPossiveis;
	private String TipoMovimento = "";
	
	private int LinhaSelecionada;
	private int ColunaSelecionada;
	private String CorSelecionada;
	private VisaoSlot[][] Tabuleiro;
	
	
	public TipoMovimentoPeao(){
		this.TipoMovimento = "PEAO";
	}

	public ArrayList<Coordenada> movimentosPossiveis(String Cor, VisaoSlot[][] tabuleiro, int linha, int coluna){
		this.CoordenadasPossiveis = new ArrayList<Coordenada>();
	
		this.LinhaSelecionada  = linha;
		this.ColunaSelecionada = coluna;
		this.CorSelecionada    = Cor;
		this.Tabuleiro         = tabuleiro;
		
		if(Cor == "BRANCA"){
			
			if (this.LinhaSelecionada > 0){
				
				this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada-1, this.ColunaSelecionada);
				
				if(coluna > 0){
					this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada-1, this.ColunaSelecionada-1);
				}
				
				if(coluna < 7){
					this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada-1, this.ColunaSelecionada+1);
				}
				
			}
		}
		
		else if(Cor == "PRETA"){
			
			if (linha < 7){
				this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada+1, this.ColunaSelecionada);
			
				if(coluna > 0){
					this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada+1, this.ColunaSelecionada-1);
				}
				
				if(coluna < 7){
					this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada+1, this.ColunaSelecionada+1);
				}	
			}
		}
		
		return this.CoordenadasPossiveis;
		
	}
	
	private Boolean tentarInserirCoordenadaPossivel(int l, int c){
		if(this.Tabuleiro[l][c].getControlePeca() == null){
			if(c == this.ColunaSelecionada){ // Se for da mesma coluna, é um movimento normal do peao.
				Coordenada coord = new Coordenada(l,c);
				this.CoordenadasPossiveis.add(coord);
				return true;
			}
			else{
				return false;
			}
			
		}
		else{
			// Verificar se e um Inimigo
			if(c != this.ColunaSelecionada){ // Se não for da mesma coluna, é um movimento de captura do peao e devera ser tratada de outra forma.
				if(this.Tabuleiro[l][c].getControlePeca().getModeloPeca().getCor() != this.CorSelecionada ){
					// Esse Slot tambem é um movimento possivel, e eh um inimigo.
					Coordenada coord = new Coordenada(l,c);
					CoordenadasPossiveis.add(coord);
					return false;
				}
			}
		}
		return false;
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
