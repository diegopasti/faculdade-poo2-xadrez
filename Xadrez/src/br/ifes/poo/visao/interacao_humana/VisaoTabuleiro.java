package br.ifes.poo.visao.interacao_humana;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import br.ifes.poo.controle.controle_interface.ControleJogador;
import br.ifes.poo.controle.controle_interface.ControleJogo;
import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.modelo.dominio_problema.pecas.TipoMovimentoPeaoInicial;
import br.ifes.poo.modelo.gerencia_tarefas.MonitorSlots;
import br.ifes.poo.utils.Coordenada;

@SuppressWarnings("serial")
public class VisaoTabuleiro extends JPanel{	
	
	private VisaoSlot[][] MatrizSlots = {{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null}};
	private ArrayList<Coordenada> MovimentosPossiveis = null;
	private MonitorSlots MonitorSlots = null;
	private VisaoSlot SlotAtivo = null;
	
	private ControleJogador JogadorAtivo=null;
	private ControleJogo controleJogo;
	
		
	public VisaoTabuleiro(){
				
		System.out.println("INICIANDO A VISAO DO TABULEIRO");
		
		this.MonitorSlots = new MonitorSlots();
		this.MonitorSlots.setTabuleiro(this);
		
		this.MovimentosPossiveis = new ArrayList<Coordenada>();
		
		this.setSize(656, 585);
		this.setBackground(new Color(255,255,255,0));
		this.setOpaque(false);
		this.setLayout(new GridLayout(8, 8));
		
		for(int k=0;k<8;k++){
			for(int i=0; i < 8; i++){
				
				VisaoSlot Slot = new VisaoSlot();
				Slot.AdicionarObservador(MonitorSlots);
				Slot.DefinirImagens("/img/Tabuleiro/Slot_Over.png", "/img/Tabuleiro/Slot_Pressed.png", "/img/Tabuleiro/Slot_Released.png");
				Slot.setPathPossibilidade("/img/Tabuleiro/Slot_Possivel.png");
				Slot.setPathEnemy("/img/Tabuleiro/Slot_Inimigo.png");
				Slot.setPathXeque("/img/Tabuleiro/Slot_Cheque.png");
				Slot.setCoordenada(new Coordenada(k, i));
				this.add(Slot);
				this.MatrizSlots[k][i] = Slot;
				
			}
		}	
		this.setVisible(true);
	}
	
	
	public void setControleJogo(ControleJogo controleJogo){
		this.controleJogo = controleJogo;
	}
	
	public void setJogadorAtivo(ControleJogador jogador){
		this.JogadorAtivo = jogador;
		VisaoSlot.setCorAtiva(this.JogadorAtivo.getCor());
	}
	
	public ControleJogador getJogadorAtivo(){
		return this.JogadorAtivo;
	}
	
	
	public void AtivarSlot(Coordenada coord){
		this.MatrizSlots[coord.getLinha()][coord.getColuna()].MarcarPeca();
		//this.AtivarSlot(this.MatrizSlots[coord.getLinha()][coord.getColuna()]);
	}
		
	
	public void AtivarSlot(VisaoSlot slot){
		
		if(this.MovimentosPossiveis.isEmpty()){
			if(this.SlotAtivo != null){ 	// TINHAMOS UMA PECA SELECIONADA QUE NAO TINHA NENHUM MOVIMENTO POSSIVEL. TEMOS DESATIVAR ELA PRA ATIVAR O NOVO SLOT SELECIONADO.
				this.DesativarSlot(this.SlotAtivo);
			}
			
			this.SlotAtivo = slot;
			this.DefinirMovimentosPossiveisSlotAtivo();			
			
			if(!this.MovimentosPossiveis.isEmpty()){
				//System.out.println("TABULEIRO >>> MOVIMENTOS POSSIVEIS: "+this.MovimentosPossiveis);
				this.MarcarMovimentosPossiveis(this.MovimentosPossiveis);
				this.MarcarInimigosPossiveis(this.MovimentosPossiveis);			
			}
			
			else{
				//System.out.println("TABULEIRO >>> NAO TEM MOVIMENTO NENHUM POSSIVEL");
			}
			
			
			
		}
		else{
			if(slot.getControlePeca() == null){
				this.MoverPeca(slot);
			}
			
			else{
			
				if(this.SlotAtivo.getControlePeca().getModeloPeca().getCor() != slot.getControlePeca().getModeloPeca().getCor()){
					
					if(slot.getSlotInimigo()){
						System.out.println("TABULEIRO >>> "+this.SlotAtivo.getControlePeca().toString()+" CAPTUROU "+slot.getControlePeca().toString()+"!");
						this.CapturarPeca(slot);
					}
				}
				else{
					// O JOGADOR TROCOU A SUA PECA ATIVA POR OUTRA DELE PROPRIO
					this.DesativarSlot(SlotAtivo);
					this.SlotAtivo = slot;
					this.DefinirMovimentosPossiveisSlotAtivo();
					
					
					if(!this.MovimentosPossiveis.isEmpty()){
						//System.out.println("TABULEIRO >>> MOVIMENTOS POSSIVEIS: "+this.MovimentosPossiveis);
						this.MarcarMovimentosPossiveis(this.MovimentosPossiveis);
						this.MarcarInimigosPossiveis(this.MovimentosPossiveis);				
					}
					
					else{
						//System.out.println("TABULEIRO >>> NAO TEM MOVIMENTO NENHUM POSSIVEL");
					}
				}
			}
		}
	}
	
