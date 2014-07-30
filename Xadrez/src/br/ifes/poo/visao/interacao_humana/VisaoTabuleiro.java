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
	
	private VisaoSlot[][] matrizSlots = {{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null}};
	private ArrayList<Coordenada> movimentosPossiveis = null;
	private MonitorSlots monitorSlots = null;
	private VisaoSlot slotAtivo = null;	
	private ControleJogador jogadorAtivo=null;
	private ControleJogo controleJogo;
	
		
	public VisaoTabuleiro(){
				
		System.out.println("INICIANDO A VISAO DO TABULEIRO");
		
		this.monitorSlots = new MonitorSlots();
		this.monitorSlots.setTabuleiro(this);		
		this.movimentosPossiveis = new ArrayList<Coordenada>();		
		this.setSize(656, 585);
		this.setBackground(new Color(255,255,255,0));
		this.setOpaque(false);
		this.setLayout(new GridLayout(8, 8));
		
		for(int k=0;k<8;k++){
			for(int i=0; i < 8; i++){
				
				VisaoSlot Slot = new VisaoSlot();
				Slot.adicionarObservador(monitorSlots);
				Slot.definirImagens("/img/Tabuleiro/Slot_Over.png", "/img/Tabuleiro/Slot_Pressed.png", "/img/Tabuleiro/Slot_Released.png");
				Slot.setPathPossibilidade("/img/Tabuleiro/Slot_Possivel.png");
				Slot.setPathEnemy("/img/Tabuleiro/Slot_Inimigo.png");
				Slot.setPathXeque("/img/Tabuleiro/Slot_Cheque.png");
				Slot.setCoordenada(new Coordenada(k, i));
				this.add(Slot);
				this.matrizSlots[k][i] = Slot;
				
			}
		}	
		this.setVisible(true);
	}
	
	
	public void setControleJogo(ControleJogo controleJogo){
		this.controleJogo = controleJogo;
	}
	
	public void setJogadorAtivo(ControleJogador jogador){
		this.jogadorAtivo = jogador;
		VisaoSlot.setCorAtiva(this.jogadorAtivo.getCor());
	}
	
	public ControleJogador getJogadorAtivo(){
		return this.jogadorAtivo;
	}	
	
	public void ativarSlot(Coordenada coord){
		this.matrizSlots[coord.getLinha()][coord.getColuna()].marcarPeca();
	}		
	
	public void ativarSlot(VisaoSlot slot){
		
		if(this.movimentosPossiveis.isEmpty()){
			if(this.slotAtivo != null){ 	// TINHAMOS UMA PECA SELECIONADA QUE NAO TINHA NENHUM MOVIMENTO POSSIVEL. TEMOS DESATIVAR ELA PRA ATIVAR O NOVO SLOT SELECIONADO.
				this.desativarSlot(this.slotAtivo);
			}
			
			this.slotAtivo = slot;
			this.definirMovimentosPossiveisSlotAtivo();			
			
			if(!this.movimentosPossiveis.isEmpty()){
				this.marcarMovimentosPossiveis(this.movimentosPossiveis);
				this.marcarInimigosPossiveis(this.movimentosPossiveis);			
			}
			
			else{
				// NAO TEM MOVIMENTO NENHUM POSSIVEL
			}
		}
		else{
			if(slot.getControlePeca() == null) this.moverPeca(slot);
			
			else{
			
				if(this.slotAtivo.getControlePeca().getModeloPeca().getCor() != slot.getControlePeca().getModeloPeca().getCor()){
					
					if(slot.getSlotInimigo()){
						System.out.println("TABULEIRO >>> "+this.slotAtivo.getControlePeca().toString()+" CAPTUROU "+slot.getControlePeca().toString()+"!");
						this.capturarPeca(slot);
					}
				}
				else{
					// O JOGADOR TROCOU A SUA PECA ATIVA POR OUTRA DELE PROPRIO
					this.desativarSlot(slotAtivo);
					this.slotAtivo = slot;
					this.definirMovimentosPossiveisSlotAtivo();
										
					if(!this.movimentosPossiveis.isEmpty()){
						//System.out.println("TABULEIRO >>> MOVIMENTOS POSSIVEIS: "+this.MovimentosPossiveis);
						this.marcarMovimentosPossiveis(this.movimentosPossiveis);
						this.marcarInimigosPossiveis(this.movimentosPossiveis);				
					}
					
					else{
						//System.out.println("TABULEIRO >>> NAO TEM MOVIMENTO NENHUM POSSIVEL");
					}
				}
			}
		}
	}
	
	public void definirMovimentosPossiveisSlotAtivo(){
		ArrayList<Coordenada> movimentos = this.slotAtivo.getControlePeca().getModeloPeca().verificarMovimentosPossiveis(this.matrizSlots);
		if(movimentos!=null){
			if(!movimentos.isEmpty()){
				this.movimentosPossiveis.addAll(movimentos);			
			}
		}
		else{
			System.out.println("Nao tinha movimentos possiveis?");
		}
	}
	
	public void desativarSlot(VisaoSlot slot){
		//System.out.println("TABULEIRO >>> TEMOS QUE DESATIVAR AS POSSIBILIDADE DE MOVIMENTO: "+this.MovimentosPossiveis);
		this.desmarcarMovimentosPossiveis(this.movimentosPossiveis);		
		this.slotAtivo.getControlePeca().getModeloPeca().resetarMovimentosPossiveis();		
		this.movimentosPossiveis.clear();
		this.slotAtivo.desmarcarPeca();
	}	
	
	public void moverPeca(VisaoSlot slotDestino){
				
		System.out.println("TABULEIRO >>> MOVENDO "+this.slotAtivo.getControlePeca().toString()+" para "+slotDestino.getCoordenada().toString());
		this.removerPeca(this.slotAtivo.getCoordenada());
		this.inserirPeca(this.slotAtivo.getControlePeca(), slotDestino.getCoordenada());
		this.ativarEfeito();
		this.verificarAmeaca();
		this.verificarXeque();		
		this.desativarSlot(this.slotAtivo);		
		this.slotAtivo.setControlePeca(null);
		this.slotAtivo = null;
		this.controleJogo.proximoJogadorAtivo();
	}
	
	
	public void verificarAmeaca(){
		ControleJogador controleJogadorAdversario = null;
		if(this.jogadorAtivo != this.controleJogo.getControlePrimeiroJogador()){
			controleJogadorAdversario = this.controleJogo.getControlePrimeiroJogador();
		}
		else{
			controleJogadorAdversario = this.controleJogo.getControleSegundoJogador();
		}
		
		// DESMARCANDO AS AMEACAS ANTIGAS PARA REMARCAR AS NOVAS
		for(int k = 0; k < controleJogadorAdversario.getPecas().size(); k++){
			controleJogadorAdversario.getPecas().get(k).getModeloPeca().setAmeacado(false);
		}
		
		ArrayList<Coordenada> Coordenadas = this.slotAtivo.getControlePeca().getModeloPeca().verificarMovimentosPossiveis(this.matrizSlots);
		for(int g = 0; g < Coordenadas.size(); g++){
			if(this.matrizSlots[Coordenadas.get(g).getLinha()][Coordenadas.get(g).getColuna()].getControlePeca() != null){ // E TEM QUE SER DE COR OPOSTA){
				this.matrizSlots[Coordenadas.get(g).getLinha()][Coordenadas.get(g).getColuna()].marcarInimigo(false);
			}			
		}
	}
	
	public void verificarXeque(){
		ArrayList<Coordenada> Coordenadas = this.slotAtivo.getControlePeca().getModeloPeca().verificarMovimentosPossiveis(this.matrizSlots);
		for(int g = 0; g < Coordenadas.size(); g++){
			if(this.matrizSlots[Coordenadas.get(g).getLinha()][Coordenadas.get(g).getColuna()].getControlePeca() != null){
				if(this.matrizSlots[Coordenadas.get(g).getLinha()][Coordenadas.get(g).getColuna()].getControlePeca().getModeloPeca().getTipo().equals("REI")){
					this.matrizSlots[Coordenadas.get(g).getLinha()][Coordenadas.get(g).getColuna()].marcarXeque();
				}	
			}			
		}
	}
	
	
	public void capturarPeca(VisaoSlot slot){
		this.controleJogo.getVisaoJogo().atualizarCemiterio(jogadorAtivo, slot.getControlePeca());
		
		if(slot.getControlePeca().getModeloPeca().getTipo().equals("REI")){
			// E O FIM DO JOGO!
			this.removerPeca(slot.getCoordenada());
			this.controleJogo.declararVencedor(getJogadorAtivo());
			this.moverPeca(slot);				
		}
		else{
			this.removerPeca(slot.getCoordenada());
			this.controleJogo.getJogadorAtivo().addPontos(slot.getControlePeca().getModeloPeca().getValor());
			this.controleJogo.atualizarPlacar();
			this.moverPeca(slot);	
		}		
	}
	
	public void marcarInimigosPossiveis(ArrayList<Coordenada> possibilidades){
		
		for(int i=0; i < possibilidades.size() ; i++){
			if(this.matrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].getControlePeca() != null){
				if(!this.matrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].getControlePeca().getModeloPeca().getCor().equals(this.slotAtivo.getControlePeca().getModeloPeca().getCor())){
					if(this.matrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].getControlePeca().getModeloPeca().getTipo().equals("REI")){
						this.matrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].marcarXeque();
					}
					else{
						this.matrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].marcarInimigo(true);	
					}						
				}	
			}			
		}
	}	
	
	public void desmarcarMovimentosPossiveis(ArrayList<Coordenada> possibilidades){
		for(int i=0; i < possibilidades.size(); i++){
			this.matrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].desmarcarPeca();
			this.matrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].setSlotPossivel(false);
		}		
	}
	
	public void marcarMovimentosPossiveis(ArrayList<Coordenada> possibilidades){
		for(int i=0; i < possibilidades.size(); i++){
			this.matrizSlots[possibilidades.get(i).getLinha()][possibilidades.get(i).getColuna()].marcarPossibilidade();
		}
	}
	
	public String getCorJogadorAtivo(){
		return this.jogadorAtivo.getCor();
	}
	
	public VisaoSlot getSlotAtivo(){
		return this.slotAtivo;
	}
	
	public void ativarEfeito(){
		String Peca = this.slotAtivo.getControlePeca().getModeloPeca().getTipo();
		
		if(Peca.contains("PEAO")){
			for(int g =0; g < this.slotAtivo.getControlePeca().getModeloPeca().getPadraoMovimento().size(); g++ ){
				if(this.slotAtivo.getControlePeca().getModeloPeca().getPadraoMovimento().get(g) instanceof TipoMovimentoPeaoInicial ){
					this.slotAtivo.getControlePeca().getModeloPeca().getPadraoMovimento().remove(g);
					break;
				}
			}			
		}
		
		if(this.slotAtivo.getControlePeca().getModeloPeca().getAmeacado()) this.slotAtivo.getControlePeca().getModeloPeca().setAmeacado(false);
	}
	
	public VisaoSlot[][] getMatrizSlots(){
		return this.matrizSlots;
	}
	
	public void inserirPeca(ControlePeca peca, Coordenada coord){
		this.matrizSlots[coord.getLinha()][ coord.getColuna()].inserirPeca(peca);
		peca.setCoordenada(coord);
	}
	
	public void removerPeca(Coordenada coord){
		this.matrizSlots[coord.getLinha()][coord.getColuna()].removerPeca();
	}
}
