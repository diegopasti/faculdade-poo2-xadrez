package br.ifes.poo.modelo.dominio_problema;

import br.ifes.poo.modelo.dominio_problema.Jogador.Inteligencia;
import br.ifes.poo.modelo.dominio_problema.Jogador.InteligenciaSimples;

public class ModeloJogador {

	private String nome;
	private String cor;
	private int pontos;
	private Inteligencia inteligencia;
	
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
		return nome;
	}

	public void setNome(String n) {
		nome = n;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int p) {
		pontos = p;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String c) {
		cor = c;
	}

	public Inteligencia getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(Inteligencia i) {
		inteligencia = i;
	}
}
