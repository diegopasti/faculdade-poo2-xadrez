package br.ifes.poo.controle.controle_interface;

import java.util.ArrayList;

import br.ifes.poo.modelo.dominio_problema.ModeloJogador;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public class ControleJogador {

	private ModeloJogador modeloJogador;
	private ArrayList<ControlePeca> pecas;
	private ArrayList<Boolean> capturadas;
	
	
	
	public ControleJogador(String Nome, String Cor){
		this.modeloJogador = new ModeloJogador(Nome,Cor);
		this.pecas = new ArrayList<ControlePeca>();
		this.capturadas = new ArrayList<Boolean>();
		
	}
		
	public void jogadaAutomatica(ControleJogador Oponente, VisaoJogo visaoJogo){
		ControlePeca PecaEscolhida = this.modeloJogador.getInteligencia().escolherPeca(this, visaoJogo);
		visaoJogo.getTabuleiro().ativarSlot(PecaEscolhida.getCoordenada());
		this.marcarMovimentosPossiveis(visaoJogo);
		visaoJogo.getTabuleiro().ativarSlot(this.modeloJogador.getInteligencia().escolherMovimento(PecaEscolhida));
	}
	
	
	private void marcarMovimentosPossiveis(VisaoJogo visaoJogo){
		for(int j=0; j < visaoJogo.getTabuleiro().getSlotAtivo().getControlePeca().getModeloPeca().getMovimentosPossiveis().size(); j++){
			visaoJogo.getTabuleiro().getMatrizSlots()[visaoJogo.getTabuleiro().getSlotAtivo().getControlePeca().getModeloPeca().getMovimentosPossiveis().get(j).getLinha()][visaoJogo.getTabuleiro().getSlotAtivo().getControlePeca().getModeloPeca().getMovimentosPossiveis().get(j).getColuna()].marcarPossibilidade();
		}
	}
	
	
	public ArrayList<Boolean> getPecasCapturadas(){
		return capturadas;
	}
	
	public String getNome() {
		return modeloJogador.getNome();
	}
	public void setNome(String nome) {
		this.modeloJogador.setNome(nome);
	}
	public String getCor() {
		return modeloJogador.getCor();
	}
	public void setCor(String cor) {
		this.modeloJogador.setCor(cor);
	}
	public int getPontos() {
		return modeloJogador.getPontos();
	}
	public void addPontos(int pontos) {
		this.modeloJogador.setPontos(this.modeloJogador.getPontos()+pontos);
	}
	public ArrayList<ControlePeca> getPecas() {
		return pecas;
	}
	public void inserirPeca(ControlePeca peca) {
		this.pecas.add(peca);
	}	
}
