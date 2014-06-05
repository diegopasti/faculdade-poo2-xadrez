package br.ifes.poo.visao.interacao_humana;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VisaoEntrada extends JPanel {
	
	private JLabel Background = null;	
	private VisaoBotao botaoJogarSozinho;
	private VisaoBotao botaoJogarDupla;
	private VisaoBotao botaoEstatisticas;
	private VisaoBotao botaoSair;
				
	public VisaoBotao getBotao(int i) {
		switch (i){
			case 1:
				return this.botaoJogarSozinho;
			case 2:
				return this.botaoJogarDupla;				
			case 3:
				return this.botaoEstatisticas;
			case 4:
				return this.botaoSair;
			default:
				return null;
		}
	}

	public void visaoMenuPrincipal(){
		this.setLayout(null);
		System.out.println("INICIANDO A VISAO DA ENTRADA");
	}
	
	public void construirMenu(int x, int y){
		this.botaoJogarSozinho = new VisaoBotao();
		this.botaoJogarSozinho.definirImagens("/img/Menu/JogarSozinho_Over.png", "/img/Menu/JogarSozinho_Pressed.png", "/img/Menu/JogarSozinho_Released.png");
				
		this.botaoJogarDupla = new VisaoBotao();
		this.botaoJogarDupla.definirImagens("/img/Menu/JogarDupla_Over.png", "/img/Menu/JogarDupla_Pressed.png", "/img/Menu/JogarDupla_Released.png");
		
		this.botaoEstatisticas= new VisaoBotao();
		this.botaoEstatisticas.definirImagens("/img/Menu/Estatisticas_Over.png", "/img/Menu/Estatisticas_Pressed.png", "/img/Menu/Estatisticas_Released.png");

		this.botaoSair = new VisaoBotao();
		this.botaoSair.definirImagens("/img/Menu/Sair_Over.png", "/img/Menu/Sair_Pressed.png", "/img/Menu/Sair_Released.png");
		
		this.botaoJogarSozinho.setLocation(x,y);
		this.botaoJogarDupla.setLocation(x,y+30);
		this.botaoEstatisticas.setLocation(x, y+60);
		this.botaoSair.setLocation(x,y+90);		
		
		this.add(this.botaoJogarSozinho);
		this.add(this.botaoJogarDupla);
		this.add(this.botaoEstatisticas);
		this.add(this.botaoSair);
	}
	
	public void construirBackground(){
		this.setLayout(null);
		ImageIcon img = new ImageIcon(getClass().getResource("/img/Entrada.png"));
		this.Background = new JLabel(img);		
		this.Background.setLocation(0, 0);
		this.Background.setSize(1024,730);
		this.add(this.Background);
		
	}

}


