package br.ifes.poo.controle.controle_interface;

import br.ifes.poo.modelo.dominio_problema.pecas.ModeloPeca;
import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoPeca;

public class ControlePeca {

	private VisaoPeca visaoPeca;
	private ModeloPeca modeloPeca;
	
	public ControlePeca(){
		this.visaoPeca = new VisaoPeca();
	}
	
	public VisaoPeca getVisaoPeca(){
		return this.visaoPeca;
	}
	
	public void configurarModelo(String cor, String nome, String pathImage, int valor, Coordenada coord){
		this.setModeloPeca(new ModeloPeca());
		this.getModeloPeca().setCor(cor);
		
		this.getModeloPeca().setTipo(nome);
		this.definirImagem(pathImage);
		this.getModeloPeca().setValor(valor);
		this.setCoordenada(coord);
	}
	
	public void setModeloPeca(ModeloPeca modeloPeca) {
		this.modeloPeca = modeloPeca;
		//this.DefinirImagem(this.modeloPeca.getPathImagem());		
	}
	
	public ModeloPeca getModeloPeca() {
		return this.modeloPeca;
	}
	
		
	public void definirTipo(String tipo){
		this.modeloPeca.setTipo(tipo);
		//this.visaoPeca = new Controle_Peca();		
	}
	
	public void definirImagem(String path){
		this.visaoPeca.definirImagem(path);
	}
	
	
	public void definirValor(int x){
		this.modeloPeca.setValor(x);		
	}
	
	public Coordenada getCoordenada(){
		return this.modeloPeca.getCoordenada();
	}
	
	public void setCoordenada(Coordenada coord){		
		this.modeloPeca.setCoordenada(coord);
	}
	
	public String toString(){
		return this.modeloPeca.getTipo()+" "+this.modeloPeca.getCor()+" "+this.modeloPeca.getCoordenada().toString();		
	}
	
}
