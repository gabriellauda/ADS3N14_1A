package controller;

public class Lista_Encadeada<T> {

	protected Nodo<T> head;
	protected Nodo<T> tail;

	public Lista_Encadeada() {
		head = null;
		tail = null;
	}

	public void insert(Nodo<T> novoNodo) {
		novoNodo.setNext(head);
		head = novoNodo;
		if (tail == null)
			tail = novoNodo;
	}

	public void insert(Nodo<T> novoNodo, Nodo<T> _ant) {
		if (_ant == null) {
			novoNodo.setNext(head);
			head = novoNodo;
			if (tail == null)
				tail = head;
		} else {
			novoNodo.setNext(_ant.getNext());
			_ant.setNext(novoNodo);
			if (_ant == tail)
				tail = novoNodo;
		}
	}

	public void append(Nodo<T> novoNodo) {
		if (tail != null)
			tail.setNext(novoNodo);
		else
			head = novoNodo;
		tail = novoNodo;
	}

	public Nodo<T> getTail() {
		return tail;
	}

	public Nodo<T> getHead() {
		return head;
	}

}
