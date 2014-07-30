package br.ifes.poo.visao.interacao_humana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import br.ifes.poo.controle.controle_interface.ControlePeca;
import br.ifes.poo.modelo.gerencia_tarefas.MonitorSlots;
import br.ifes.poo.utils.Coordenada;

@SuppressWarnings("serial")
public class VisaoSlot extends JPanel implements MouseListener{

	private Boolean slotSelecionado=false;       // Armazena o estado do Slot. Se esta ou não Selecionado.
	private Boolean slotInimigo=false;
	private Boolean slotPossivel=false;
	private Boolean slotXeque=false;
	private Coordenada coordenada;
	
	private ControlePeca controlePeca = null;     // Armazena a Peca que esta contida no slot.
	private JButton botaoSlot;         // Representa o Slot como um Botao no tabuleiro.
	
	private String pathReleased;       // 
	private String pathOverlapping;    // Path dos Arquivos de Imagem para usar no controle de eventos basicos da Imagem
	private String pathPressed;        //
	
	private String pathPossibilidade;  // 
	private String pathEnemy;        //
	private String pathXeque;        // Path dos Arquivos de Imagem para usar no controle de eventos adicionais da Imagem
	
	//private String PathXequeMate;    //
	private MonitorSlots monitor = null;	
	private static String corAtiva;
	
	public static String getCorAtiva(){
		return corAtiva;
	}
	
	public static void setCorAtiva(String cor){
		corAtiva = cor;
	}
	
