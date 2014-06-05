package br.ifes.poo.controle.controle_interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ifes.poo.visao.interacao_humana.VisaoAplicacao;

public class ControleAplicacao {
	
	private VisaoAplicacao visaoAplicacao;	
	private ControleEntrada controleEntrada;
	private ControleJogo controleJogo;	
	private ControleCadastroJogadores controleCadastroJogadores;

	
	public ControleAplicacao() {
		System.out.println("INICIANDO O CONTROLE DA APLICACAO");
		//this.modeloAplicacao = new ModeloAplicacao();
	}
	
	private void ConstruirEntrada(){
		this.controleEntrada = new ControleEntrada();
		
		// ADICIONANDO O EVENTO DE CLICK NO BOTAO DE JOGAR SOZINHO DO MENU.
		this.controleEntrada.getBotao(1).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visaoAplicacao.ExcluirPainel();
				ConstruirCadastroJogadores(1);
			}
		});;
		
		// ADICIONANDO O EVENTO DE CLICK NO BOTAO DE JOGAR EM DOIS.
		this.controleEntrada.getBotao(2).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visaoAplicacao.ExcluirPainel();
				ConstruirCadastroJogadores(2);
			}
		});;
				
		
		// ADICIONANDO O EVENTO DE CLICK NO BOTAO DE SAIR.
		this.controleEntrada.getBotao(4).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visaoAplicacao.DestruirTela();				
			}
		});;
		
		this.visaoAplicacao.IncluirPainel(this.controleEntrada.getEntrada());
		
	}
	
	private void ConstruirCadastroJogadores(int opcao){
		this.controleCadastroJogadores = new ControleCadastroJogadores(opcao);
		this.visaoAplicacao.IncluirPainel(this.controleCadastroJogadores.getVisaoCadastrarJogadores());
		
		this.controleCadastroJogadores.getBotao(1).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visaoAplicacao.ExcluirPainel();
				ConstruirEntrada();
			}
		});;
		
		this.controleCadastroJogadores.getBotao(2).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(!controleCadastroJogadores.getNomeJogador(1).isEmpty() && !controleCadastroJogadores.getNomeJogador(2).isEmpty()){
					visaoAplicacao.ExcluirPainel();
					ConstruirJogo(controleCadastroJogadores.getNomeJogador(1),controleCadastroJogadores.getNomeJogador(2));
				}
			}
		});;
		
		
	}
	
	private void ConstruirJogo(String nome1, String nome2){
		this.controleJogo = new ControleJogo(nome1,nome2);
		this.visaoAplicacao.IncluirPainel(this.controleJogo.getVisaoJogo());
		this.controleJogo.getBotao(1).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visaoAplicacao.ExcluirPainel();
				ConstruirEntrada();
			}
		});;
		
	}
	
	public void IniciarAplicativo(){
		this.visaoAplicacao = new VisaoAplicacao("PROJETO XADREZ");
		this.ConstruirEntrada();		
		this.visaoAplicacao.setVisible(true);
		
	}
	
	
}
