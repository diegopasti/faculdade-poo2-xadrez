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

	private Boolean SlotSelecionado=false;       // Armazena o estado do Slot. Se esta ou não Selecionado.
	private Boolean SlotInimigo=false;
	private Boolean SlotPossivel=false;
	private Boolean SlotXeque=false;
	private Coordenada Coordenada;
	
	private ControlePeca controlePeca = null;     // Armazena a Peca que esta contida no slot.
	private JButton botaoSlot;         // Representa o Slot como um Botao no tabuleiro.
	
	
	private String PathReleased;       // 
	private String PathOverlapping;    // Path dos Arquivos de Imagem para usar no controle de eventos basicos da Imagem
	private String PathPressed;        //
	
	
	
	private String PathPossibilidade;  // 
	private String PathEnemy;        //
	private String PathXeque;        // Path dos Arquivos de Imagem para usar no controle de eventos adicionais da Imagem
	//private String PathXequeMate;    //
	
	private MonitorSlots Monitor = null;
	
	private static String CorAtiva;
	
	public static String getCorAtiva(){
		return CorAtiva;
	}
	
	public static void setCorAtiva(String cor){
		CorAtiva = cor;
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
				if(!getSelecionado() && (controlePeca != null)){
					if(controlePeca.getModeloPeca().getCor() == VisaoSlot.CorAtiva || getSlotPossivel()){
						//System.out.println("\nSLOT >>> SELECIONAMOS UMA PECA: ");
						marcarPeca();
					}
				}
				
				// MOVENDO UMA PECA
				else if (controlePeca == null && getSlotPossivel()){
					//System.out.println("\nSLOT >>> TENTANDO MOVER A PECA");
					moverPeca();
				}
				
				// DESMARCANDO UMA PECA
				else if(controlePeca != null){
					//System.out.println("\nSLOT >>> DESMARCAMOS UMA PECA: ");
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
		//System.out.println("SELECIONEI PECA");
		this.atualizarBotao(getPathPressed());
		this.NotificarObservador("MARCAR");
	}
		
	public void marcarInimigo(Boolean AtualizarImagem){
		this.setSlotInimigo(true);
		if(AtualizarImagem){
			this.atualizarBotao(this.getPathEnemy());	
		}
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
		this.Monitor = monitor;		
	}
	
	public void NotificarObservador(String Acao){
		
		if(Acao  == "MARCAR"){
			this.Monitor.setSlotAtivo(this);
			this.Monitor.update(Acao);
		}
		
		else if (Acao == "MOVER"){
			this.Monitor.setSlotAtivo(this);
			this.Monitor.update(Acao);
		}
		
		else if (Acao == "DESMARCAR"){
			this.Monitor.update(Acao);
			//this.Monitor.DesativarSlot(this);
		}
						
	}
	
	public void removerObservador(){
		this.Monitor = null;
	}
	
	public void inserirPeca(ControlePeca Peca){
		Peca.getVisaoPeca().setSize(82, 74);
		Peca.getVisaoPeca().setLocation(-1, -1);
		this.controlePeca = Peca;
		this.add(this.controlePeca.getVisaoPeca());
	}
	
	public void removerPeca(){
		if(this.controlePeca != null){
			this.remove(this.controlePeca.getVisaoPeca());
			this.atualizarBotao(this.getPathReleased());
		}
	}

	public void definirImagens(String Over, String Pressed, String Released){
		this.setPathOverlapping(Over);
		this.setPathPressed(Pressed);
		this.setPathReleased(Released);
		this.atualizarBotao(this.getPathReleased());
	}
	
	public void atualizarBotao(String Path){
		this.botaoSlot.setIcon(null);
		this.botaoSlot.setIcon(getIcone(Path));
	}
	
	public ImageIcon getIcone(String Path) {
		return new ImageIcon(getClass().getResource(Path));
	}
		
	public JButton getBotaoSlot() {
		return this.botaoSlot;
	}

	public void setBotaoSlot(VisaoBotao botaoSlot) {
		this.botaoSlot = botaoSlot;
	}

	public Boolean getSelecionado() {
		return this.SlotSelecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.SlotSelecionado = selecionado;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(this.controlePeca != null){			
			if(this.controlePeca.getModeloPeca().getCor() != VisaoSlot.CorAtiva){
				if(this.getSlotPossivel()){
					this.atualizarBotao(this.getPathOverlapping());	
				}
			}						
			else{
				//if(this.controlePeca.getModeloPeca()){ TENHO QUE VERIFICAR SE A PECA EH DO ZEUS
					
				//}
				this.atualizarBotao(this.getPathOverlapping());
			}
		}
		
		else{
			if(this.getSlotPossivel()){
				this.atualizarBotao(this.getPathOverlapping());	
			}			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(this.controlePeca != null){
			if(this.getSlotPossivel()){
				
				if(this.getSlotInimigo()){
					if(this.getSlotXeque()){
						this.atualizarBotao(this.getPathXeque());
					}
					else{
						this.atualizarBotao(this.getPathEnemy());
					}
				}
				else{
					
					if(!this.getSelecionado()){
						this.atualizarBotao(this.getPathReleased());
					}
					else{
						this.atualizarBotao(this.getPathPossibilidade());
					}
					
				}
			}
			else{
				this.atualizarBotao(this.getPathReleased());
			}
			
			if(this.getSelecionado()){
				this.atualizarBotao(this.getPathPressed());
			}
		}
		else if(this.getSlotPossivel()){
			this.atualizarBotao(this.getPathPossibilidade());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
			
	}

	public String getPathReleased() {
		return this.PathReleased;
	}

	public void setPathReleased(String pathReleased) {
		this.PathReleased = pathReleased;
	}

	public String getPathOverlapping() {
		return this.PathOverlapping;
	}

	public void setPathOverlapping(String pathOverlapping) {
		this.PathOverlapping = pathOverlapping;
	}
	
	
	public String getPathEnemy() {
		return this.PathEnemy;
	}

	public void setPathEnemy(String pathEnemy) {
		this.PathEnemy = pathEnemy;
	}
	

	public String getPathPressed() {
		return this.PathPressed;
	}

	public void setPathPressed(String pathPressed) {
		this.PathPressed = pathPressed;
	}


	public String getPathPossibilidade() {
		return this.PathPossibilidade;
	}


	public void setPathPossibilidade(String pathPossibilidade) {
		this.PathPossibilidade = pathPossibilidade;
	}


	public Boolean getSlotPossivel() {
		return this.SlotPossivel;
	}


	public void setSlotPossivel(Boolean slotPossivel) {
		this.SlotPossivel = slotPossivel;
	}


	public Coordenada getCoordenada() {
		return this.Coordenada;
	}


	public void setCoordenada(Coordenada coordenada) {
		this.Coordenada = coordenada;
	}


	public Boolean getSlotInimigo() {
		return this.SlotInimigo;
	}


	public void setSlotInimigo(Boolean slotInimigo) {
		this.SlotInimigo = slotInimigo;
	}

	public String getPathXeque() {
		return PathXeque;
	}

	public void setPathXeque(String pathXeque) {
		PathXeque = pathXeque;
	}

	public Boolean getSlotXeque() {
		return SlotXeque;
	}

	public void setSlotXeque(Boolean slotXeque) {
		SlotXeque = slotXeque;
	}
}
