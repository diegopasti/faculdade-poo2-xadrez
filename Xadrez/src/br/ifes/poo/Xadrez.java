package br.ifes.poo;

import br.ifes.poo.controle.controle_interface.ControleAplicacao;

public class Xadrez {

	public static void main(String[] args) {
		
		// Criar uma tela de Carregamento do Arquivo (Metodo Proxie)
		System.out.println("INICIANDO O XADREZ");
		ControleAplicacao Xadrez = new ControleAplicacao();
		Xadrez.IniciarAplicativo(); 
	}
}
