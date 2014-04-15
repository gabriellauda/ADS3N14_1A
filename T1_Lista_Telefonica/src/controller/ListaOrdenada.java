package controller;

public class ListaOrdenada<T extends Comparable<T>> extends ListaEncadeada<T> {

	public Nodo<T> procuraNodo(Nodo<T> needle) {
		Nodo<T> atual = getHead();
		Nodo<T> anterior = null;
		T chaveNeedle = needle.getChave();

		while (atual != null) {
			T chaveAtual = atual.getChave();
			int cmp = chaveNeedle.compareTo(chaveAtual);
			if (cmp == 0)
				return atual;
			if (cmp < 0)
				return anterior;
			anterior = atual;
			atual = atual.getNext();
		}
		return anterior;
	}

	@Override
	public void append(Nodo<T> novo) {
		insert(novo);
	}

	@Override
	public void insert(Nodo<T> novo, Nodo<T> anterior) {
		insert(novo);
	}

	@Override
	public void insert(Nodo<T> novo) {
		Nodo<T> anterior = procuraNodo(novo);

		if (anterior == null) {
			novo.setNext(head);
			head = novo;
			if (tail == null)
				tail = novo;
		} else {
			novo.setAnterior(anterior);
			novo.setNext(anterior.getNext());
			anterior.setNext(novo);
			anterior.setAnterior(novo.getAnterior());
			if (tail == anterior)
				tail = novo;
		}

	}

	public Nodo<T> remove(Nodo<T> nodo) {
		Nodo<T> anterior = nodo.getAnterior();

		if (nodo == head && nodo == tail) {
			head = null;
			tail = null;
			return head;
		} else if (nodo == head) {
			head = nodo.getNext();
			head.setAnterior(null);
			return head;
		} else if (nodo == tail) {
			tail = nodo.getAnterior();
			tail.setNext(null);
			return tail;
		} else {
			anterior.setNext(nodo.getNext());
			anterior.setAnterior(anterior.getAnterior());
			return anterior;
		}

	}

	public Nodo<T> Busca(Nodo<T> busca) {
		Nodo<T> buscaNodo = BuscaNodo(busca);
		return buscaNodo;

	}

	public Nodo<T> BuscaNodo(Nodo<T> needle) {
		Nodo<T> atual = getHead();
		Nodo<T> anterior = null;
		T chaveNeedle = needle.getChave();

		while (atual != null) {
			T chaveAtual = atual.getChave();
			int cmp = chaveNeedle.compareTo(chaveAtual);
			if (cmp == 0 || cmp < 0)
				return atual;
			anterior = atual;
			atual = atual.getNext();
		}
		return anterior;
	}

}
