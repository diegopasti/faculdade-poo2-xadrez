package br.ifes.poo.visao.interacao_humana;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ifes.poo.controle.controle_interface.ControlePeca;

@SuppressWarnings("serial")
public class VisaoCemiterio extends JPanel{
	
	private String path = "/img/Pecas/";
	private String cor;
	private ArrayList<JLabel> pecasMortas;
	
				
	public VisaoCemiterio(String color,ArrayList<Boolean> capturadas){
		cor = color;
		if(cor.equals("BRANCA")){
			path = path + "Brancas/";
			cor = "BRANCA";
		}
		else{
			path = path + "Pretas/";
			cor = "PRETA";
		}
		
		GridLayout grid = new GridLayout(2,8);
		grid.setHgap(5);
		grid.setVgap(5);
		
		this.setLayout(grid);
		this.setOpaque(false);
		this.setSize(184,50);
		this.pecasMortas = new ArrayList<JLabel>();
		this.criarListaPecas(capturadas);
		this.adicionarComponentes();
		
	}
	
	public void capturarPeca(ControlePeca peca){
		System.out.println("Ve a peca que vai pro cemiterio: "+peca.getModeloPeca().getTipo());
		if(peca.getModeloPeca().getTipo().contains("PEAO")){
			for(int k=8;k<16;k++){
				if(!pecasMortas.get(k).isVisible()){
					pecasMortas.get(k).setVisible(true);
					break;
				}
			}
		}
		
		switch(peca.getModeloPeca().getTipo()){
		case "TORRE1":
			pecasMortas.get(0).setVisible(true);
			break;
		
		case "CAVALO1":
			pecasMortas.get(1).setVisible(true);
			break;
		
		case "BISPO1":
			pecasMortas.get(2).setVisible(true);
			break;
			
		case "REI":
			pecasMortas.get(3).setVisible(true);
			break;
			
		case "RAINHA":
			pecasMortas.get(4).setVisible(true);
			break;
			
		case "BISPO2":
			pecasMortas.get(5).setVisible(true);
			break;
			
		case "CAVALO2":
			pecasMortas.get(6).setVisible(true);
			break;
			
		case "TORRE2":
			pecasMortas.get(7).setVisible(true);
			break;
		}
		
	}
	
	
	private void adicionarComponentes(){
		for(int k=0; k < this.pecasMortas.size();k++){
			this.add(this.pecasMortas.get(k));
			this.pecasMortas.get(k).setVisible(false);
		}
	}
	
	private void criarListaPecas(ArrayList<Boolean> capturadas){
		
		JLabel lb0 = new JLabel(new ImageIcon(getClass().getResource(path+"TorreMini.png")));
		this.pecasMortas.add(lb0);
		
		JLabel lb1 = new JLabel(new ImageIcon(getClass().getResource(path+"CavaloMini.png")));
		this.pecasMortas.add(lb1);
		
		JLabel lb2 = new JLabel(new ImageIcon(getClass().getResource(path+"BispoMini.png")));
		this.pecasMortas.add(lb2);
		
		JLabel lb3 = new JLabel(new ImageIcon(getClass().getResource(path+"ReiMini.png")));
		JLabel lb4 = new JLabel(new ImageIcon(getClass().getResource(path+"RainhaMini.png")));
		
		if(cor.equals("BRANCA")){			
			this.pecasMortas.add(lb3);
			this.pecasMortas.add(lb4);
		}		
		else{
			this.pecasMortas.add(lb4);
			this.pecasMortas.add(lb3);
		}
		
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
		
		if(!capturadas.isEmpty()){
			for(int k =0;k<capturadas.size();k++){
				pecasMortas.get(k).setVisible(capturadas.get(k)); 
			}	
		}
		
		
		
	}
}

