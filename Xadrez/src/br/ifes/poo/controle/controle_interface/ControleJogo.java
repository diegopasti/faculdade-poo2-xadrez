package br.ifes.poo.controle.controle_interface;

import br.ifes.poo.modelo.gerencia_tarefas.FabricaAbstrataPecas;
import br.ifes.poo.modelo.gerencia_tarefas.FabricaPecasBrancas;
import br.ifes.poo.modelo.gerencia_tarefas.FabricaPecasPretas;
import br.ifes.poo.visao.interacao_humana.VisaoBotao;
import br.ifes.poo.visao.interacao_humana.VisaoJogo;

public class ControleJogo {
	
	private VisaoJogo visaoJogo   = null;
	private FabricaAbstrataPecas FabricaPecas;
	private ControleJogador JogadorAtivo = null;
	private ControleJogador controlePrimeiroJogador;
	private ControleJogador controleSegundoJogador;	
	
	public ControleJogo(String nome1, String nome2){	
		System.out.println("INICIANDO O CONTROLE DO JOGO");
		this.visaoJogo = new VisaoJogo();
		this.construirJogadores(nome1,nome2);
		this.iniciarControleTurno();		
		//this.ConfigurarDatabase();		
		this.visaoJogo.setVisible(true);
		//this.visaoJogo.getChat().inserirMensagem(JogadorAtivo.getNome(), "Coeh vei funciona");
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
		if(this.JogadorAtivo == null){ 	// PRIMEIRO TURNO
			this.JogadorAtivo = this.controlePrimeiroJogador;
			this.visaoJogo.getTabuleiro().setJogadorAtivo(this.JogadorAtivo);
			this.visaoJogo.getTabuleiro().setControleJogo(this);
		}
	}
	
	public ControleJogador getJogadorAtivo(){	
		return this.JogadorAtivo;
	}
	
	public void proximoJogadorAtivo(){
		if(this.getJogadorAtivo() == this.controlePrimeiroJogador){
			this.JogadorAtivo = this.controleSegundoJogador;			
		}
		else{
			this.JogadorAtivo = this.controlePrimeiroJogador;
		}
		
		this.visaoJogo.getTabuleiro().setJogadorAtivo(this.JogadorAtivo);
		
		if(this.JogadorAtivo.getNome().equals("ZEUS")){
			this.jogarPeloZeus();
		}
	}	
	
	public void jogarPeloZeus(){
		this.JogadorAtivo.jogadaAutomatica(this.controlePrimeiroJogador, this.visaoJogo);
	}
	
	public void construirJogadores(String nome1, String nome2){
		
		this.controlePrimeiroJogador = new ControleJogador(nome1,"BRANCA");
		if(nome2=="ZEUS"){
			this.controleSegundoJogador  = new ControleJogador("ZEUS","PRETA");
		}
		else{
			this.controleSegundoJogador  = new ControleJogador(nome2,"PRETA");
		}
		
		//this.visaoJogo.configurarPlacar(this.controlePrimeiroJogador, this.controleSegundoJogador);
		
		this.construirPecasBrancas();
		this.construirPecasPretas();
	}
	
	public void declararVencedor(ControleJogador Jogador){
		System.out.println("FIM DO JOGO!"+this.JogadorAtivo.getNome()+" VENCEU!");				
	}

	
	private void construirPecasBrancas(){
		this.FabricaPecas = new FabricaPecasBrancas();
		
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getRei());
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getRainha());
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getBispo(1));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getBispo(2));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getTorre(1));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getTorre(2));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getCavalo(1));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getCavalo(2));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getPeao(1));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getPeao(2));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getPeao(3));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getPeao(4));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getPeao(5));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getPeao(6));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getPeao(7));
		this.controlePrimeiroJogador.inserirPeca(this.FabricaPecas.getPeao(8));
		
		for(int i=0; i < this.controlePrimeiroJogador.getPecas().size() ; i++){
			this.visaoJogo.inserirPeca(this.controlePrimeiroJogador.getPecas().get(i));	
		}
		
	}	
	
	private void construirPecasPretas(){
		this.FabricaPecas = new FabricaPecasPretas();		
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getRei());
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getRainha());
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getBispo(1));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getBispo(2));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getTorre(1));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getTorre(2));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getCavalo(1));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getCavalo(2));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getPeao(1));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getPeao(2));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getPeao(3));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getPeao(4));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getPeao(5));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getPeao(6));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getPeao(7));
		this.controleSegundoJogador.inserirPeca(this.FabricaPecas.getPeao(8));
		
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