	public void DefinirMovimentosPossiveisSlotAtivo(){
		this.MovimentosPossiveis.addAll(this.SlotAtivo.getControlePeca().getModeloPeca().VerificarMovimentosPossiveis(this.MatrizSlots));
	}
	
	public void DesativarSlot(VisaoSlot slot){
		//System.out.println("TABULEIRO >>> TEMOS QUE DESATIVAR AS POSSIBILIDADE DE MOVIMENTO: "+this.MovimentosPossiveis);
		this.DesmarcarMovimentosPossiveis(this.MovimentosPossiveis);		
		this.SlotAtivo.getControlePeca().getModeloPeca().ResetarMovimentosPossiveis();
		
		this.MovimentosPossiveis.clear();
		this.SlotAtivo.DesmarcarPeca();
	}
	
	
	public void MoverPeca(VisaoSlot SlotDestino){
				
		System.out.println("TABULEIRO >>> MOVENDO "+this.SlotAtivo.getControlePeca().toString()+" para "+SlotDestino.getCoordenada().toString());
		this.RemoverPeca(this.SlotAtivo.getCoordenada());
		this.InserirPeca(this.SlotAtivo.getControlePeca(), SlotDestino.getCoordenada());
		
		this.AtivarEfeito();
		this.VerificarAmeaca();
		this.VerificarXeque();		
		
		this.DesativarSlot(this.SlotAtivo);		
		this.SlotAtivo.setControlePeca(null);
		this.SlotAtivo = null;
		
		this.controleJogo.ProximoJogadorAtivo();
	}
	
	
	public void VerificarAmeaca(){
		ControleJogador controleJogadorAdversario = null;
		if(this.JogadorAtivo != this.controleJogo.getControlePrimeiroJogador()){
			controleJogadorAdversario = this.controleJogo.getControlePrimeiroJogador();
		}
		else{
			controleJogadorAdversario = this.controleJogo.getControleSegundoJogador();
		}
		
		// DESMARCANDO AS AMEACAS ANTIGAS PARA REMARCAR AS NOVAS
		for(int k = 0; k < controleJogadorAdversario.getPecas().size(); k++){
			controleJogadorAdversario.getPecas().get(k).getModeloPeca().setAmeacado(false);
		}
		
		ArrayList<Coordenada> Coordenadas = this.SlotAtivo.getControlePeca().getModeloPeca().VerificarMovimentosPossiveis(this.MatrizSlots);
		for(int g = 0; g < Coordenadas.size(); g++){
			if(this.MatrizSlots[Coordenadas.get(g).getLinha()][Coordenadas.get(g).getColuna()].getControlePeca() != null){ // E TEM QUE SER DE COR OPOSTA){
				this.MatrizSlots[Coordenadas.get(g).getLinha()][Coordenadas.get(g).getColuna()].MarcarInimigo(false);
			}
			
		}
	}
	
