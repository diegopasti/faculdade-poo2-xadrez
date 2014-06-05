package br.ifes.poo.modelo.dominio_problema;

import br.ifes.poo.modelo.dominio_problema.Jogador.Inteligencia;
import br.ifes.poo.modelo.dominio_problema.Jogador.InteligenciaSimples;

public class ModeloJogador {

	private String Nome;
	private String Cor;
	private int Pontos;
	private Inteligencia Inteligencia;
	
	public ModeloJogador(String nome, String Cor){
		if(nome.equals("ZEUS")){
			this.setInteligencia(new InteligenciaSimples());
		}
		else{
			this.setInteligencia(null);
		}
		this.setNome(nome);
		this.setCor(Cor);
		this.setPontos(0);
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public int getPontos() {
		return Pontos;
	}

	public void setPontos(int pontos) {
		Pontos = pontos;
	}

	public String getCor() {
		return Cor;
	}

	public void setCor(String cor) {
		Cor = cor;
	}

	public Inteligencia getInteligencia() {
		return Inteligencia;
	}

	public void setInteligencia(Inteligencia inteligencia) {
		Inteligencia = inteligencia;
	}
}
