package br.ifes.poo.modelo.dominio_problema.Jogador;
import java.util.ArrayList;
import java.util.Random;

import br.ifes.poo.controle.controle_interface.ControleJogador;
import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public class InteligenciaSimples implements Inteligencia{

	private ArrayList<ControlePeca> PecasMovimentaveis;
	private ArrayList<Estrategia> Estrategias;
	private ControlePeca PecaEscolhida;	
	private VisaoJogo visaoJogo;
	
	public InteligenciaSimples(){
		this.configurarEstrategias();
	}
	
	@Override
	public ControlePeca escolherPeca(ControleJogador Jogador, VisaoJogo visaoJogo) {
		this.visaoJogo = visaoJogo;
		
		this.verificarPecasMovimentaveis(Jogador);
		
		this.PecaEscolhida = this.verificarMovimentosDefensivos();
		if(this.PecaEscolhida == null){
			this.PecaEscolhida = this.verificarMovimentosOfensivos();
			if(this.PecaEscolhida == null){
				this.PecaEscolhida = this.verificarMovimentoSimples();		
			}
		}
		
		return this.PecaEscolhida;
	}
	
	@Override
	public Coordenada escolherMovimento(ControlePeca PecaEscolhida) {
		
		ControlePeca Peca = null;
		
		for(int k=1; k < PecaEscolhida.getModeloPeca().getMovimentosPossiveis().size(); k++){
			if(this.visaoJogo.getTabuleiro().getMatrizSlots()[PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getLinha()][PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getColuna()].getControlePeca() != null){
				if(this.visaoJogo.getTabuleiro().getJogadorAtivo().getCor() != this.visaoJogo.getTabuleiro().getMatrizSlots()[PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getLinha()][PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getColuna()].getControlePeca().getModeloPeca().getCor()){
					if(this.visaoJogo.getTabuleiro().getMatrizSlots()[PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getLinha()][PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getColuna()].getControlePeca().getModeloPeca().getAmeacado()){
						if(Peca == null){
							Peca = this.visaoJogo.getTabuleiro().getMatrizSlots()[PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getLinha()][PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getColuna()].getControlePeca();
						}
						
						else{
							if(this.visaoJogo.getTabuleiro().getMatrizSlots()[PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getLinha()][PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getColuna()].getControlePeca().getModeloPeca().getValor() > Peca.getModeloPeca().getValor()){
								Peca = this.visaoJogo.getTabuleiro().getMatrizSlots()[PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getLinha()][PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(k).getColuna()].getControlePeca();
							}
						}
					}
				}
			}
		}
		
		if(Peca == null){
			System.out.println("INTELIGENCIA >>> A PECA ESCOLHIDA N�O TEM NENHUM MOVIMENTO QUE PODE CAPTURAR ALGUMA PECA");
			int random = new Random().nextInt(PecaEscolhida.getModeloPeca().getMovimentosPossiveis().size()); 
			System.out.println("INTELIGENCIA >>> A PECA ESCOLHIDA TEM "+PecaEscolhida.getModeloPeca().getMovimentosPossiveis().size()+"E ESCOLHEU O"+random+" MOVIMENTO");
			return new Coordenada(PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(random).getLinha(),PecaEscolhida.getModeloPeca().getMovimentosPossiveis().get(random).getColuna());
		}
		else{
			System.out.println("INTELIGENCIA >>> A PECA ESCOLHIDA TEM UM MOVIMENTO QUE PODE CAPTURAR ALGUMA PECA");
			return Peca.getCoordenada();
		}
		
		
	}
	
	private ControlePeca verificarMovimentoSimples(){
		return this.PecasMovimentaveis.get(new Random().nextInt(this.PecasMovimentaveis.size()));
	}
	
	private ControlePeca verificarMovimentosOfensivos(){
		//ControlePeca Peca = this.Estrategias.get(1).EscolherPeca(this.visaoJogo);
		return null;//Peca;
	}
	
	private ControlePeca verificarMovimentosDefensivos(){
		ControlePeca Peca = this.Estrategias.get(1).escolherPeca(this.visaoJogo);
		return Peca;
	}
	
	private void verificarPecasMovimentaveis(ControleJogador Jogador){
		this.PecasMovimentaveis = new ArrayList<ControlePeca>();		
		
		// CALCULAR OS MOVIMENTOS POSSIVEIS DE TODOS AS PECAS
		for(int c=0; c < Jogador.getPecas().size(); c++){
			if(!Jogador.getPecas().get(c).getModeloPeca().verificarMovimentosPossiveis(this.visaoJogo.getTabuleiro().getMatrizSlots()).isEmpty()){
				this.PecasMovimentaveis.add(Jogador.getPecas().get(c));
			}
		}		
	}
	
	private void configurarEstrategias(){
		this.Estrategias = new ArrayList<Estrategia>();
		this.Estrategias.add(new EstrategiaBasica());
		this.Estrategias.add(new EstrategiaDefensiva());	
		this.Estrategias.add(new EstrategiaOfensiva());
	}	

}