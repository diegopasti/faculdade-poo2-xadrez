package br.ifes.poo.visao.interacao_humana;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class VisaoCadastrarJogadores extends JPanel{
	
	private JLabel Background;
	
	private JLabel labelPrimeiroNome;
	private JLabel labelSegundoNome;
	private JTextField textPrimeiroNome;
	private JTextField textSegundoNome;
	
	private VisaoBotao botaoVoltar = null;
	private VisaoBotao botaoJogar = null;
	
	public VisaoCadastrarJogadores(int opcao){
		this.construirTela();
		if(opcao == 1){
			this.textSegundoNome.setText("ZEUS");
			this.textSegundoNome.setEnabled(false);
		}
	
	}
	
	public String getNomeJogador(int opcao){
		switch(opcao){
		case 1:
			return this.textPrimeiroNome.getText().toUpperCase();
		case 2:
			return this.textSegundoNome.getText().toUpperCase();
		default:
			return "";
		}		
	}
	
	private void construirTela() {
		this.setLayout(null);
		this.construirBackground();
		this.construirComponentes();
		this.adicionarComponentes();	
	}
	
	private void construirBackground(){
		ImageIcon img = new ImageIcon(getClass().getResource("/img/Entrada.png"));
		this.Background = new JLabel(img);		
		this.Background.setLocation(0, 0);
		this.Background.setSize(800,600);
		
	}
	
	private void construirComponentes(){
		this.construirComponentesPrimeiroJogador(550, 410);
		this.construirComponentesSegundoJogador(550,477);
		this.construirComponentesControleTarefas();					
	}
	
	
	private void adicionarComponentes(){
		this.add(this.botaoVoltar);
		this.add(this.botaoJogar);
		this.add(this.labelPrimeiroNome);
		this.add(this.labelSegundoNome);	
		this.add(this.Background);
			
		this.add(this.textPrimeiroNome);		
		this.add(this.textSegundoNome);
	}
	
	private void construirComponentesControleTarefas(){
		this.botaoVoltar = new VisaoBotao();
		this.botaoVoltar.definirImagens("/img/Menu/Voltar_Over.png", "/img/Menu/Voltar_Pressed.png", "/img/Menu/Voltar_Released.png");
		
		this.botaoJogar = new VisaoBotao();
		this.botaoJogar.definirImagens("/img/Menu/Jogar_Over.png", "/img/Menu/Jogar_Pressed.png", "/img/Menu/Jogar_Released.png");
		
		this.botaoVoltar.setLocation(20, 540);
		this.botaoJogar.setLocation(580, 540);
		
	}
	
	private void construirComponentesPrimeiroJogador(int x, int y){
				
		this.labelPrimeiroNome = new JLabel();
		ImageIcon img = new ImageIcon(getClass().getResource("/img/Componentes/PrimeiraCaixaTexto.png"));
		this.labelPrimeiroNome.setIcon(img);
		this.labelPrimeiroNome.setLocation(x,y); // 40 , 435
		this.labelPrimeiroNome.setSize(200, 61);
		
		this.textPrimeiroNome = new JTextField();
		this.textPrimeiroNome.setHorizontalAlignment(JTextField.CENTER);
		this.textPrimeiroNome.setBorder(null);
		this.textPrimeiroNome.setLocation(x+10, y+28); // x+10 ; y+28
		this.textPrimeiroNome.setSize(182,26);		
	}
	
	
	private void construirComponentesSegundoJogador(int x , int y){
		this.labelSegundoNome = new JLabel();
		ImageIcon img = new ImageIcon(getClass().getResource("/img/Componentes/SegundaCaixaTexto.png"));
		this.labelSegundoNome.setIcon(img);
		this.labelSegundoNome.setLocation(x,y); // 40 , 503
		this.labelSegundoNome.setSize(200, 61);
		
		this.textSegundoNome = new JTextField();
		this.textSegundoNome.setHorizontalAlignment(JTextField.CENTER);
		this.textSegundoNome.setBorder(null);
		this.textSegundoNome.setLocation(x+10, y+28);  // 50 , 531
		this.textSegundoNome.setSize(182,26);
		
	}
	
	public VisaoBotao getBotao(int i) {
		switch (i){
			case 1:
				return this.botaoVoltar;
			
			case 2:
				return this.botaoJogar;
				
			default:
				return null;
		}
	}
}
