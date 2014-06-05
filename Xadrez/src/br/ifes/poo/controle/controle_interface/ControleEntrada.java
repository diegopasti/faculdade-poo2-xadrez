package br.ifes.poo.controle.controle_interface;


import br.ifes.poo.visao.interacao_humana.VisaoBotao;
import br.ifes.poo.visao.interacao_humana.VisaoEntrada;

public class ControleEntrada {
	
	private VisaoEntrada visaoEntrada = null;
	
	public ControleEntrada(){
		System.out.println("INICIANDO O CONTROLE DA ENTRADA");
		this.visaoEntrada = new VisaoEntrada();
		this.visaoEntrada.ConstruirMenu(40,435);
		this.visaoEntrada.ConstruirBackground();
		this.visaoEntrada.setVisible(true);
	}
	
	public VisaoBotao getBotao(int i){
		return this.visaoEntrada.getBotao(i);
	}
	
	public VisaoEntrada getEntrada(){		
		return this.visaoEntrada;
	}
	

}
