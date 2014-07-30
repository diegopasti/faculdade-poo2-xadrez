package br.ifes.poo.modelo.gerencia_dados;

import java.util.ArrayList;

import br.ifes.poo.controle.controle_interface.ControleJogador;
import br.ifes.poo.controle.controle_interface.ControlePeca;

public class ContextoJogador implements InterfaceContexto{
	
	private String nome;
	private String cor;
	private String pontos;
	private ArrayList<ControlePeca> pecas;
	private ArrayList<Boolean> capturadas;
	private Boolean turnoAtivo=false;
	
	
	public ContextoJogador(ControleJogador user){
		nome = user.getNome();
		cor = user.getCor();
		pontos = ""+user.getPontos();
		pecas = user.getPecas();
		capturadas = user.getPecasCapturadas();
	}

	@Override
	public void salvarContexto() {
		System.out.println("GERANDO CONTEXTO DO "+nome.toUpperCase());
		System.out.println("COR: "+cor);
		System.out.println("PONTOS: "+pontos);
		
		System.out.println("PECAS DISPONIVEIS: ");
		for(int k=0; k<pecas.size();k++){
			if(pecas.get(k).estaCapturada()){
				//System.out.println("PECA CAPTURADA: "+pecas.get(k).getModeloPeca().getTipo());	
			}
			else{
				System.out.print(pecas.get(k).getModeloPeca().getTipo()+" ");
			}				
		}
		
		
		System.out.println("\nPECAS CAPTURADAS: ");
		for(int k=0; k < capturadas.size();k++){
			System.out.printf("%5s ",capturadas.get(k));
			if(k==7){
				System.out.println(" ");
			}
		}
		System.out.println("");
	}

	@Override
	public void recuperarContexto() {	
	}
	
	public void setJogadorAtivo(Boolean ativo){
		setTurnoAtivo(ativo);
	}

	public Boolean getTurnoAtivo() {
		return turnoAtivo;
	}

	public void setTurnoAtivo(Boolean turnoAtivo) {
		this.turnoAtivo = turnoAtivo;
	}

}
