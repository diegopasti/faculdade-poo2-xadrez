package br.ifes.poo.modelo.dominio_problema;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.ifes.poo.utils.ItemChat;

public class ModeloChat {
	
	private ArrayList<ItemChat> mensagens;
	
	public ModeloChat(){
		this.mensagens = new ArrayList<ItemChat>();
	}
	
	public void inserirMensagem(String user, String text){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat stf = new SimpleDateFormat("hh:mm");
		String data = sdf.format(new Date());
		String hora = stf.format(new Date());
		mensagens.add(new ItemChat(user,data,hora,text)); 
	}
	
	public ArrayList<ItemChat> getMensagens() {
		return mensagens;
	}	
}
