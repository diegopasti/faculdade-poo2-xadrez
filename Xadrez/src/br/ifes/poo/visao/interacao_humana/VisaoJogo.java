package br.ifes.poo.visao.interacao_humana;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.ifes.poo.controle.controle_interface.ControleJogador;
import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.utils.Coordenada;

@SuppressWarnings("serial")
public class VisaoJogo extends JPanel {
	
	private JLabel Background = null;
	private JLabel NomePrimeiroJogador = null;
	private JLabel NomeSegundoJogador = null;
	
	private JLabel PontosPrimeiroJogador = null;
	private JLabel PontosSegundoJogador = null;
	
	private VisaoBotao botaoVoltar = null;
	private VisaoBotao botaoDesistir = null;
	
	private VisaoTabuleiro visaoTabuleiro;
	
	
	public VisaoJogo(){
		this.ConstruirTabuleiro(10,8);
		this.ConstruirMenu(677,526);
		this.ConstruirPlacar();
		this.ConstruirBackground();	
		System.out.println("INICIANDO A VISAO DO JOGO");
	}

	public void ConstruirBackground(){
		this.setLayout(null);
		ImageIcon img = new ImageIcon(getClass().getResource("/img/Tabuleiro.png"));
		this.Background = new JLabel(img);		
		this.Background.setLocation(0, 0);
		this.Background.setSize(800,600);
		this.add(this.Background);		
	}
	
	public void ConfigurarPlacar(ControleJogador PriJogador, ControleJogador SegJogador){
		this.NomePrimeiroJogador.setText(PriJogador.getNome());
		this.NomeSegundoJogador.setText(SegJogador.getNome());
		
		this.PontosPrimeiroJogador.setText(new String(""+PriJogador.getPontos()));
		this.PontosSegundoJogador.setText(new String(""+SegJogador.getPontos()));
		
	}
	
	public void AtualizarPlacar(ControleJogador PriJogador, ControleJogador SegJogador){
		this.PontosPrimeiroJogador.setText(new String(""+PriJogador.getPontos()));
		this.PontosSegundoJogador.setText(new String(""+SegJogador.getPontos()));
	}
	
	public void ConstruirPlacar(){
		this.NomePrimeiroJogador = new JLabel("PLAYER 1", SwingConstants.CENTER);
		this.NomePrimeiroJogador.setFont(new Font( "Arial", Font.BOLD, 16 ) );
		this.NomePrimeiroJogador.setLocation(677, 10);
		this.NomePrimeiroJogador.setSize(112,24);
		this.add(this.NomePrimeiroJogador);
		
		
		this.NomeSegundoJogador = new JLabel("PLAYER 2", SwingConstants.CENTER);
		this.NomeSegundoJogador.setFont(new Font( "Arial", Font.BOLD, 16 ) );
		this.NomeSegundoJogador.setLocation(677, 66);
		this.NomeSegundoJogador.setSize(112,24);
		this.add(this.NomeSegundoJogador);
		
		this.PontosPrimeiroJogador = new JLabel("0", SwingConstants.CENTER);
		this.PontosPrimeiroJogador.setFont(new Font( "Arial", Font.BOLD, 16 ) );
		this.PontosPrimeiroJogador.setLocation(677, 33);
		this.PontosPrimeiroJogador.setSize(112,24);
		this.add(this.PontosPrimeiroJogador);
		
		this.PontosSegundoJogador = new JLabel("0", SwingConstants.CENTER);
		this.PontosSegundoJogador.setFont(new Font( "Arial", Font.BOLD, 16 ) );
		this.PontosSegundoJogador.setLocation(677, 89);
		this.PontosSegundoJogador.setSize(112,24);
		this.add(this.PontosSegundoJogador);
		
	}
	
	
	public VisaoTabuleiro getTabuleiro(){
		return this.visaoTabuleiro;
	}
	
	public void ConstruirTabuleiro(int x, int y){
		this.visaoTabuleiro = new VisaoTabuleiro();
		
		this.visaoTabuleiro.setLocation(x, y);
		this.add(visaoTabuleiro);
	}
	
	
	public void InserirPeca(ControlePeca Peca){
		//System.out.println("INSERINDO PECA: "+Peca.toString());
		this.visaoTabuleiro.InserirPeca(Peca, Peca.getCoordenada());		
	}
	
	public void RemoverPeca(Coordenada coord){
		this.visaoTabuleiro.RemoverPeca(coord);		
	}
	
	public void MoverPeca(ControlePeca Peca, int linOrigem, int colOrigem, int linDest, int colDest){
		this.visaoTabuleiro.RemoverPeca(new Coordenada(linOrigem, colOrigem));
		this.visaoTabuleiro.InserirPeca(Peca, new Coordenada(linOrigem, colOrigem));		
	}
	
	
	public void ConstruirMenu(int x, int y){
		this.botaoVoltar = new VisaoBotao();
		this.botaoVoltar.DefinirImagens("/img/Menu/Voltar_Over.png", "/img/Menu/Voltar_Pressed.png", "/img/Menu/Voltar_Released.png");
		
		this.botaoDesistir = new VisaoBotao();
		this.botaoDesistir.DefinirImagens("/img/Menu/Desistir_Over.png", "/img/Menu/Desistir_Pressed.png", "/img/Menu/Desistir_Released.png");
		
		this.botaoDesistir.setLocation(x,y);
		this.botaoVoltar.setLocation(x,y+30);		
		
		this.add(this.botaoDesistir);
		this.add(this.botaoVoltar);
	}
	
	public VisaoBotao getBotao(int i) {
		switch (i){
			case 1:
				return this.botaoVoltar;
			
			default:
				return null;
		}
	}
	
}
