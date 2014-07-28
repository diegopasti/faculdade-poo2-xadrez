package br.ifes.poo.visao.interacao_humana.cemiterio;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ifes.poo.controle.controle_interface.ControlePeca;

@SuppressWarnings("serial")
public class VisaoCemiterioAbstrato extends JPanel implements VisaoCemiterio {
	
	protected String path;
	protected String cor;
	protected ArrayList<JLabel> pecasCapturadas;
	
	@Override
	public void capturarPeca(ControlePeca peca) {
		if(peca.getModeloPeca().getTipo().contains("PEAO")){
			pecasCapturadas.get(8+peca.getModeloPeca().getCoordenadaInicial().getColuna()).setVisible(true);

		}
		
		else{
			pecasCapturadas.get(peca.getModeloPeca().getCoordenadaInicial().getColuna()).setVisible(true);
		}
	}
	
	protected void construirVisao(){
		pecasCapturadas = new ArrayList<JLabel>();
		configurarGrid();		
		criarPecas();
		adicionarComponentes();
	}
	
	protected void configurarGrid(){
		GridLayout grid = new GridLayout(2,8);
		grid.setHgap(5);
		grid.setVgap(5);
		setLayout(grid);
		setOpaque(false);
		setSize(184,50);
	}
		
	protected void adicionarComponentes(){
		for(int k=0; k < pecasCapturadas.size();k++){
			add(pecasCapturadas.get(k));
			pecasCapturadas.get(k).setVisible(false);
		}
	}
	
	protected void criarPecas(){

	}
}
