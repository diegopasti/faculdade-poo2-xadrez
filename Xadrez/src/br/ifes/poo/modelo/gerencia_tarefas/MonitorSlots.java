package br.ifes.poo.modelo.gerencia_tarefas;

import br.ifes.poo.utils.Observador;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;
import br.ifes.poo.visao.interacao_humana.VisaoTabuleiro;

public class MonitorSlots implements Observador {

	private VisaoSlot Slot = null;
	private VisaoTabuleiro Tabuleiro = null;
		
	@Override
	public void update(String acao) {
		
		
		if(acao.equals("MARCAR")){
			if(this.Slot.getControlePeca() == null){
			}
			else{
				System.out.println("\nMONITOR   >>> "+this.Slot.getControlePeca().getModeloPeca().getTipo()+" "+this.Slot.getCoordenada().toString()+" FOI SELECIONADO");
			}
			this.Tabuleiro.ativarSlot(this.Slot);
		}
		
		else if(acao.equals("MOVER")){
			//System.out.println("MONITOR >> GALERA SLOT["+this.Slot.getCoordenada().toString()+"] FOI MOVIDO!");
			this.Tabuleiro.moverPeca(this.Slot);
		}
		
		else if(acao.equals("DESMARCAR")){
			//System.out.println("MONITOR >> GALERA SLOT["+this.Slot.getCoordenada().toString()+"] FOI DESMARCADO!");
			this.Tabuleiro.desativarSlot(this.Slot);
		}
		 
	}

	public void setSlotAtivo(VisaoSlot slot){
		this.Slot = slot;
	}
	
	public void desativarSlot(VisaoSlot slot){
		this.Slot = null;
	}

	public VisaoTabuleiro getTabuleiro() {
		return Tabuleiro;
	}

	public void setTabuleiro(VisaoTabuleiro tabuleiro) {
		Tabuleiro = tabuleiro;
	}
	
	
	
	
}
