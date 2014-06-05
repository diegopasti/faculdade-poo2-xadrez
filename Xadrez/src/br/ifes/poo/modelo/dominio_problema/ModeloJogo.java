package br.ifes.poo.modelo.dominio_problema;

public class ModeloJogo {
	
	private ModeloJogador Turno;
	private ModeloJogador PrimeiroJogador;
	private ModeloJogador SegundoJogador;
	
	
	public ModeloJogo(){
		System.out.println("INICIANDO O MODELO DO JOGO");
		this.setTurno(null);
	}


	public ModeloJogador getPrimeiroJogador() {
		return this.PrimeiroJogador;
	}


	public void setPrimeiroJogador(ModeloJogador primeiroJogador) {
		this.PrimeiroJogador = primeiroJogador;
	}


	public ModeloJogador getSegundoJogador() {
		return this.SegundoJogador;
	}


	public void setSegundoJogador(ModeloJogador segundoJogador) {
		this.SegundoJogador = segundoJogador;
	}


	public ModeloJogador getTurno() {
		return this.Turno;
	}


	public void setTurno(ModeloJogador jogador) {
		this.Turno = jogador;
	}
	
}

