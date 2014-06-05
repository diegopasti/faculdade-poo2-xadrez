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
		this.ConstruirJogadores(nome1,nome2);
		this.IniciarControleTurno();
		
		//this.ConfigurarDatabase();
		
		this.visaoJogo.setVisible(true);		
	}
	
	public ControleJogador getControlePrimeiroJogador() {
		return controlePrimeiroJogador;
	}

	public ControleJogador getControleSegundoJogador() {
		return controleSegundoJogador;
	}
	
	public void AtualizarPlacar(){
		this.visaoJogo.AtualizarPlacar(this.controlePrimeiroJogador, this.controleSegundoJogador);
	}
	
	public void IniciarControleTurno(){
		if(this.JogadorAtivo == null){ 	// PRIMEIRO TURNO
			this.JogadorAtivo = this.controlePrimeiroJogador;
			this.visaoJogo.getTabuleiro().setJogadorAtivo(this.JogadorAtivo);
			this.visaoJogo.getTabuleiro().setControleJogo(this);
		}
	}
	
	public ControleJogador getJogadorAtivo(){	
		return this.JogadorAtivo;
	}
	
	public void ProximoJogadorAtivo(){
		if(this.getJogadorAtivo() == this.controlePrimeiroJogador){
			this.JogadorAtivo = this.controleSegundoJogador;			
		}
		else{
			this.JogadorAtivo = this.controlePrimeiroJogador;
		}
		
		this.visaoJogo.getTabuleiro().setJogadorAtivo(this.JogadorAtivo);
		
		if(this.JogadorAtivo.getNome().equals("ZEUS")){
			this.JogarPeloZeus();
		}
	}	
	
	public void JogarPeloZeus(){
		this.JogadorAtivo.JogadaAutomatica(this.controlePrimeiroJogador, this.visaoJogo);
	}
	
	public void ConstruirJogadores(String nome1, String nome2){
		
		this.controlePrimeiroJogador = new ControleJogador(nome1,"BRANCA");
		if(nome2=="ZEUS"){
			this.controleSegundoJogador  = new ControleJogador("ZEUS","PRETA");
		}
		else{
			this.controleSegundoJogador  = new ControleJogador(nome2,"PRETA");
		}
		
		this.visaoJogo.ConfigurarPlacar(this.controlePrimeiroJogador, this.controleSegundoJogador);
		
		this.ConstruirPecasBrancas();
		this.ConstruirPecasPretas();
	}
	
	public void DeclararVencedor(ControleJogador Jogador){
		System.out.println("FIM DO JOGO!"+this.JogadorAtivo.getNome()+" VENCEU!");				
	}

	
	private void ConstruirPecasBrancas(){
		this.FabricaPecas = new FabricaPecasBrancas();
		
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getRei());
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getRainha());
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getBispo(1));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getBispo(2));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getTorre(1));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getTorre(2));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getCavalo(1));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getCavalo(2));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getPeao(1));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getPeao(2));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getPeao(3));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getPeao(4));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getPeao(5));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getPeao(6));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getPeao(7));
		this.controlePrimeiroJogador.InserirPeca(this.FabricaPecas.getPeao(8));
		
		for(int i=0; i < this.controlePrimeiroJogador.getPecas().size() ; i++){
			this.visaoJogo.InserirPeca(this.controlePrimeiroJogador.getPecas().get(i));	
		}
		
	}	
	
	private void ConstruirPecasPretas(){
		this.FabricaPecas = new FabricaPecasPretas();		
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getRei());
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getRainha());
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getBispo(1));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getBispo(2));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getTorre(1));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getTorre(2));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getCavalo(1));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getCavalo(2));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getPeao(1));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getPeao(2));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getPeao(3));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getPeao(4));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getPeao(5));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getPeao(6));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getPeao(7));
		this.controleSegundoJogador.InserirPeca(this.FabricaPecas.getPeao(8));
		
		for(int i=0; i < this.controleSegundoJogador.getPecas().size() ; i++){
			this.visaoJogo.InserirPeca(this.controleSegundoJogador.getPecas().get(i));	
		}		
	}
	
	public VisaoBotao getBotao(int i){
		return this.visaoJogo.getBotao(i);
	}
	
	public VisaoJogo getVisaoJogo(){		
		return this.visaoJogo;
	}
}
