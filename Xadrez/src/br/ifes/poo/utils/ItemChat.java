package br.ifes.poo.utils;

public class ItemChat {
	
	private String texto;
	private String data;
	private String hora;
	private String usuario;
	
	public ItemChat(String user, String date, String time, String txt){
		this.usuario = user;
		this.data = date;
		this.hora = time;
		this.texto = txt;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getData() {
		return data;
	}
	public void setData(String dataHora) {
		this.data = dataHora;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}

}
