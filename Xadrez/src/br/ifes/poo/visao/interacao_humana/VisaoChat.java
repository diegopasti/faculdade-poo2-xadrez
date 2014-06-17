package br.ifes.poo.visao.interacao_humana;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.ifes.poo.controle.controle_interface.ControleChat;

@SuppressWarnings("serial")
public class VisaoChat extends JPanel {
	
	private ControleChat controleChat;
	private JTextArea textArea; 
	private JScrollPane scrollPane;
	
		
	public VisaoChat(){
		this.controleChat = new ControleChat();
		this.construirChat();
		this.ConfigurarChat();
	}
	
	private void construirChat(){
		this.setLayout(new BorderLayout(20,20));

		this.textArea = new JTextArea(1,50);
		this.textArea.setForeground(Color.white);
		this.textArea.setSize(238,440);
		this.scrollPane = new JScrollPane(this.textArea);
		this.scrollPane.setSize(238, 440);
		
		this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane,BorderLayout.CENTER);
	}
	
	private void ConfigurarChat(){
		this.setOpaque(false);
		this.textArea.setBorder(null);
		this.textArea.setOpaque(false);
		this.textArea.setEditable(false);
		//this.textArea.setEnabled(false);
		this.textArea.setLineWrap(true);		
		
		this.scrollPane.setBorder(null);
		this.scrollPane.setOpaque(false);
		this.scrollPane.getViewport().setBorder(null);
		this.scrollPane.getViewport().setOpaque(false);
}
	
	public void inserirMensagem(String user, String msg){
		String linhasVazias = "";
		for(int k=0; k<40-this.textArea.getLineCount();k++){
			linhasVazias = linhasVazias +"\n";
		}
		
		textArea.setText(linhasVazias + textArea.getText());
		
		if(this.textArea.getText().isEmpty()){
			this.textArea.setText(user+": "+msg);	
		}
		else{
			this.textArea.setText(this.textArea.getText()+"\n"+user+": "+msg);
		}
		
		this.controleChat.inserirMensagem(user, msg);
	}
	
	public void esconderChat(){
		this.setVisible(false);
	}
	
	public void exibirChat(){
		this.setVisible(true);
	}
	
}
