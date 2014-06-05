package br.ifes.poo.visao.interacao_humana;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VisaoPeca extends JButton{

	private String PathImagem;
	
		
	public VisaoPeca(){
		super();
		this.setBackground(new Color(0,0,0,0));
		this.setBorderPainted(false);
		this.setOpaque(false);
	}
	
	public void definirImagem(String txt){
		this.setIcon(new ImageIcon(getClass().getResource(txt)));
	}

	public String getPathImagem() {
		return PathImagem;
	}

	public void setPathImagem(String pathImagem) {
		PathImagem = pathImagem;
	}

	
}
