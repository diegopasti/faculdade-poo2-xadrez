	package br.ifes.poo.controle.controle_interface;

import br.ifes.poo.visao.interacao_humana.VisaoBotao;
import br.ifes.poo.visao.interacao_humana.VisaoCadastrarJogadores;


public class ControleCadastroJogadores {
	
	private VisaoCadastrarJogadores visaoCadastroJogadores = null;

	public ControleCadastroJogadores(int numJogadores){
		System.out.println("INICIANDO O CONTROLE DE CADASTRO DE JOGADORES");
		
		if(numJogadores == 1){
			this.visaoCadastroJogadores = new VisaoCadastrarJogadores(1);	
		}
		else{
			this.visaoCadastroJogadores = new VisaoCadastrarJogadores(2);
		}
	}
	
	public String getNomeJogador(int opcao){
		return this.visaoCadastroJogadores.getNomeJogador(opcao);
	}
	
	public VisaoBotao getBotao(int i){
		return this.visaoCadastroJogadores.getBotao(i);
	}
	
	public VisaoCadastrarJogadores getVisaoCadastrarJogadores(){		
		return this.visaoCadastroJogadores;
	}
}
