package br.ifes.poo.modelo.dominio_problema.pecas;

import java.util.ArrayList;

import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;

public class ModeloPeca {

	private int valor;
	private String tipo;
	private String cor;
	
	private Boolean ameacado;
	
	private Coordenada coordenada;
	
	private Coordenada coordenadaInicial;
	
	private ArrayList<Coordenada> movimentosPossiveis;
	
	private ArrayList<TipoMovimentoAbstrato> padroesMovimento = null;
	
	public ModeloPeca(){
		this.padroesMovimento = new ArrayList<TipoMovimentoAbstrato>();
		this.movimentosPossiveis = new ArrayList<Coordenada>();
		this.setAmeacado(false);
	}
	
	public ArrayList<TipoMovimentoAbstrato> getPadraoMovimento() {
		return this.padroesMovimento;
	}
	
	public void addPadraoMovimento(TipoMovimentoAbstrato padraoMovimento) {
		this.padroesMovimento.add(padraoMovimento);
	}
	
	public void removePadraoMovimento(TipoMovimentoAbstrato padraoMovimento) {
		this.padroesMovimento.add(padraoMovimento);
	}
	
	public void resetarMovimentosPossiveis(){		
		
		for(int g=0; g < this.padroesMovimento.size(); g++){
			this.padroesMovimento.get(g).resetarMovimentosPossiveis();
		}
		this.movimentosPossiveis.clear();		
	}
	
	public ArrayList<Coordenada> verificarMovimentosPossiveis(VisaoSlot[][] tabuleiro){
		ArrayList<Coordenada> coordenadasPossiveis = new ArrayList<Coordenada>();
				
		if(this.padroesMovimento != null){
			for(int i=0; i < this.padroesMovimento.size(); i++){				
				ArrayList<Coordenada> Coord = new ArrayList<Coordenada>();
				Coord = this.padroesMovimento.get(i).movimentosPossiveis(this.cor, tabuleiro, getCoordenada().getLinha() , getCoordenada().getColuna());
				//System.out.println("TIPO DE MOVIMENTO: "+this.PadroesMovimento.get(i).getTipoMovimento()+" > "+Coord.size()+" COORDENADAS POSSIVEIS.");
				
				for(int g=0; g < Coord.size(); g++){
					//System.out.println("COORDENADAS POSSIVEIS: "+Coord.get(g).toString());
					coordenadasPossiveis.add(Coord.get(g));
				}				
			}
			this.movimentosPossiveis = coordenadasPossiveis;
			return this.movimentosPossiveis;
		}
		return null;		
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String t) {
		tipo = t;
	}
	public int getValor() {
		return this.valor;
	}
	public void setValor(int value) {
		this.valor = value;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String c) {
		cor = c;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada c){
		coordenada = c;
	}	

	public void setCoordenadaInicial(Coordenada c){
		coordenadaInicial = c;
	}
	
	public Coordenada getCoordenadaInicial(){
		return coordenadaInicial;
	}
	
	public ArrayList<Coordenada> getMovimentosPossiveis() {
		return movimentosPossiveis;
	}

	public Boolean getAmeacado() {
		return ameacado;
	}

	public void setAmeacado(Boolean ameacado) {
		this.ameacado = ameacado;
	}
}
