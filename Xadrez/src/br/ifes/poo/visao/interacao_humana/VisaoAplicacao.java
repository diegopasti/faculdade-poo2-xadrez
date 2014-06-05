package br.ifes.poo.visao.interacao_humana;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
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
		
		this.ConstruirTela(Nome);
		this.ConstruirControleSinais();
		System.out.println("INICIANDO A VISAO DA APLICACAO");
	}

	public void ConstruirControleSinais(){
		this.ControleSinais = new ControleSinais();
		this.addMouseListener(ControleSinais);
		this.addMouseMotionListener(ControleSinais);
	}
		
	public void ConstruirTela(String Nome) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addContainerListener(this.ControleSinais);
		
		this.setSize(805,628);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	public void IncluirPainel(JPanel Painel){
		this.PainelPrincipal = Painel;
		this.setContentPane(this.PainelPrincipal);
	}
	
	public void ExcluirPainel(){		
		this.remove(this.PainelPrincipal);
		this.PainelPrincipal.setVisible(false);
		this.PainelPrincipal = null;
		this.repaint();
	}
	
	public void ExibirAplicacao() {		
		this.setVisible(true);
	}

	public void DestruirTela() {
		this.dispose();
	}

	private class ControleSinais implements MouseListener, MouseMotionListener, ContainerListener{
		
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
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
