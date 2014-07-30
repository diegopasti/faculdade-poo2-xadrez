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
	
	private JLabel background = null;
	private JLabel nomePrimeiroJogador = null;
	private JLabel nomeSegundoJogador = null;
	
	private JLabel pontosPrimeiroJogador = null;
	private JLabel pontosSegundoJogador = null;
	
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
		this.add(background);		
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
		this.background = new JLabel(img);		
		this.background.setLocation(0, 0);
		this.background.setSize(1024,730);
	}
	
	public void configurarPlacar(ControleJogador priJogador, ControleJogador segJogador){
		this.nomePrimeiroJogador.setText(priJogador.getNome());
		this.nomeSegundoJogador.setText(segJogador.getNome());
		this.pontosPrimeiroJogador.setText(new String(""+priJogador.getPontos()));
		this.pontosSegundoJogador.setText(new String(""+segJogador.getPontos()));
	}
	
	public void atualizarPlacar(ControleJogador priJogador, ControleJogador segJogador){
		this.pontosPrimeiroJogador.setText(new String(""+priJogador.getPontos()));
		this.pontosSegundoJogador.setText(new String(""+segJogador.getPontos()));
	}
	
	private void construirPlacar(){
		this.nomePrimeiroJogador = new JLabel("PLAYER 1", SwingConstants.CENTER);
		this.nomePrimeiroJogador.setFont(new Font( "Arial", Font.BOLD, 16 ) );
		this.nomePrimeiroJogador.setForeground(Color.white);
		this.nomePrimeiroJogador.setLocation(500, 14);
		this.nomePrimeiroJogador.setSize(150,24);
		this.add(this.nomePrimeiroJogador);		
		
		this.nomeSegundoJogador = new JLabel("PLAYER 2", SwingConstants.CENTER);
		this.nomeSegundoJogador.setFont(new Font( "Arial", Font.BOLD, 16 ) );
		this.nomeSegundoJogador.setForeground(Color.white);
		this.nomeSegundoJogador.setLocation(500, 670);
		this.nomeSegundoJogador.setSize(150,24);
		this.add(this.nomeSegundoJogador);
		
		this.pontosPrimeiroJogador = new JLabel("0", SwingConstants.LEFT);
		this.pontosPrimeiroJogador.setFont(new Font( "Arial", Font.BOLD, 26 ) );
		this.pontosPrimeiroJogador.setForeground(Color.white);
		this.pontosPrimeiroJogador.setLocation(15, 48);
		this.pontosPrimeiroJogador.setSize(30,24);
		this.add(this.pontosPrimeiroJogador);
		
		this.pontosSegundoJogador = new JLabel("0", SwingConstants.LEFT);
		this.pontosSegundoJogador.setFont(new Font( "Arial", Font.BOLD, 26 ) );
		this.pontosSegundoJogador.setForeground(Color.white);
		this.pontosSegundoJogador.setLocation(15, 638);
		this.pontosSegundoJogador.setSize(30,24);
		this.add(this.pontosSegundoJogador);
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
	
	public void inserirPeca(ControlePeca peca){
		this.visaoTabuleiro.inserirPeca(peca, peca.getCoordenada());		
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
			case 1:	return this.menuJogo.getBotao(1);
			
			case 2:	return this.menuJogo.getBotao(2);
			
			case 3: return this.menuJogo.getBotao(3);
			
			default: return null;
		}
	}	
	
	private class ControleSinais implements KeyEventDispatcher{
		@Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
            	//System.out.println(e.getKeyCode());

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
