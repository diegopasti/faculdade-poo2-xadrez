package br.ifes.poo.controle.controle_interface;

import java.util.ArrayList;

import br.ifes.poo.modelo.dominio_problema.ModeloJogador;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public class ControleJogador {

	private ModeloJogador modeloJogador;
	private ArrayList<ControlePeca> pecas;	
	
	
	public ControleJogador(String Nome, String Cor){
		this.modeloJogador = new ModeloJogador(Nome,Cor);
		this.pecas = new ArrayList<ControlePeca>();
		
	}
	
	public void capturarPeca(ControlePeca peca){
		
		int i =0;
		
		if(peca.getModeloPeca().getTipo().contains("PEAO")){
			i = 8;
		}
		
		for(int k=i; k<pecas.size();k++){
			if(peca.getModeloPeca().getTipo().equals(pecas.get(k).getModeloPeca().getTipo())){
				System.out.println("Compara a Peca: "+peca.getModeloPeca().getTipo()+" com a do conjunto: "+pecas.get(k).getModeloPeca().getTipo());
				this.pecas.get(k).setCapturada(true);
			}
		}
		
		//System.out.println("Olha quem ja foi capturada: "+this.getPecasCapturadas());
			
	}
		
	public void jogadaAutomatica(ControleJogador oponente, VisaoJogo visaoJogo){
		ControlePeca pecaEscolhida = this.modeloJogador.getInteligencia().escolherPeca(this, visaoJogo);
		visaoJogo.getTabuleiro().ativarSlot(pecaEscolhida.getCoordenada());
		this.marcarMovimentosPossiveis(visaoJogo);
		visaoJogo.getTabuleiro().ativarSlot(this.modeloJogador.getInteligencia().escolherMovimento(pecaEscolhida));
	}
	
	
	private void marcarMovimentosPossiveis(VisaoJogo visaoJogo){
		for(int j=0; j < visaoJogo.getTabuleiro().getSlotAtivo().getControlePeca().getModeloPeca().getMovimentosPossiveis().size(); j++){
			visaoJogo.getTabuleiro().getMatrizSlots()[visaoJogo.getTabuleiro().getSlotAtivo().getControlePeca().getModeloPeca().getMovimentosPossiveis().get(j).getLinha()][visaoJogo.getTabuleiro().getSlotAtivo().getControlePeca().getModeloPeca().getMovimentosPossiveis().get(j).getColuna()].marcarPossibilidade();
		}
	}
	
	
	public ArrayList<Boolean> getPecasCapturadas(){
		ArrayList<Boolean> pecasCapturadas = new ArrayList<Boolean>();
		
		for(int k=0; k < this.pecas.size();k++){
			pecasCapturadas.add(this.pecas.get(k).estaCapturada());
		}
		
		return pecasCapturadas;
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
