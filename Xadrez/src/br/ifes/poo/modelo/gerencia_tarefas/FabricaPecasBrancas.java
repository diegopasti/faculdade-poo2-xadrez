package br.ifes.poo.modelo.gerencia_tarefas;

import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.modelo.dominio_problema.pecas.TipoMovimentoBispo;
import br.ifes.poo.modelo.dominio_problema.pecas.TipoMovimentoCavalo;
import br.ifes.poo.modelo.dominio_problema.pecas.TipoMovimentoPeao;
import br.ifes.poo.modelo.dominio_problema.pecas.TipoMovimentoPeaoInicial;
import br.ifes.poo.modelo.dominio_problema.pecas.TipoMovimentoRei;
import br.ifes.poo.modelo.dominio_problema.pecas.TipoMovimentoTorre;
import br.ifes.poo.utils.Coordenada;


public class FabricaPecasBrancas extends FabricaAbstrataPecas {

	private ControlePeca controlePeca = null;
	
	public FabricaPecasBrancas(){
		
	}
	
	public void setConfiguracao(String nome, String pathImage, int valor, int lin, int col){
		this.controlePeca = new ControlePeca();
		this.controlePeca.ConfigurarModelo("BRANCA",nome,pathImage,valor,new Coordenada(lin, col));
	}
	
	public ControlePeca getControlePeca() {
		return controlePeca;
	}

	public void setControlePeca(ControlePeca controlePeca) {
		this.controlePeca = controlePeca;
	}
	
	
	// CONSTRUTOR DAS PECAS
	
	public ControlePeca getRei(){
		this.setConfiguracao("REI","/img/Pecas/Brancas/Rei.png",10, 7, 4);
		this.controlePeca.getModeloPeca().addPadraoMovimento(new TipoMovimentoRei());
		return this.controlePeca;
	}
	
	public ControlePeca getRainha(){
		this.setConfiguracao("RAINHA","/img/Pecas/Brancas/Rainha.png",9, 7, 3);
		
		this.controlePeca.getModeloPeca().addPadraoMovimento(new TipoMovimentoBispo());
		this.controlePeca.getModeloPeca().addPadraoMovimento(new TipoMovimentoTorre());
		return this.controlePeca;
	}
	
	public ControlePeca getBispo(int ordem){
		switch (ordem){
		case 1: 
			this.setConfiguracao("BISPO1","/img/Pecas/Brancas/Bispo.png",3, 7, 2);
			break;
		case 2:
			this.setConfiguracao("BISPO2","/img/Pecas/Brancas/Bispo.png",3, 7, 5);
			break;
		}
		this.controlePeca.getModeloPeca().addPadraoMovimento(new TipoMovimentoBispo());
		return this.controlePeca;
	}
	
	public ControlePeca getTorre(int ordem){
		
		switch (ordem){
		case 1: 
			this.setConfiguracao("TORRE1","/img/Pecas/Brancas/Torre.png",5, 7, 0);
			break;
		case 2:
			this.setConfiguracao("TORRE2","/img/Pecas/Brancas/Torre.png",5, 7, 7);
			break;
		}
		this.controlePeca.getModeloPeca().addPadraoMovimento(new TipoMovimentoTorre());
		return this.controlePeca;
	}
	
	public ControlePeca getCavalo(int ordem){
		
		switch (ordem){
		case 1: 
			this.setConfiguracao("CAVALO1","/img/Pecas/Brancas/Cavalo.png",3, 7, 1);
			break;
		case 2:
			this.setConfiguracao("CAVALO2","/img/Pecas/Brancas/Cavalo.png",3, 7, 6);
			break;
		}
		
		this.controlePeca.getModeloPeca().addPadraoMovimento(new TipoMovimentoCavalo());
		return this.controlePeca;
	}
	
	public ControlePeca getPeao(int ordem){
		
		switch (ordem){
		case 1: 
			this.setConfiguracao("PEAO1","/img/Pecas/Brancas/Peao.png",1, 6, 0);
			break;
		case 2:
			this.setConfiguracao("PEAO2","/img/Pecas/Brancas/Peao.png",1, 6, 1);
			break;
		case 3:
			this.setConfiguracao("PEAO3","/img/Pecas/Brancas/Peao.png",1, 6, 2);
			break;
		case 4:
			this.setConfiguracao("PEAO4","/img/Pecas/Brancas/Peao.png",1, 6, 3);
			break;
		case 5:
			this.setConfiguracao("PEAO5","/img/Pecas/Brancas/Peao.png",1, 6, 4);
			break;
		case 6:
			this.setConfiguracao("PEAO6","/img/Pecas/Brancas/Peao.png",1, 6, 5);
			break;
		case 7:
			this.setConfiguracao("PEAO7","/img/Pecas/Brancas/Peao.png",1, 6, 6);
			break;
		case 8:
			this.setConfiguracao("PEAO8","/img/Pecas/Brancas/Peao.png",1, 6, 7);
			break;
		}
		
		this.controlePeca.getModeloPeca().addPadraoMovimento(new TipoMovimentoPeao());
		this.controlePeca.getModeloPeca().addPadraoMovimento(new TipoMovimentoPeaoInicial());
		
		return this.controlePeca;
	}	
}
