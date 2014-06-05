package br.ifes.poo.modelo.gerencia_tarefas;

import br.ifes.poo.controle.controle_interface.ControlePeca;

public abstract class FabricaAbstrataPecas {
	
	private ControlePeca controlePeca = null;
	
	public void setConfiguracao(String nome, String pathImage, int valor, int lin, int col){
		
	}
	
	public ControlePeca getControlePeca() {
		return this.controlePeca;
	}

	public void setControlePeca(ControlePeca controlePeca) {
		this.controlePeca = controlePeca;
	}
	
	public ControlePeca getRei(){
		return null;
	}
	
	public ControlePeca getRainha(){
		return null;
	}
	
	public ControlePeca getBispo(int ordem){
		return null;
	}
	
	public ControlePeca getTorre(int ordem){
		return null;
	}
	
	public ControlePeca getCavalo(int ordem){
		return null;
	}
	
	public ControlePeca getPeao(int ordem){
		return null;
	}
}
