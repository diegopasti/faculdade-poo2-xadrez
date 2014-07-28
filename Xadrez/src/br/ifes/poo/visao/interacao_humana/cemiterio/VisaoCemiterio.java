package br.ifes.poo.visao.interacao_humana.cemiterio;

import br.ifes.poo.controle.controle_interface.ControlePeca;

public interface VisaoCemiterio {

	public void capturarPeca(ControlePeca peca);
	public void setLocation(int x, int y);
	
}
