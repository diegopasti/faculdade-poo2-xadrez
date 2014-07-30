package br.ifes.poo.visao.interacao_humana;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VisaoBotao extends JButton implements MouseListener {

	private String pathReleased;
	private String pathOverlapping;
	private String pathPressed;
		
	public VisaoBotao(){
		super();
		this.setFocusable(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setSize(140, 30);
		this.addMouseListener(this);
	}	
	
	public void definirImagens(String Over, String Pressed, String Released){
		this.setPathOverlapping(Over);
		this.setPathPressed(Pressed);
		this.setPathReleased(Released);
		this.atualizarBotao(this.getPathReleased());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
		this.atualizarBotao(this.getPathOverlapping());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.atualizarBotao(this.getPathReleased());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.atualizarBotao(this.getPathPressed());		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.atualizarBotao(this.getPathReleased());		
	}

	public void atualizarBotao(String Path){
		this.setIcon(null);
		this.setIcon(getIcone(Path));
	}
	
	public ImageIcon getIcone(String Path) {
		return new ImageIcon(getClass().getResource(Path));
	}

	public String getPathReleased() {
		return pathReleased;
	}

	public void setPathReleased(String pathReleased) {
		this.pathReleased = pathReleased;
	}

	public String getPathOverlapping() {
		return pathOverlapping;
	}

	public void setPathOverlapping(String pathOverlapping) {
		this.pathOverlapping = pathOverlapping;
	}

	public String getPathPressed() {
		return pathPressed;
	}

	public void setPathPressed(String pathPressed) {
		this.pathPressed = pathPressed;
	}	
}
