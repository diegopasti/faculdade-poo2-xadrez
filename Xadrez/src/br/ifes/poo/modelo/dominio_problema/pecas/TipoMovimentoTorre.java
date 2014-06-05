package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class TipoMovimentoTorre extends TipoMovimentoAbstrato {
	
	private ArrayList<Coordenada> CoordenadasPossiveis;
	private String TipoMovimento = "";
	
	private int LinhaSelecionada;
	private int ColunaSelecionada;
	private String CorSelecionada;
	private VisaoSlot[][] Tabuleiro;
	
	public TipoMovimentoTorre() {
		this.TipoMovimento = "TORRE";
	}
	
	@Override
	public ArrayList<Coordenada> movimentosPossiveis(String Cor, VisaoSlot[][] tabuleiro, int linha, int coluna) {

		this.CoordenadasPossiveis = new ArrayList<Coordenada>();
		this.LinhaSelecionada  = linha;
		this.ColunaSelecionada = coluna;
		this.CorSelecionada    = Cor;
		this.Tabuleiro         = tabuleiro;
			
		// DA POSICAO DA PECA PRA BAIXO
		for (int g=this.LinhaSelecionada+1; g<8; g++){
			
			if(!this.tentarInserirCoordenadaPossivel(g, this.ColunaSelecionada)){
				break;
			}						
		}
		
		// DA POSICAO DA PECA PRA CIMA
		for (int g=this.LinhaSelecionada-1; g>=0; g--){			
			if(!this.tentarInserirCoordenadaPossivel(g, this.ColunaSelecionada)){
				break;
			}
		}
		
		// DA POSICAO DA PECA PRA ESQUERDA
		for (int g=this.ColunaSelecionada-1; g>=0; g--){			
			if(!this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada, g)){
				break;
			}
		}
		
		
		// DA POSICAO DA PECA PRA DIREITA
		for (int g=this.ColunaSelecionada+1; g<8; g++){
			if(!this.tentarInserirCoordenadaPossivel(this.LinhaSelecionada, g)){
				break;
			}
		}
		
	return this.CoordenadasPossiveis;
	}
	
	private Boolean tentarInserirCoordenadaPossivel(int l, int c){
		
		//System.out.print("VERIFICANDO O SLOT ["+l+","+c+"] : "+this.Tabuleiro[l][c].getControlePeca()+". ");
		
		if(this.Tabuleiro[l][c].getControlePeca() == null){
			
			Coordenada coord = new Coordenada(l,c);
			this.CoordenadasPossiveis.add(coord);
			
			//System.out.println("SLOT EH NULO E VOU ADICIONEI ESSA POSSIBILIDADE DE MOVIMENTO.");
			return true;
		}
		else{
			// Verificar se e um Inimigo
			if(this.Tabuleiro[l][c].getControlePeca().getModeloPeca().getCor() != this.CorSelecionada ){
				// Esse Slot tambem é um movimento possivel, e eh um inimigo.
				Coordenada coord = new Coordenada(l,c);
				CoordenadasPossiveis.add(coord);
				//System.out.println("SLOT EH UM INIMIGO ADICIONEI ESSA POSSIBILIDADE DE MOVIMENTO.");
				return false;
			}
			else{
				//System.out.println("SLOT EH UM AMIGO NAO ADICIONEI ESSA POSSIBILIDADE DE MOVIMENTO.");
				return false;
				
			}
			
		}
		
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
