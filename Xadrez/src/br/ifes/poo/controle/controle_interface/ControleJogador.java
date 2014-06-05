package br.ifes.poo.controle.controle_interface;

import java.util.ArrayList;
import br.ifes.poo.modelo.dominio_problema.ModeloJogador;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public class ControleJogador {

	private ModeloJogador modeloJogador;
	private ArrayList<ControlePeca> Pecas;
	
	
	public ControleJogador(String Nome, String Cor){
		this.modeloJogador = new ModeloJogador(Nome,Cor);
		this.Pecas = new ArrayList<ControlePeca>();
	}

	public void JogadaAutomatica(ControleJogador Oponente, VisaoJogo visaoJogo){
		ControlePeca PecaEscolhida = this.modeloJogador.getInteligencia().EscolherPeca(this, visaoJogo);
		visaoJogo.getTabuleiro().AtivarSlot(PecaEscolhida.getCoordenada());
		this.MarcarMovimentosPossiveis(visaoJogo);
		visaoJogo.getTabuleiro().AtivarSlot(this.modeloJogador.getInteligencia().EscolherMovimento(PecaEscolhida));
	}
	
	
	private void MarcarMovimentosPossiveis(VisaoJogo visaoJogo){
		for(int j=0; j < visaoJogo.getTabuleiro().getSlotAtivo().getControlePeca().getModeloPeca().getMovimentosPossiveis().size(); j++){
			visaoJogo.getTabuleiro().getMatrizSlots()[visaoJogo.getTabuleiro().getSlotAtivo().getControlePeca().getModeloPeca().getMovimentosPossiveis().get(j).getLinha()][visaoJogo.getTabuleiro().getSlotAtivo().getControlePeca().getModeloPeca().getMovimentosPossiveis().get(j).getColuna()].MarcarPossibilidade();
		}
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
		return Pecas;
	}
	public void InserirPeca(ControlePeca peca) {
		this.Pecas.add(peca);
	}	
}
