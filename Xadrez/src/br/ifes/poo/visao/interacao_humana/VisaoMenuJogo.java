package br.ifes.poo.visao.interacao_humana;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VisaoMenuJogo extends JPanel {

	private JLabel background = null;
	private VisaoBotao botaoVoltar = null;
	private VisaoBotao botaoDesistir = null;
	private VisaoBotao botaoEmpate = null;
	
	public VisaoMenuJogo(){
		this.setOpaque(false);
		this.setLayout(null);
		this.background = new JLabel(new ImageIcon(getClass().getResource("/img/Componentes/esc.png")));
		this.background.setSize(300,200);	
		this.background.setLocation(0,0);
		this.construirMenu();
		this.adicionarComponentes();
		
	}
	
	private void adicionarComponentes(){
		this.add(this.botaoEmpate);
		this.add(this.botaoDesistir);
		this.add(this.botaoVoltar);
		this.add(this.background);
	}
	
	private void construirMenu(){
		this.botaoEmpate = new VisaoBotao();
		this.botaoEmpate.definirImagens("/img/Menu/Empate_Over.png", "/img/Menu/Empate_Pressed.png", "/img/Menu/Empate_Released.png");
		this.botaoEmpate.setSize(100,30);
		
		this.botaoVoltar = new VisaoBotao();
		this.botaoVoltar.definirImagens("/img/Menu/Voltar_Over.png", "/img/Menu/Voltar_Pressed.png", "/img/Menu/Voltar_Released.png");
		this.botaoVoltar.setSize(90,30);
		
		this.botaoDesistir = new VisaoBotao();
		this.botaoDesistir.definirImagens("/img/Menu/Desistir_Over.png", "/img/Menu/Desistir_Pressed.png", "/img/Menu/Desistir_Released.png");
		this.botaoDesistir.setSize(100,30);
				
		this.botaoEmpate.setLocation(70, 40);
		this.botaoDesistir.setLocation(67,90);
		this.botaoVoltar.setLocation(74,140);		
	}
	
	public VisaoBotao getBotao(int i) {
		switch (i){
			case 1:
				return this.botaoVoltar;
			
			case 2:
				return this.botaoEmpate;
			
			case 3:
				return this.botaoDesistir;
			
			default:
				return null;
		}
	}
}
