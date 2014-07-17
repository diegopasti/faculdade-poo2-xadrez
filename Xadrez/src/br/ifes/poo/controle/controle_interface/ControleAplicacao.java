package br.ifes.poo.controle.controle_interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ifes.poo.modelo.gerencia_dados.ContextoJogo;
import br.ifes.poo.modelo.gerencia_dados.ControleContexto;
import br.ifes.poo.visao.interacao_humana.VisaoAplicacao;

public class ControleAplicacao {
	
	private VisaoAplicacao visaoAplicacao;	
	private ControleEntrada controleEntrada;
	private ControleJogo controleJogo;	
	private ControleCadastroJogadores controleCadastroJogadores;
	
	private ControleContexto controleContexto;

	
	public ControleAplicacao() {
		System.out.println("INICIANDO O CONTROLE DA APLICACAO");
		controleContexto = new ControleContexto();
		//this.modeloAplicacao = new ModeloAplicacao();
	}
	
	private void construirEntrada(){
		this.controleEntrada = new ControleEntrada();
		
		// ADICIONANDO O EVENTO DE CLICK NO BOTAO DE JOGAR SOZINHO DO MENU.
		this.controleEntrada.getBotao(1).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visaoAplicacao.excluirPainel();
				construirCadastroJogadores(1);
			}
		});;
		
		// ADICIONANDO O EVENTO DE CLICK NO BOTAO DE JOGAR EM DOIS.
		this.controleEntrada.getBotao(2).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visaoAplicacao.excluirPainel();
				construirCadastroJogadores(2);
			}
		});;
				
		
		// ADICIONANDO O EVENTO DE CLICK NO BOTAO DE SAIR.
		this.controleEntrada.getBotao(4).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visaoAplicacao.destruirTela();				
			}
		});;
		
		this.visaoAplicacao.incluirPainel(this.controleEntrada.getEntrada());
		
	}
	
	private void construirCadastroJogadores(int opcao){
		this.controleCadastroJogadores = new ControleCadastroJogadores(opcao);
		this.visaoAplicacao.incluirPainel(this.controleCadastroJogadores.getVisaoCadastrarJogadores());
		
		this.controleCadastroJogadores.getBotao(1).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visaoAplicacao.excluirPainel();
				construirEntrada();
			}
		});;
		
		this.controleCadastroJogadores.getBotao(2).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!controleCadastroJogadores.getNomeJogador(1).isEmpty() && !controleCadastroJogadores.getNomeJogador(2).isEmpty()){
					visaoAplicacao.excluirPainel();
					construirJogo(controleCadastroJogadores.getNomeJogador(1),controleCadastroJogadores.getNomeJogador(2));
				}
			}
		});;
		
		
	}
	
	private void construirJogo(String nome1, String nome2){
		this.controleJogo = new ControleJogo(nome1,nome2);
		this.visaoAplicacao.incluirPainel(this.controleJogo.getVisaoJogo());
		this.controleJogo.getBotao(1).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//visaoAplicacao.excluirPainel();
				ContextoJogo contexto = new ContextoJogo(controleJogo);
				contexto.salvarContexto();
				//construirEntrada();
			}
		});;
		
		this.controleJogo.getBotao(3).addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				visaoAplicacao.excluirPainel();
				construirEntrada();
			}
		});;
		
	}
	
	public void iniciarAplicativo(){
		this.visaoAplicacao = new VisaoAplicacao("PROJETO XADREZ");
		this.construirEntrada();		
		this.visaoAplicacao.setVisible(true);
		
	}
	
	
}
