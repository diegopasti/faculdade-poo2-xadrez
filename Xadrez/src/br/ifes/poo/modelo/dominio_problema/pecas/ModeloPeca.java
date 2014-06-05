package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class ModeloPeca {

	private int Valor;
	private String Tipo;
	private String Cor;
	
	private Boolean Ameacado;
	
	private Coordenada Coordenada;
	
	private ArrayList<Coordenada> MovimentosPossiveis;
	
	private ArrayList<TipoMovimentoAbstrato> PadroesMovimento = null;

	public ModeloPeca(){
		this.PadroesMovimento = new ArrayList<TipoMovimentoAbstrato>();
		this.MovimentosPossiveis = new ArrayList<Coordenada>();
		this.setAmeacado(false);
	}
	
	public ArrayList<TipoMovimentoAbstrato> getPadraoMovimento() {
		return this.PadroesMovimento;
	}
	
	public void addPadraoMovimento(TipoMovimentoAbstrato padraoMovimento) {
		this.PadroesMovimento.add(padraoMovimento);
	}
	
	public void RemovePadraoMovimento(TipoMovimentoAbstrato padraoMovimento) {
		this.PadroesMovimento.add(padraoMovimento);
	}
	
	public void ResetarMovimentosPossiveis(){		
		
		for(int g=0; g < this.PadroesMovimento.size(); g++){
			this.PadroesMovimento.get(g).ResetarMovimentosPossiveis();
		}
		this.MovimentosPossiveis.clear();		
	}
	
	public ArrayList<Coordenada> VerificarMovimentosPossiveis(VisaoSlot[][] tabuleiro){
		//System.out.println("PECA >>> VERIFICAR OS "+this.PadroesMovimento.size()+" MOVIMENTOS POSSIVEIS.");	
		
		ArrayList<Coordenada> CoordenadasPossiveis = new ArrayList<Coordenada>();
				
		if(this.PadroesMovimento != null){
			for(int i=0; i < this.PadroesMovimento.size(); i++){
				
				ArrayList<Coordenada> Coord = new ArrayList<Coordenada>();
				Coord = this.PadroesMovimento.get(i).MovimentosPossiveis(this.Cor, tabuleiro, getCoordenada().getLinha() , getCoordenada().getColuna());
				//System.out.println("TIPO DE MOVIMENTO: "+this.PadroesMovimento.get(i).getTipoMovimento()+" > "+Coord.size()+" COORDENADAS POSSIVEIS.");
				
				for(int g=0; g < Coord.size(); g++){
					//System.out.println("COORDENADAS POSSIVEIS: "+Coord.get(g).toString());
					CoordenadasPossiveis.add(Coord.get(g));
				}				
			}
			this.MovimentosPossiveis = CoordenadasPossiveis;
			return this.MovimentosPossiveis;
		}
		return null;
		
	}
	
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public int getValor() {
		return this.Valor;
	}
	public void setValor(int value) {
		this.Valor = value;
	}

	public String getCor() {
		return Cor;
	}

	public void setCor(String cor) {
		Cor = cor;
	}

	public Coordenada getCoordenada() {
		return Coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		Coordenada = coordenada;
	}

	public ArrayList<Coordenada> getMovimentosPossiveis() {
		return MovimentosPossiveis;
	}

	public Boolean getAmeacado() {
		return Ameacado;
	}

	public void setAmeacado(Boolean ameacado) {
		Ameacado = ameacado;
	}
}
