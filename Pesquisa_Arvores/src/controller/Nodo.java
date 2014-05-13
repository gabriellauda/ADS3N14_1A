package controller;

public class Nodo<T, C> {
	private T chave;
	private C dado;
	private int altura;

	public Nodo() {
		chave = null;
		dado = null;
		altura = 0;
	}

	public T getChave() {
		return chave;
	}

	public void setChave(T chave) {
		this.chave = chave;
	}

	public C getDado() {
		return dado;
	}

	public void setDado(C dado) {
		this.dado = dado;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

}
