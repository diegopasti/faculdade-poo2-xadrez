package br.ifes.poo.visao.interacao_humana;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VisaoCemiterio extends JPanel{
	
	private String path = "/img/Pecas/";
	private ArrayList<JLabel> pecasMortas;
				
	public VisaoCemiterio(String cor){
		if(cor.equals("BRANCA")){
			path = path + "Brancas/";
		}
		else{
			path = path + "Pretas/";
		}
		
		GridLayout grid = new GridLayout(2,8);
		grid.setHgap(5);
		grid.setVgap(5);
		
		this.setLayout(grid);
		this.setOpaque(false);
		this.setSize(184,50);
		this.pecasMortas = new ArrayList<JLabel>();
		this.criarListaPecasBrancas();
		this.adicionarComponentes();
	}
	
	
	private void adicionarComponentes(){
		for(int k=0; k < this.pecasMortas.size();k++){
			this.add(this.pecasMortas.get(k));
		}
	}
	
	private void criarListaPecasBrancas(){
				
		JLabel lb0 = new JLabel(new ImageIcon(getClass().getResource(path+"TorreMini.png")));
		this.pecasMortas.add(lb0);
		
		JLabel lb1 = new JLabel(new ImageIcon(getClass().getResource(path+"CavaloMini.png")));
		this.pecasMortas.add(lb1);
		
		JLabel lb2 = new JLabel(new ImageIcon(getClass().getResource(path+"BispoMini.png")));
		this.pecasMortas.add(lb2);
		
		JLabel lb3 = new JLabel(new ImageIcon(getClass().getResource(path+"ReiMini.png")));
		this.pecasMortas.add(lb3);
		
		JLabel lb4 = new JLabel(new ImageIcon(getClass().getResource(path+"RainhaMini.png")));
		this.pecasMortas.add(lb4);
		
		JLabel lb5 = new JLabel(new ImageIcon(getClass().getResource(path+"BispoMini.png")));
		this.pecasMortas.add(lb5);
		
		JLabel lb6 = new JLabel(new ImageIcon(getClass().getResource(path+"CavaloMini.png")));
		this.pecasMortas.add(lb6);
		
		JLabel lb7 = new JLabel(new ImageIcon(getClass().getResource(path+"TorreMini.png")));
		this.pecasMortas.add(lb7);
		
		for(int k=0;k<8;k++){
			JLabel lb = new JLabel(new ImageIcon(getClass().getResource(path+"PeaoMini.png")));
			this.pecasMortas.add(lb);
		}
		
		
	}
}
