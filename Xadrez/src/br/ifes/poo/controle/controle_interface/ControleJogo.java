package br.ifes.poo.controle.controle_interface;

import br.ifes.poo.modelo.gerencia_tarefas.FabricaAbstrataPecas;
import br.ifes.poo.modelo.gerencia_tarefas.FabricaPecasBrancas;
import br.ifes.poo.modelo.gerencia_tarefas.FabricaPecasPretas;
import br.ifes.poo.visao.interacao_humana.VisaoBotao;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public class ControleJogo {
	
	private VisaoJogo visaoJogo   = null;
	private FabricaAbstrataPecas fabricaPecas;
	private ControleJogador jogadorAtivo = null;
	private ControleJogador controlePrimeiroJogador;
	private ControleJogador controleSegundoJogador;	
	
	public ControleJogo(String nome1, String nome2){	
		System.out.println("INICIANDO O CONTROLE DO JOGO");
		this.construirJogadores(nome1,nome2);
		
		this.visaoJogo = new VisaoJogo(controlePrimeiroJogador, controleSegundoJogador);
		this.visaoJogo.configurarPlacar(controlePrimeiroJogador, controleSegundoJogador);
		this.construirPecasBrancas();
		this.construirPecasPretas();
		
		this.iniciarControleTurno();		
		//this.ConfigurarDatabase();		
		this.visaoJogo.setVisible(true);		
	}
		
	public ControleJogador getControlePrimeiroJogador() {
		return controlePrimeiroJogador;
	}

	public ControleJogador getControleSegundoJogador() {
		return controleSegundoJogador;
	}
	
	public void atualizarPlacar(){
		this.visaoJogo.atualizarPlacar(this.controlePrimeiroJogador, this.controleSegundoJogador);
	}
	
	public void iniciarControleTurno(){
		if(this.jogadorAtivo == null){ 	// PRIMEIRO TURNO
			this.jogadorAtivo = this.controlePrimeiroJogador;
			this.visaoJogo.getTabuleiro().setJogadorAtivo(this.jogadorAtivo);
			this.visaoJogo.getTabuleiro().setControleJogo(this);
		}
	}
	
	public ControleJogador getJogadorAtivo(){	
		return this.jogadorAtivo;
	}
	
	public void proximoJogadorAtivo(){
		if(this.getJogadorAtivo() == this.controlePrimeiroJogador){
			this.jogadorAtivo = this.controleSegundoJogador;			
		}
		else{
			this.jogadorAtivo = this.controlePrimeiroJogador;
		}
		
		this.visaoJogo.getTabuleiro().setJogadorAtivo(this.jogadorAtivo);
		
		if(this.jogadorAtivo.getNome().equals("ZEUS")){
			this.jogarPeloZeus();
		}
	}	
	
	public void jogarPeloZeus(){
		this.jogadorAtivo.jogadaAutomatica(this.controlePrimeiroJogador, this.visaoJogo);
	}
	
	public void construirJogadores(String nome1, String nome2){
		
		this.controlePrimeiroJogador = new ControleJogador(nome1,"BRANCA");
		if(nome2.equals("ZEUS")){
			this.controleSegundoJogador  = new ControleJogador("ZEUS","PRETA");
		}
		else{
			this.controleSegundoJogador  = new ControleJogador(nome2,"PRETA");
		}
	}
	
	public void declararVencedor(ControleJogador jogador){
		System.out.println("FIM DO JOGO!"+this.jogadorAtivo.getNome()+" VENCEU!");				
	}

	private void construirPecasBrancas(){
		this.fabricaPecas = new FabricaPecasBrancas();
		
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getRei());
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getRainha());
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getBispo(1));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getBispo(2));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getTorre(1));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getTorre(2));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getCavalo(1));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getCavalo(2));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getPeao(1));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getPeao(2));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getPeao(3));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getPeao(4));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getPeao(5));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getPeao(6));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getPeao(7));
		this.controlePrimeiroJogador.inserirPeca(this.fabricaPecas.getPeao(8));
		
		for(int i=0; i < this.controlePrimeiroJogador.getPecas().size() ; i++){
			this.visaoJogo.inserirPeca(this.controlePrimeiroJogador.getPecas().get(i));	
		}
		
	}	
	
	private void construirPecasPretas(){
		this.fabricaPecas = new FabricaPecasPretas();		
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getRei());
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getRainha());
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getBispo(1));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getBispo(2));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getTorre(1));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getTorre(2));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getCavalo(1));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getCavalo(2));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getPeao(1));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getPeao(2));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getPeao(3));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getPeao(4));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getPeao(5));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getPeao(6));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getPeao(7));
		this.controleSegundoJogador.inserirPeca(this.fabricaPecas.getPeao(8));
		
		for(int i=0; i < this.controleSegundoJogador.getPecas().size() ; i++){
			this.visaoJogo.inserirPeca(this.controleSegundoJogador.getPecas().get(i));	
		}		
	}
	
	public VisaoBotao getBotao(int i){
		return this.visaoJogo.getBotao(i);
	}
	
	public VisaoJogo getVisaoJogo(){		
		return this.visaoJogo;
	}
}