	public VisaoSlot(){
		
		this.setSelecionado(false);
		this.setSlotPossivel(false);
		this.setSlotInimigo(false);		
		this.setLayout(null);		
		this.setOpaque(false);
		this.botaoSlot = new JButton();
		this.botaoSlot.setFocusable(false);
		this.botaoSlot.setContentAreaFilled(false);
		this.botaoSlot.setBorderPainted(false);
		this.botaoSlot.setSize(82,74);
		this.botaoSlot.addMouseListener(this);
		this.botaoSlot.setLocation(0, -2);
		this.botaoSlot.addMouseListener(this);
		
		this.botaoSlot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// MARCANDO UMA PECA
				if(!getSelecionado() && (controlePeca != null)) if(controlePeca.getModeloPeca().getCor() == VisaoSlot.corAtiva || getSlotPossivel()) marcarPeca();
								
				// MOVENDO UMA PECA
				else if (controlePeca == null && getSlotPossivel())	moverPeca();
				
				// DESMARCANDO UMA PECA
				else if(controlePeca != null){
					desmarcarPeca();				
					NotificarObservador("DESMARCAR");	
				}
			}
		});
		
		this.add(this.botaoSlot);
	}	
	
	public ControlePeca getControlePeca(){
		return this.controlePeca;
	}
	
	public void setControlePeca(ControlePeca peca){
		this.controlePeca = null;
	}
	
	public void moverPeca(){
		this.NotificarObservador("MOVER");		
	}
	
	public void marcarPeca(){
		this.setSelecionado(true);
		this.atualizarBotao(getPathPressed());
		this.NotificarObservador("MARCAR");
	}
		
	public void marcarInimigo(Boolean atualizarImagem){
		this.setSlotInimigo(true);
		if(atualizarImagem)	this.atualizarBotao(this.getPathEnemy());	
		this.getControlePeca().getModeloPeca().setAmeacado(true);
	}
	
	public void marcarXeque(){
		this.atualizarBotao(this.getPathXeque());
		this.setSlotXeque(true);
		this.setSlotInimigo(true);
	}
	
	public void desmarcarPeca(){		
		this.setSelecionado(false);
		this.setSlotInimigo(false);
		this.setSlotXeque(false);
		this.atualizarBotao(getPathReleased());
	}
	
	public void marcarPossibilidade(){
		this.atualizarBotao(getPathPossibilidade());
		this.setSlotPossivel(true);		
	}
	
	public void adicionarObservador(MonitorSlots monitor){
		this.monitor = monitor;		
	}
	
	public void NotificarObservador(String acao){
		
		if(acao.equals("MARCAR")){
			this.monitor.setSlotAtivo(this);
			this.monitor.update(acao);
		}
		
		else if (acao.equals("MOVER")){
			this.monitor.setSlotAtivo(this);
			this.monitor.update(acao);
		}
		
		else if (acao.equals("DESMARCAR")) this.monitor.update(acao);
	}
	
	public void removerObservador(){
		this.monitor = null;
	}
	
	public void inserirPeca(ControlePeca peca){
		peca.getVisaoPeca().setSize(82, 74);
		peca.getVisaoPeca().setLocation(-1, -1);
		this.controlePeca = peca;
		this.add(this.controlePeca.getVisaoPeca());
	}
	
	public void removerPeca(){
		if(this.controlePeca != null){
			this.remove(this.controlePeca.getVisaoPeca());
			this.atualizarBotao(this.getPathReleased());
		}
	}

	public void definirImagens(String over, String pressed, String released){
		this.setPathOverlapping(over);
		this.setPathPressed(pressed);
		this.setPathReleased(released);
		this.atualizarBotao(this.getPathReleased());
	}
	
	public void atualizarBotao(String path){
		this.botaoSlot.setIcon(null);
		this.botaoSlot.setIcon(getIcone(path));
	}
	
	public ImageIcon getIcone(String path) {
		return new ImageIcon(getClass().getResource(path));
	}
		
	public JButton getBotaoSlot() {
		return this.botaoSlot;
	}

	public void setBotaoSlot(VisaoBotao botaoSlot) {
		this.botaoSlot = botaoSlot;
	}

	public Boolean getSelecionado() {
		return this.slotSelecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.slotSelecionado = selecionado;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(this.controlePeca != null){			
			if(this.controlePeca.getModeloPeca().getCor() != VisaoSlot.corAtiva) if(this.getSlotPossivel()) this.atualizarBotao(this.getPathOverlapping());	
			
			else this.atualizarBotao(this.getPathOverlapping());
		}
		
		else if(this.getSlotPossivel()) this.atualizarBotao(this.getPathOverlapping());	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(this.controlePeca != null){
			if(this.getSlotPossivel()){
				
				if(this.getSlotInimigo()){
					if(this.getSlotXeque()) this.atualizarBotao(this.getPathXeque());
					
					else this.atualizarBotao(this.getPathEnemy());
				}
				else{
					
					if(!this.getSelecionado()) this.atualizarBotao(this.getPathReleased());
					
					else this.atualizarBotao(this.getPathPossibilidade());
				}
			}
			else this.atualizarBotao(this.getPathReleased());
						
			if(this.getSelecionado()) this.atualizarBotao(this.getPathPressed());
		}

		else if(this.getSlotPossivel())	this.atualizarBotao(this.getPathPossibilidade());
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public String getPathReleased() {
		return this.pathReleased;
	}

	public void setPathReleased(String pathReleased) {
		this.pathReleased = pathReleased;
	}

	public String getPathOverlapping() {
		return this.pathOverlapping;
	}

	public void setPathOverlapping(String pathOverlapping) {
		this.pathOverlapping = pathOverlapping;
	}	
	
	public String getPathEnemy() {
		return this.pathEnemy;
	}

	public void setPathEnemy(String pathEnemy) {
		this.pathEnemy = pathEnemy;
	}	

	public String getPathPressed() {
		return this.pathPressed;
	}

	public void setPathPressed(String pathPressed) {
		this.pathPressed = pathPressed;
	}

	public String getPathPossibilidade() {
		return this.pathPossibilidade;
	}

	public void setPathPossibilidade(String pathPossibilidade) {
		this.pathPossibilidade = pathPossibilidade;
	}

	public Boolean getSlotPossivel() {
		return this.slotPossivel;
	}

	public void setSlotPossivel(Boolean slotPossivel) {
		this.slotPossivel = slotPossivel;
	}

	public Coordenada getCoordenada() {
		return this.coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public Boolean getSlotInimigo() {
		return this.slotInimigo;
	}

	public void setSlotInimigo(Boolean slotInimigo) {
		this.slotInimigo = slotInimigo;
	}

	public String getPathXeque() {
		return pathXeque;
	}

	public void setPathXeque(String pathXeque) {
		this.pathXeque = pathXeque;
	}

	public Boolean getSlotXeque() {
		return slotXeque;
	}

	public void setSlotXeque(Boolean slotXeque) {
		this.slotXeque = slotXeque;
	}
}
