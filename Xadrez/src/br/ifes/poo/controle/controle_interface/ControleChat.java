package br.ifes.poo.controle.controle_interface;

import br.ifes.poo.modelo.dominio_problema.ModeloChat;

public class ControleChat {
	
	private ModeloChat modeloChat;
	
	public ControleChat(){
		this.modeloChat = new ModeloChat();
	}
	
	public void inserirMensagem(String user, String msg){
		this.modeloChat.inserirMensagem(user, msg);
	}
}
