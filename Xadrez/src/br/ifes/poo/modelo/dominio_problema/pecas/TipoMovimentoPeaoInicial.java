package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoPeaoInicial extends TipoMovimentoAbstrato{

private ArrayList<Coordenada> CoordenadasPossiveis;
private String TipoMovimento = "";
private int LinhaSelecionada;
private int ColunaSelecionada;
private String CorSelecionada;
private VisaoSlot[][] Tabuleiro;
	
	public TipoMovimentoPeaoInicial(){
		this.TipoMovimento = "PEAO INICIAL";
	}
	
	public ArrayList<Coordenada> MovimentosPossiveis(String Cor, VisaoSlot[][] tabuleiro, int linha, int coluna){
		this.CoordenadasPossiveis = new ArrayList<Coordenada>();
		
		this.LinhaSelecionada  = linha;
		this.ColunaSelecionada = coluna;
		this.CorSelecionada    = Cor;
		this.Tabuleiro         = tabuleiro;
		
		if(this.CorSelecionada == "BRANCA"){			
			if(this.LinhaSelecionada > 1){
				this.TentarInserirCoordenadaPossivel(this.LinhaSelecionada-2, this.ColunaSelecionada);
			}			
		}
					
		else if (this.CorSelecionada == "PRETA"){			
			if(this.LinhaSelecionada < 6){
				this.TentarInserirCoordenadaPossivel(this.LinhaSelecionada+2, this.ColunaSelecionada);
			}
		}				
	
		return this.CoordenadasPossiveis;
	}
	
	private Boolean TentarInserirCoordenadaPossivel(int l, int c){
		if(this.Tabuleiro[l][c].getControlePeca() == null){			
			Coordenada coord = new Coordenada(l,c);
			this.CoordenadasPossiveis.add(coord);
			return true;
		}
		return false;
	}
	
	public void ResetarMovimentosPossiveis(){
		this.CoordenadasPossiveis.clear();
	}

	public String getTipoMovimento() {
		return TipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		TipoMovimento = tipoMovimento;
	}
	
}
