package br.ifes.poo.visao.interacao_humana;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VisaoBotao extends JButton implements MouseListener {

	private String PathReleased;
	private String PathOverlapping;
	private String PathPressed;
	
	
	public VisaoBotao(){
		super();
		this.setFocusable(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setSize(140, 30);
		this.addMouseListener(this);
	}
	
	
	public void DefinirImagens(String Over, String Pressed, String Released){
		this.setPathOverlapping(Over);
		this.setPathPressed(Pressed);
		this.setPathReleased(Released);
		this.AtualizarBotao(this.getPathReleased());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
		this.AtualizarBotao(this.getPathOverlapping());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.AtualizarBotao(this.getPathReleased());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.AtualizarBotao(this.getPathPressed());		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.AtualizarBotao(this.getPathReleased());		
	}

	public void AtualizarBotao(String Path){
		this.setIcon(null);
		this.setIcon(getIcone(Path));
	}
	
	public ImageIcon getIcone(String Path) {
		return new ImageIcon(getClass().getResource(Path));
	}


	public String getPathReleased() {
		return PathReleased;
	}


	public void setPathReleased(String pathReleased) {
		PathReleased = pathReleased;
	}


	public String getPathOverlapping() {
		return PathOverlapping;
	}


	public void setPathOverlapping(String pathOverlapping) {
		PathOverlapping = pathOverlapping;
	}


	public String getPathPressed() {
		return PathPressed;
	}


	public void setPathPressed(String pathPressed) {
		PathPressed = pathPressed;
	}
	
}
