package br.ifes.poo.visao.interacao_humana;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ifes.poo.controle.controle_interface.ControlePeca;

@SuppressWarnings("serial")
public class VisaoPecasCapturadas extends JPanel{
	
	private String path = "/img/Pecas/";
	private String cor;
	private ArrayList<JLabel> pecasCapturadas;
	
	public VisaoPecasCapturadas(String color){
		definirCor(color);
		configurar();
		construirVisao();
	}
	
	private void construirVisao(){
		this.pecasCapturadas = new ArrayList<JLabel>();
		this.criarListaPecas();
		this.adicionarComponentes();
	}
	
	private void definirCor(String c){
		cor = c;
		if(cor.equals("BRANCA")){
			path = path + "Brancas/";
			cor = "BRANCA";
		}
		else{
			path = path + "Pretas/";
			cor = "PRETA";
		}
	}
	
	private void configurar(){
		GridLayout grid = new GridLayout(2,8);
		grid.setHgap(5);
		grid.setVgap(5);
		
		this.setLayout(grid);
		this.setOpaque(false);
		this.setSize(184,50);
	}
	
	public void capturarPeca(ControlePeca peca){
		System.out.println("Vou capturar: "+peca);
		if(peca.getModeloPeca().getTipo().contains("PEAO")){
			this.pecasCapturadas.get(8+peca.getModeloPeca().getCoordenadaInicial().getColuna()).setVisible(true);
			System.out.println("Capturei??");
		}
		
		else{
			this.pecasCapturadas.get(peca.getModeloPeca().getCoordenadaInicial().getColuna()).setVisible(true);
			System.out.println("Capturei??");
		}
	}
	
	
	private void adicionarComponentes(){
		for(int k=0; k < this.pecasCapturadas.size();k++){
			this.add(this.pecasCapturadas.get(k));
			this.pecasCapturadas.get(k).setVisible(false);
		}
	}
	
	private void criarListaPecas(){
		
		JLabel lb0 = new JLabel(new ImageIcon(getClass().getResource(path+"TorreMini.png")));
		this.pecasCapturadas.add(lb0);
		
		JLabel lb1 = new JLabel(new ImageIcon(getClass().getResource(path+"CavaloMini.png")));
		this.pecasCapturadas.add(lb1);
		
		JLabel lb2 = new JLabel(new ImageIcon(getClass().getResource(path+"BispoMini.png")));
		this.pecasCapturadas.add(lb2);
		
		JLabel lb3 = new JLabel(new ImageIcon(getClass().getResource(path+"ReiMini.png")));
		JLabel lb4 = new JLabel(new ImageIcon(getClass().getResource(path+"RainhaMini.png")));
		
		if(cor.equals("BRANCA")){			
			this.pecasCapturadas.add(lb3);
			this.pecasCapturadas.add(lb4);
		}		
		else{
			this.pecasCapturadas.add(lb4);
			this.pecasCapturadas.add(lb3);
		}
		
		JLabel lb5 = new JLabel(new ImageIcon(getClass().getResource(path+"BispoMini.png")));
		this.pecasCapturadas.add(lb5);
		
		JLabel lb6 = new JLabel(new ImageIcon(getClass().getResource(path+"CavaloMini.png")));
		this.pecasCapturadas.add(lb6);
		
		JLabel lb7 = new JLabel(new ImageIcon(getClass().getResource(path+"TorreMini.png")));
		this.pecasCapturadas.add(lb7);
		
		for(int k=0;k<8;k++){
			JLabel lb = new JLabel(new ImageIcon(getClass().getResource(path+"PeaoMini.png")));
			this.pecasCapturadas.add(lb);
		}	
	}
}

