package br.ifes.poo.modelo.dominio_problema;

public class ModeloJogo {
	
	private ModeloJogador turno;
	private ModeloJogador primeiroJogador;
	private ModeloJogador segundoJogador;
	
	
	public ModeloJogo(){
		System.out.println("INICIANDO O MODELO DO JOGO");
		this.setTurno(null);
	}


	public ModeloJogador getPrimeiroJogador() {
		return this.primeiroJogador;
	}


	public void setPrimeiroJogador(ModeloJogador primeiroJogador) {
		this.primeiroJogador = primeiroJogador;
	}


	public ModeloJogador getSegundoJogador() {
		return this.segundoJogador;
	}


	public void setSegundoJogador(ModeloJogador segundoJogador) {
		this.segundoJogador = segundoJogador;
	}


	public ModeloJogador getTurno() {
		return this.turno;
	}


	public void setTurno(ModeloJogador jogador) {
		this.turno = jogador;
	}
	
}

