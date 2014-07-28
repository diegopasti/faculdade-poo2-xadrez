package br.ifes.poo.visao.interacao_humana;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.ifes.poo.controle.controle_interface.ControleJogador;
import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.utils.Coordenada;
import br.ifes.poo.visao.interacao_humana.cemiterio.VisaoCemiterio;
import br.ifes.poo.visao.interacao_humana.cemiterio.VisaoCemiterioBranco;
import br.ifes.poo.visao.interacao_humana.cemiterio.VisaoCemiterioPreto;

@SuppressWarnings("serial")
public class VisaoJogo extends JPanel{
	
	private JLabel Background = null;
	private JLabel NomePrimeiroJogador = null;
	private JLabel NomeSegundoJogador = null;
	
	private JLabel PontosPrimeiroJogador = null;
	private JLabel PontosSegundoJogador = null;
	
	private JTextField textMensagem = null;
	private JLabel labelMensagem = null;
	
	private VisaoCemiterio visaoCemiterioBranca;
	private VisaoCemiterio visaoCemiterioPreta;
	private VisaoTabuleiro visaoTabuleiro;
	private VisaoChat visaoChat;
	private VisaoMenuJogo menuJogo;
	
	public VisaoJogo(ControleJogador primeiroJogador, ControleJogador segundoJogador){	
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new ControleSinais());
		construirTela(primeiroJogador,segundoJogador);
		System.out.println("INICIANDO A VISAO DO JOGO");
	}	
	
	private void construirTela(ControleJogador primeiroJogador, ControleJogador segundoJogador){
		this.setLayout(null);
		this.construirBackground();
		this.construirTabuleiro(326,62); // 70
		this.construirMenu();
		this.construirPlacar();
		this.construirChat();		
		this.construirCampoMensagem();
		this.construirCemiterio(primeiroJogador.getPecasCapturadas(),segundoJogador.getPecasCapturadas());
		this.adicionarComponentes();
		
	}
	
	private void construirCemiterio(ArrayList<Boolean> pecasPretasMortas, ArrayList<Boolean> pecasBrancasMortas){
		visaoCemiterioBranca = new VisaoCemiterioBranco();
		visaoCemiterioBranca.setLocation(70,35);
		
		visaoCemiterioPreta = new VisaoCemiterioPreto();
		visaoCemiterioPreta.setLocation(70,625);
		
	}
	
	public void atualizarCemiterio(ControleJogador user, ControlePeca peca){
		user.capturarPeca(peca);
		
		if(user.getCor().equals("BRANCA")){
			visaoCemiterioBranca.capturarPeca(peca);
			
		}
		else{
			visaoCemiterioPreta.capturarPeca(peca);
		}
	}
	
	private void adicionarComponentes(){
		this.add(menuJogo);
		this.add(visaoTabuleiro);		
		this.add(visaoChat);		
		this.add(textMensagem);		
		this.add(labelMensagem);
		this.add((Component) visaoCemiterioBranca);
		this.add((Component) visaoCemiterioPreta);
		this.add(Background);		
	}
	
	private void construirCampoMensagem(){		
		this.labelMensagem = new JLabel(new ImageIcon(getClass().getResource("/img/Componentes/enter.png")));
		this.labelMensagem.setSize(280,25);
		this.labelMensagem.setLocation(-216, 555);	
		
		this.textMensagem = new JTextField();
		this.textMensagem.setForeground(Color.white);
		this.textMensagem.setSize(220, 25);
		this.textMensagem.setLocation(4,555);
		this.textMensagem.setBorder(null);
		this.textMensagem.setOpaque(false);
		
	}
	
	private void construirMenu(){
		this.menuJogo = new VisaoMenuJogo();
		this.menuJogo.setLocation(990, 255);	
		this.menuJogo.setSize(300, 200);
	}
	
	private void construirChat(){
		this.visaoChat = new VisaoChat();
		this.visaoChat.setLocation(7, 127);
		this.visaoChat.setSize(276,425);
	}

	private void construirBackground(){		
		ImageIcon img = new ImageIcon(getClass().getResource("/img/Tabuleiro.png"));
		this.Background = new JLabel(img);		
		this.Background.setLocation(0, 0);
		this.Background.setSize(1024,730);
	}
	
	public void configurarPlacar(ControleJogador PriJogador, ControleJogador SegJogador){
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
		this.NomePrimeiroJogador = new JLabel("PLAYER 1", SwingConstants.CENTER);
		this.NomePrimeiroJogador.setFont(new Font( "Arial", Font.BOLD, 16 ) );
		this.NomePrimeiroJogador.setForeground(Color.white);
		this.NomePrimeiroJogador.setLocation(500, 14);
		this.NomePrimeiroJogador.setSize(150,24);
		this.add(this.NomePrimeiroJogador);		
		
		this.NomeSegundoJogador = new JLabel("PLAYER 2", SwingConstants.CENTER);
		this.NomeSegundoJogador.setFont(new Font( "Arial", Font.BOLD, 16 ) );
		this.NomeSegundoJogador.setForeground(Color.white);
		this.NomeSegundoJogador.setLocation(500, 670);
		this.NomeSegundoJogador.setSize(150,24);
		this.add(this.NomeSegundoJogador);
		
		
		
		this.PontosPrimeiroJogador = new JLabel("0", SwingConstants.LEFT);
		this.PontosPrimeiroJogador.setFont(new Font( "Arial", Font.BOLD, 26 ) );
		this.PontosPrimeiroJogador.setForeground(Color.white);
		this.PontosPrimeiroJogador.setLocation(15, 48);
		this.PontosPrimeiroJogador.setSize(30,24);
		this.add(this.PontosPrimeiroJogador);
		
		this.PontosSegundoJogador = new JLabel("0", SwingConstants.LEFT);
		this.PontosSegundoJogador.setFont(new Font( "Arial", Font.BOLD, 26 ) );
		this.PontosSegundoJogador.setForeground(Color.white);
		this.PontosSegundoJogador.setLocation(15, 638);
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
	
	public VisaoBotao getBotao(int i) {
		switch (i){
			case 1:
				return this.menuJogo.getBotao(1);
			
			case 2:
				return this.menuJogo.getBotao(2);
			
			case 3:
				return this.menuJogo.getBotao(3);
			
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
            		if(menuJogo.getLocation().getX()==820.0){
            			menuJogo.setLocation(990, 255);	
            		}
            		else{
            			menuJogo.setLocation(820, 255);
            		}            		
            	}
            	            	
            	if(e.getKeyCode()==10){
            		if(labelMensagem.getLocation().getX()==0.0){
            			if(!textMensagem.getText().equals(" d i g i t e   s u a   m e n s a g e m   a q u i")){
            				// Enviar mensagem pro chat
                			visaoChat.inserirMensagem(visaoTabuleiro.getJogadorAtivo().getNome(),textMensagem.getText());	
            			}
            			
            			labelMensagem.setLocation(-216,555);
            			textMensagem.setLocation(-216,555);
            			textMensagem.setEnabled(false);
                		textMensagem.setText("");
            		}
            		else{
            			textMensagem.setEnabled(true);
            			textMensagem.setLocation(4,555);
            			labelMensagem.setLocation(0,555);
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
