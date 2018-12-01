package mochila;

public class Pacote {
	
	private int valor;
	private int peso;
	
	public Pacote() {
		valor = 0;
		peso = 0;
	}
	
	public Pacote(int valor, int peso) {
		this.valor = valor;
		this.peso = peso;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
}
