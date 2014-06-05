package br.ifes.poo.modelo.gerencia_tarefas;

import br.ifes.poo.utils.Observador;
import br.ifes.poo.visao.interacao_humana.VisaoSlot;
import br.ifes.poo.visao.interacao_humana.VisaoTabuleiro;

public class MonitorSlots implements Observador {

	private VisaoSlot Slot = null;
	private VisaoTabuleiro Tabuleiro = null;
		
	@Override
	public void Update(String Acao) {
		
		
		if(Acao == "MARCAR"){
			if(this.Slot.getControlePeca() == null){
			}
			else{
				System.out.println("\nMONITOR   >>> "+this.Slot.getControlePeca().getModeloPeca().getTipo()+" "+this.Slot.getCoordenada().toString()+" FOI SELECIONADO");
			}
			this.Tabuleiro.AtivarSlot(this.Slot);
		}
		
		else if(Acao == "MOVER"){
			//System.out.println("MONITOR >> GALERA SLOT["+this.Slot.getCoordenada().toString()+"] FOI MOVIDO!");
			this.Tabuleiro.MoverPeca(this.Slot);
		}
		
		else if(Acao == "DESMARCAR"){
			//System.out.println("MONITOR >> GALERA SLOT["+this.Slot.getCoordenada().toString()+"] FOI DESMARCADO!");
			this.Tabuleiro.DesativarSlot(this.Slot);
		}
		 
	}

	public void setSlotAtivo(VisaoSlot slot){
		this.Slot = slot;
	}
	
	public void DesativarSlot(VisaoSlot slot){
		this.Slot = null;
	}

	public VisaoTabuleiro getTabuleiro() {
		return Tabuleiro;
	}

	public void setTabuleiro(VisaoTabuleiro tabuleiro) {
		Tabuleiro = tabuleiro;
	}
	
	
	
	
}
