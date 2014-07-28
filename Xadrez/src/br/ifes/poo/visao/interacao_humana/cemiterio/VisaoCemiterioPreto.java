package br.ifes.poo.visao.interacao_humana.cemiterio;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class VisaoCemiterioPreto extends VisaoCemiterioAbstrato{

	
	public VisaoCemiterioPreto(){
		path = "/img/Pecas/Brancas/";
		construirVisao();		
	}
	
	protected void criarPecas(){
		JLabel lb0 = new JLabel(new ImageIcon(getClass().getResource(path+"TorreMini.png")));
		JLabel lb1 = new JLabel(new ImageIcon(getClass().getResource(path+"CavaloMini.png")));
		JLabel lb2 = new JLabel(new ImageIcon(getClass().getResource(path+"BispoMini.png")));
		JLabel lb3 = new JLabel(new ImageIcon(getClass().getResource(path+"ReiMini.png")));
		JLabel lb4 = new JLabel(new ImageIcon(getClass().getResource(path+"RainhaMini.png")));
		JLabel lb5 = new JLabel(new ImageIcon(getClass().getResource(path+"BispoMini.png")));
		JLabel lb6 = new JLabel(new ImageIcon(getClass().getResource(path+"CavaloMini.png")));
		JLabel lb7 = new JLabel(new ImageIcon(getClass().getResource(path+"TorreMini.png")));
		
		pecasCapturadas.add(lb0);
		pecasCapturadas.add(lb1);
		pecasCapturadas.add(lb2);
		pecasCapturadas.add(lb4);
		pecasCapturadas.add(lb3);
		pecasCapturadas.add(lb5);		
		pecasCapturadas.add(lb6);
		pecasCapturadas.add(lb7);
		
		for(int k=0;k<8;k++){
			JLabel lb = new JLabel(new ImageIcon(getClass().getResource(path+"PeaoMini.png")));
			pecasCapturadas.add(lb);
		}	
	}
}
