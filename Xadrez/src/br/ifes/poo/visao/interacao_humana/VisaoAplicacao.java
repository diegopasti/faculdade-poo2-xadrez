package br.ifes.poo.visao.interacao_humana;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VisaoAplicacao extends JFrame {
	
	private JPanel PainelPrincipal = null;
	private ControleSinais ControleSinais = null;

	public VisaoAplicacao(String Nome) {
		super(Nome);
		
		this.construirTela(Nome);
		this.construirControleSinais();
		System.out.println("INICIANDO A VISAO DA APLICACAO");
	}

	public void construirControleSinais(){
		this.ControleSinais = new ControleSinais();
		this.addMouseListener(ControleSinais);
		this.addMouseMotionListener(ControleSinais);
	}
		
	public void construirTela(String Nome) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addContainerListener(this.ControleSinais);
		
		this.setSize(1024,730); // 805,628
		this.setLocationRelativeTo(null); // centraliza a tela
		this.setResizable(false);
	}
	
	public void incluirPainel(JPanel Painel){
		this.PainelPrincipal = Painel;
		this.setContentPane(this.PainelPrincipal);
	}
	
	public void excluirPainel(){		
		this.remove(this.PainelPrincipal);
		this.PainelPrincipal.setVisible(false);
		this.PainelPrincipal = null;
		this.repaint();
	}
	
	public void exibirAplicacao() {		
		this.setVisible(true);
	}

	public void destruirTela() {
		this.dispose();
	}

	private class ControleSinais implements MouseListener, MouseMotionListener, ContainerListener{
		
		@Override
		public void mouseDragged(MouseEvent arg0) {
			
		}
	
		@Override
		public void mouseMoved(MouseEvent arg0) {
			//System.out.println("Mouse Event");
			
		}
	
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.out.println("Mouse Click");
			
		}
	
		@Override
		public void mouseEntered(MouseEvent arg0) {
			//System.out.println("Mouse Enter");
			
		}
	
		@Override
		public void mouseExited(MouseEvent arg0) {
			//System.out.println("Mouse Exited");
			
		}
	
		@Override
		public void mousePressed(MouseEvent arg0) {
			//System.out.println("Mouse pressed");
			
		}
	
		@Override
		public void mouseReleased(MouseEvent arg0) {
			//System.out.println("Mouse release");
			
		}

		@Override
		public void componentAdded(ContainerEvent arg0) {
			System.out.println("ADICIONEI UM PANEL");
			
		}

		@Override
		public void componentRemoved(ContainerEvent arg0) {
			System.out.println("EXCLUI UM PANEL");
			
		}

	}
	
}
