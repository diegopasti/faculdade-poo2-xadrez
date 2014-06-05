package br.ifes.poo.utils;

public class Coordenada {

	private int Linha;
	private int Coluna;
	
	public Coordenada(int l, int c){
		this.setLinha(l);
		this.setColuna(c);
	}
	
	public int getLinha() {
		return Linha;
	}
	public void setLinha(int linha) {
		Linha = linha;
	}
	public int getColuna() {
		return Coluna;
	}
	public void setColuna(int coluna) {
		Coluna = coluna;
	}
	
	public String toString(){
		return "["+this.getLinha()+","+this.getColuna()+"]";
	}
	
	public Boolean isEqual(Coordenada outra){
		if(this.getColuna() != outra.getColuna() || this.getLinha() != outra.getLinha()){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	
}
