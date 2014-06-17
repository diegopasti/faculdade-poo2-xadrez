package br.ifes.poo.visao.interacao_humana;

import java.awt.Color;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.ifes.poo.controle.controle_interface.ControleJogador;
import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.utils.Coordenada;

@SuppressWarnings("serial")
public class VisaoJogo extends JPanel{
	
	private JLabel Background = null;
	private JLabel NomePrimeiroJogador = null;
	private JLabel NomeSegundoJogador = null;
	
	private JLabel PontosPrimeiroJogador = null;
	private JLabel PontosSegundoJogador = null;
	
	private JTextField textMensagem = null;
	private JLabel labelMensagem = null;
	private JLabel labelEsc = null;
	private VisaoBotao botaoScrollUp=null;
	private VisaoBotao botaoScrollDown=null;
	
	private VisaoBotao botaoVoltar = null;
	private VisaoBotao botaoDesistir = null;
	private VisaoBotao botaoEmpate = null;
	
	private VisaoTabuleiro visaoTabuleiro;
	private VisaoChat visaoChat;
	
	public VisaoJogo(){	
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new ControleSinais());
		this.construirTela();
		this.adicionarComponentes();
		System.out.println("INICIANDO A VISAO DO JOGO");
	}
	
	
	private void construirTela(){
		this.setLayout(null);
		this.construirBackground();
		this.construirTabuleiro(296,62); // 70
		this.construirMenu(680,20);
		//this.construirPlacar();
		this.construirChat();
		this.construirCampoMensagem();
		this.adicionarComponentes();
	}
	
	private void adicionarComponentes(){
		this.add(visaoTabuleiro);
		this.add(this.botaoEmpate);
		this.add(this.botaoDesistir);
		this.add(this.botaoVoltar);
		this.add(this.visaoChat);
		
		this.add(this.textMensagem);
		this.add(this.labelMensagem);
		this.add(this.labelEsc);
		
		this.add(this.botaoScrollUp);
		this.add(this.botaoScrollDown);
		
		this.add(this.Background);
		
		
		
		
	}
	
	private void construirCampoMensagem(){		
		this.labelMensagem = new JLabel(new ImageIcon(getClass().getResource("/img/Componentes/enter.png")));
		this.labelMensagem.setSize(280,25);
		this.labelMensagem.setLocation(-216, 677);	
		
		this.labelEsc = new JLabel(new ImageIcon(getClass().getResource("/img/Componentes/esc.png")));
		this.labelEsc.setSize(300,200);
		this.labelEsc.setLocation(990, 255);		
		
		this.textMensagem = new JTextField();
		this.textMensagem.setForeground(Color.white);
		this.textMensagem.setSize(220, 25);
		this.textMensagem.setLocation(4,677);
		this.textMensagem.setBorder(null);
		this.textMensagem.setOpaque(false);
		
		/*
		this.botaoScrollDown = new VisaoBotao();
		this.botaoScrollDown.setSize(40, 17);
		this.botaoScrollDown.definirImagens("/img/Componentes/ArrowUp_Over.png", "/img/Componentes/ArrowUp_Pressed.png", "/img/Componentes/ArrowUp_Released.png");
		this.botaoScrollDown.setLocation(130,565);
		
		this.botaoScrollUp = new VisaoBotao();
		this.botaoScrollUp.setSize(40, 17);
		this.botaoScrollUp.definirImagens("/img/Componentes/ArrowDown_Over.png", "/img/Componentes/ArrowDown_Pressed.png", "/img/Componentes/ArrowDown_Released.png");
		this.botaoScrollUp.setLocation(130,120);
		*/
	}
	
	
	private void construirChat(){
		this.visaoChat = new VisaoChat();
		this.visaoChat.setLocation(7, 127);
		this.visaoChat.setSize(276,445);
	}

	private void construirBackground(){		
		ImageIcon img = new ImageIcon(getClass().getResource("/img/Tabuleiro.png"));
		this.Background = new JLabel(img);		
		this.Background.setLocation(0, 0);
		this.Background.setSize(1024,730);
	}
	
	private void configurarPlacar(ControleJogador PriJogador, ControleJogador SegJogador){
		this.NomePrimeiroJogador.setText(PriJogador.getNome());
		this.NomeSegundoJogador.setText(SegJogador.getNome());
		this.PontosPrimeiroJogador.setText(new String(""+PriJogador.getPontos()));
		this.PontosSegundoJogador.setText(new String(""+SegJogador.getPontos()));
	}
	
	public void atualizarPlacar(ControleJogador PriJogador, ControleJogador SegJogador){
		this.PontosPrimeiroJogador.setText(new String(""+PriJogador.getPontos()));
		this.PontosSegundoJogador.setText(new String(""+SegJogador.getPontos()));
	}
	
	private void construirPlacar(){
		this.NomePrimeiroJogador = new JLabel("PLAYER 1", SwingConstants.LEFT);
		this.NomePrimeiroJogador.setFont(new Font( "Arial", Font.BOLD, 16 ) );
		this.NomePrimeiroJogador.setForeground(Color.white);
		this.NomePrimeiroJogador.setLocation(330, 670);
		this.NomePrimeiroJogador.setSize(112,24);
		this.add(this.NomePrimeiroJogador);		
		
		this.NomeSegundoJogador = new JLabel("PLAYER 2", SwingConstants.LEFT);
		this.NomeSegundoJogador.setFont(new Font( "Arial", Font.BOLD, 16 ) );
		this.NomeSegundoJogador.setForeground(Color.white);
		this.NomeSegundoJogador.setLocation(330, 20);
		this.NomeSegundoJogador.setSize(112,24);
		this.add(this.NomeSegundoJogador);
		
		this.PontosPrimeiroJogador = new JLabel("0", SwingConstants.LEFT);
		this.PontosPrimeiroJogador.setFont(new Font( "Arial", Font.BOLD, 20 ) );
		this.PontosPrimeiroJogador.setForeground(Color.white);
		this.PontosPrimeiroJogador.setLocation(300, 665);
		this.PontosPrimeiroJogador.setSize(30,24);
		this.add(this.PontosPrimeiroJogador);
		
		this.PontosSegundoJogador = new JLabel("0", SwingConstants.LEFT);
		this.PontosSegundoJogador.setFont(new Font( "Arial", Font.BOLD, 20 ) );
		this.PontosSegundoJogador.setForeground(Color.white);
		this.PontosSegundoJogador.setLocation(300, 20);
		this.PontosSegundoJogador.setSize(30,24);
		this.add(this.PontosSegundoJogador);
		
	}
	
	public VisaoChat getChat(){
		return this.visaoChat;
	}
	
	public VisaoTabuleiro getTabuleiro(){
		return this.visaoTabuleiro;
	}
	
	public void construirTabuleiro(int x, int y){
		this.visaoTabuleiro = new VisaoTabuleiro();		
		this.visaoTabuleiro.setLocation(x, y);
		
	}
	
	
	public void inserirPeca(ControlePeca Peca){
		//System.out.println("INSERINDO PECA: "+Peca.toString());
		this.visaoTabuleiro.inserirPeca(Peca, Peca.getCoordenada());		
	}
	
	public void removerPeca(Coordenada coord){
		this.visaoTabuleiro.removerPeca(coord);		
	}
	
	public void moverPeca(ControlePeca Peca, int linOrigem, int colOrigem, int linDest, int colDest){
		this.visaoTabuleiro.removerPeca(new Coordenada(linOrigem, colOrigem));
		this.visaoTabuleiro.inserirPeca(Peca, new Coordenada(linOrigem, colOrigem));		
	}
	
	
	private void construirMenu(int x, int y){
		this.botaoEmpate = new VisaoBotao();
		this.botaoEmpate.definirImagens("/img/Menu/Empate_Over.png", "/img/Menu/Empate_Pressed.png", "/img/Menu/Empate_Released.png");
		this.botaoEmpate.setSize(100,30);
		
		this.botaoVoltar = new VisaoBotao();
		this.botaoVoltar.definirImagens("/img/Menu/Voltar_Over.png", "/img/Menu/Voltar_Pressed.png", "/img/Menu/Voltar_Released.png");
		this.botaoVoltar.setSize(90,30);
		
		this.botaoDesistir = new VisaoBotao();
		this.botaoDesistir.definirImagens("/img/Menu/Desistir_Over.png", "/img/Menu/Desistir_Pressed.png", "/img/Menu/Desistir_Released.png");
		this.botaoDesistir.setSize(100,30);
		
		
		this.botaoEmpate.setLocation(x, y);
		this.botaoDesistir.setLocation(x+100,y);
		this.botaoVoltar.setLocation(x+200,y);		
	}
	
	public VisaoBotao getBotao(int i) {
		switch (i){
			case 1:
				return this.botaoVoltar;
			
			default:
				return null;
		}
	}
	
	
	private class ControleSinais implements KeyEventDispatcher{
		@Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
            	
            	System.out.println(e.getKeyCode());
            	
            	
            	if(e.getKeyCode()==27){
            		if(labelEsc.getLocation().getX()==720.0){
            			labelEsc.setLocation(990, 255);	
            		}
            		else{
            			labelEsc.setLocation(720, 255);
            		}
            		
            	}
            	
            	
            	if(e.getKeyCode()==10){
            		if(textMensagem.getLocation().getX()==0.0){
            			if(!textMensagem.getText().equals(" d i g i t e   s u a   m e n s a g e m   a q u i")){
            				// Enviar mensagem pro chat
                			visaoChat.inserirMensagem(visaoTabuleiro.getJogadorAtivo().getNome(),textMensagem.getText());	
            			}
            			
            			labelMensagem.setLocation(-436,677);
            			textMensagem.setLocation(-500,677);
            			textMensagem.setEnabled(false);
                		textMensagem.setText("");
            		}
            		else{
            			textMensagem.setEnabled(true);
            			textMensagem.setLocation(0,677);
            			labelMensagem.setLocation(0,677);
            			textMensagem.setText(" d i g i t e   s u a   m e n s a g e m   a q u i");
            			textMensagem.grabFocus();
            			textMensagem.selectAll();	
            		}
            		
            	}
            	
            }
            	
	        else if (e.getID() == KeyEvent.KEY_RELEASED) {
	                
	        } else if (e.getID() == KeyEvent.KEY_TYPED) {
	        	//System.out.println("Tecla tipada");
	        }
            return false;
	    }
	}	
	
	
	
}
