package controller;

public class Nodo<T> {

	private T chave;
	private T chaveArq;
	private Nodo<T> next;
	private Nodo<T> anterior;

	public Nodo() {
		chave = null;
		chaveArq = null;
		next = null;
		anterior = null;
	}

	public T getChave() {
		return chave;
	}

	public void setChave(T chave) {
		this.chave = chave;
	}

	public T getChaveArq() {
		return chaveArq;
	}

	public void setChaveArq(T chaveArq) {
		this.chaveArq = chaveArq;
	}

	public Nodo<T> getNext() {
		return next;
	}

	public void setNext(Nodo<T> next) {
		this.next = next;
	}

	public Nodo<T> getAnterior() {
		return anterior;
	}

	public void setAnterior(Nodo<T> anterior) {
		this.anterior = anterior;
	}

}
