package controller;

public class Lista_Ordenada<T extends Comparable<T>> extends Lista_Encadeada<T> {

	public Nodo<T> searchNodo(Nodo<T> needle) {
		Nodo<T> _atual = getHead();
		Nodo<T> _ant = null;
		T chaveNeedle = needle.getChave();

		while (_atual != null) {
			T chaveAtual = _atual.getChave();
			int _comp = chaveNeedle.compareTo(chaveAtual);
			if (_comp == 0)
				return _atual;
			if (_comp < 0)
				return _ant;
			_ant = _atual;
			_atual = _atual.getNext();
		}
		return _ant;
	}

	@Override
	public void append(Nodo<T> novoNodo) {
		insert(novoNodo);
	}

	@Override
	public void insert(Nodo<T> novoNodo, Nodo<T> _ant) {
		insert(novoNodo);
	}

	@Override
	public void insert(Nodo<T> novoNodo) {
		Nodo<T> _ant = searchNodo(novoNodo);

		if (_ant == null) {
			novoNodo.setNext(head);
			head = novoNodo;
			if (tail == null)
				tail = novoNodo;
		} else {
			novoNodo.setAnt(_ant);
			novoNodo.setNext(_ant.getNext());
			_ant.setNext(novoNodo);
			_ant.setAnt(novoNodo.getAnt());
			if (tail == _ant)
				tail = novoNodo;
		}

	}

	public Nodo<T> remove(Nodo<T> nodo) {
		Nodo<T> _ant = nodo.getAnt();

		if (nodo == head && nodo == tail) {
			head = null;
			tail = null;
			return head;
		} else if (nodo == head) {
			head = nodo.getNext();
			head.setAnt(null);
			return head;
		} else if (nodo == tail) {
			tail = nodo.getAnt();
			tail.setNext(null);
			return tail;
		} else {
			_ant.setNext(nodo.getNext());
			_ant.setAnt(_ant.getAnt());
			return _ant;
		}
	}

	public Nodo<T> Busca(Nodo<T> search) {
		Nodo<T> buscaNodo = BuscaNodo(search);
		return buscaNodo;

	}

	public Nodo<T> BuscaNodo(Nodo<T> needle) {
		Nodo<T> _atual = getHead();
		Nodo<T> _ant = null;
		T chaveNeedle = needle.getChave();

		while (_atual != null) {
			T chaveAtual = _atual.getChave();
			int _comp = chaveNeedle.compareTo(chaveAtual);
			if (_comp == 0 || _comp < 0)
				return _atual;
			_ant = _atual;
			_atual = _atual.getNext();
		}
		return _ant;
	}

}
