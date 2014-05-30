package controller;

public class Nodo<T> {

	private Nodo<T> next;
	private Nodo<T> _ant;
	private T chave;
	private T chaveArq;

	public Nodo() {
		chave = null;
		chaveArq = null;
		next = null;
		_ant = null;
	}

	public T getChave() {
		return chave;
	}

	public T getChaveArq() {
		return chaveArq;
	}

	public void setChave(T chave) {
		this.chave = chave;
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

	public Nodo<T> getAnt() {
		return _ant;
	}

	public void setAnt(Nodo<T> _ant) {
		this._ant = _ant;
	}

}