	public void VerificarXeque(){
		ArrayList<Coordenada> Coordenadas = this.SlotAtivo.getControlePeca().getModeloPeca().VerificarMovimentosPossiveis(this.MatrizSlots);
		for(int g = 0; g < Coordenadas.size(); g++){
			if(this.MatrizSlots[Coordenadas.get(g).getLinha()][Coordenadas.get(g).getColuna()].getControlePeca() != null){
				if(this.MatrizSlots[Coordenadas.get(g).getLinha()][Coordenadas.get(g).getColuna()].getControlePeca().getModeloPeca().getTipo() == "REI"){
					this.MatrizSlots[Coordenadas.get(g).getLinha()][Coordenadas.get(g).getColuna()].MarcarXeque();
				}	
			}
			
		}
	}
	
	
	public void CapturarPeca(VisaoSlot slot){
		if(slot.getControlePeca().getModeloPeca().getTipo() == "REI"){
			// E O FIM DO JOGO!
			this.RemoverPeca(slot.getCoordenada());
			this.controleJogo.DeclararVencedor(getJogadorAtivo());
			this.MoverPeca(slot);	
			
		}
		else{
			this.RemoverPeca(slot.getCoordenada());
			this.controleJogo.getJogadorAtivo().addPontos(slot.getControlePeca().getModeloPeca().getValor());
			this.controleJogo.AtualizarPlacar();
			this.MoverPeca(slot);	
		}
		
	}
	
	public void MarcarInimigosPossiveis(ArrayList<Coordenada> possibilidades){
		
		for(int i=0; i < possibilidades.size() ; i++){
			if(this.MatrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].getControlePeca() != null){
				if(this.MatrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].getControlePeca().getModeloPeca().getCor() != this.SlotAtivo.getControlePeca().getModeloPeca().getCor()){
					if(this.MatrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].getControlePeca().getModeloPeca().getTipo() == "REI"){
						this.MatrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].MarcarXeque();
					}
					else{
						this.MatrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].MarcarInimigo(true);	
					}
						
				}	
			}			
		}
	}
	
	
	public void DesmarcarMovimentosPossiveis(ArrayList<Coordenada> possibilidades){
		//System.out.println("TABULEIRO >>> "+possibilidades.size()+" VOU DESMARCAR O CAMINHO DO: "+this.SlotAtivo.getControlePeca().toString());
		for(int i=0; i < possibilidades.size(); i++){
			//System.out.println("TABULEIRO >>> SLOT: "+possibilidades.get(i).toString());
			this.MatrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].DesmarcarPeca();
			this.MatrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].setSlotPossivel(false);
		}
		
	}
	
	public void MarcarMovimentosPossiveis(ArrayList<Coordenada> possibilidades){
		//System.out.println("TABULEIRO >>> QUANTIDADE DE MOVIMENTOS POSSIVEIS: "+possibilidades.size());
		
		for(int i=0; i < possibilidades.size(); i++){
			//System.out.println("TABULEIRO >>> SLOT: "+possibilidades.get(i).toString());
			this.MatrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].MarcarPossibilidade();
		}
	
	}
	
	public String getCorJogadorAtivo(){
		return this.JogadorAtivo.getCor();
	}

	
	public VisaoSlot getSlotAtivo(){
		return this.SlotAtivo;
	}
	
	
	
	public void AtivarEfeito(){
		String Peca = this.SlotAtivo.getControlePeca().getModeloPeca().getTipo();
		
		if(Peca.contains("PEAO")){
			// REMOVE A OPCAO DE MOVER DUAS CASAS A FRENTE APOS TER REALIZADO SEU PRIMEIRO MOVIMENTO.
			for(int g =0; g < this.SlotAtivo.getControlePeca().getModeloPeca().getPadraoMovimento().size(); g++ ){
				if(this.SlotAtivo.getControlePeca().getModeloPeca().getPadraoMovimento().get(g) instanceof TipoMovimentoPeaoInicial ){
					//System.out.println("O PEAO VAI PERDER O CAPACIDADE DE ANDAR DUAS CASAS");
					this.SlotAtivo.getControlePeca().getModeloPeca().getPadraoMovimento().remove(g);
					break;
				}
			}			
		}
		
		if(this.SlotAtivo.getControlePeca().getModeloPeca().getAmeacado()){
			this.SlotAtivo.getControlePeca().getModeloPeca().setAmeacado(false);
		}
		
	}
	
	public VisaoSlot[][] getMatrizSlots(){
		return this.MatrizSlots;
	}
	
	
	public void InserirPeca(ControlePeca Peca, Coordenada coord){
		this.MatrizSlots[coord.getLinha()][ coord.getColuna()].InserirPeca(Peca);
		Peca.setCoordenada(coord);
	}
	
	public void RemoverPeca(Coordenada coord){
		this.MatrizSlots[coord.getLinha()][coord.getColuna()].RemoverPeca();
	}
}
