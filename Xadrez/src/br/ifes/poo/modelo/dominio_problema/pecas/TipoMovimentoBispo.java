package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoBispo extends TipoMovimentoAbstrato {

	private ArrayList<Coordenada> CoordenadasPossiveis;
	private String TipoMovimento = "";
	
	private int LinhaSelecionada;
	private int ColunaSelecionada;
	private String CorSelecionada;
	private VisaoSlot[][] Tabuleiro;
	
	
	public TipoMovimentoBispo() {
		this.TipoMovimento = "BISPO";
	}
	
	@Override
	public ArrayList<Coordenada> movimentosPossiveis(String Cor, VisaoSlot[][] tabuleiro, int linha, int coluna) {

		this.CoordenadasPossiveis = new ArrayList<Coordenada>();
		
		this.LinhaSelecionada  = linha;
		this.ColunaSelecionada = coluna;
		this.CorSelecionada    = Cor;
		this.Tabuleiro         = tabuleiro;
		
		int l, c;
		
		l = this.LinhaSelecionada;
		c = this.ColunaSelecionada;
		
		// DIAGONAL SUPERIORA ESQUERDA
		for(int g=1; g < 8; g++){
			l = this.LinhaSelecionada - g;
			c = this.ColunaSelecionada - g;
			
			if(l >= 0 && c>=0){
				if(!this.tentarInserirCoordenadaPossivel(l, c)){
					break;
				}
			}
			else{
				break;
			}
		}
		
		// DIAGONAL SUPERIORA DIREITA
		for(int g=1; g < 8; g++){
			
			l = this.LinhaSelecionada - g;
			c = this.ColunaSelecionada + g;
			
			if(l>=0 && c<8){		
				
				if(!this.tentarInserirCoordenadaPossivel(l, c)){
					break;
				}
			}
			else{
				break;
			}
		}
		
		
		// DIAGONAL INFERIOR ESQUERDA
		for(int g=1; g < 8; g++){
			
			l = this.LinhaSelecionada + g;
			c = this.ColunaSelecionada - g;
			//System.out.println("VE AI OS VALORES DE G: InfEsq >> SLOT ["+l+","+c+"] : ");
			
			if(l<8 && c>=0){		
				
				if(!this.tentarInserirCoordenadaPossivel(l, c)){
					break;
				}
			}
			else{
				break;
			}
		}
		
		// DIAGONAL INFERIOR DIREITA
		for(int g=1; g < 8; g++){
			
			l = this.LinhaSelecionada + g;
			c = this.ColunaSelecionada + g;
			if(l<8 && c<8){		
				
				if(!this.tentarInserirCoordenadaPossivel(l, c)){
					break;
				}
			}
			else{
				break;
			}
		}

		
	return this.CoordenadasPossiveis;
	}
	
	
	private Boolean tentarInserirCoordenadaPossivel(int l, int c){
		if(this.Tabuleiro[l][c].getControlePeca() == null){
			//System.out.println("SLOT ["+l+","+c+"] : "+tabuleiro[l][c].getControlePeca());
			Coordenada coord = new Coordenada(l,c);
			this.CoordenadasPossiveis.add(coord);
			return true;
		}
		else{
			// Verificar se e um Inimigo
			if(this.Tabuleiro[l][c].getControlePeca().getModeloPeca().getCor() != this.CorSelecionada ){
				// Esse Slot tambem é um movimento possivel, e eh um inimigo.
				Coordenada coord = new Coordenada(l,c);
				CoordenadasPossiveis.add(coord);
				return false;
			}
			
		}
		return false;
	}
	
	
	
	@Override
	public void resetarMovimentosPossiveis() {
		this.CoordenadasPossiveis.clear();
	}	
		
	public String getTipoMovimento() {
		return this.TipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.TipoMovimento = tipoMovimento;
	}
	
}
