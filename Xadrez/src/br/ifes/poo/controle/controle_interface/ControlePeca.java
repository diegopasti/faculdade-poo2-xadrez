package br.ifes.poo.controle.controle_interface;

import br.ifes.poo.modelo.dominio_problema.pecas.ModeloPeca;
import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoPeca;

public class ControlePeca {

	private VisaoPeca visaoPeca;
	private ModeloPeca modeloPeca;
	private Boolean estaCapturada;
	
	public ControlePeca(){
		visaoPeca = new VisaoPeca();
		this.estaCapturada = false;
	}
	
	public Boolean estaCapturada(){
		return this.estaCapturada;
	}
	
	public void setCapturada(Boolean valor){
		this.estaCapturada = valor;
	}
	
	public VisaoPeca getVisaoPeca(){
		return visaoPeca;
	}
	
	public void configurarModelo(String cor, String nome, String pathImage, int valor, Coordenada coord){
		setModeloPeca(new ModeloPeca());
		getModeloPeca().setCor(cor);
		
		getModeloPeca().setTipo(nome);
		definirImagem(pathImage);
		getModeloPeca().setValor(valor);
		setCoordenada(coord);
		modeloPeca.setCoordenadaInicial(coord);
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
