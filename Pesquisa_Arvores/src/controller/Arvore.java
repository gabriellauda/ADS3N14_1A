package controller;

public class Arvore<T extends Comparable<T>, C> {
	Nodo<T, C> raiz;
	Arvore<T, C> esquerda;
	Arvore<T, C> direita;

	public Arvore() {
		this.raiz = null;
		this.esquerda = null;
		this.direita = null;
	}

	public Nodo<T, C> getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo<T, C> raiz) {
		this.raiz = raiz;
	}

	public Arvore<T, C> getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(Arvore<T, C> esquerda) {
		this.esquerda = esquerda;
	}

	public Arvore<T, C> getDireita() {
		return direita;
	}

	public void setDireita(Arvore<T, C> direita) {
		this.direita = direita;
	}

	public void inserir(Nodo<T, C> nodo) {
		if (this.raiz == null) {
			nodo.setAltura(nodo.getAltura());
			this.raiz = nodo;
		} else {
			int cmp = nodo.getChave().compareTo(this.raiz.getChave());

			if (cmp > 0) {
				if (this.direita == null)
					this.direita = new Arvore<T, C>();

				nodo.setAltura(nodo.getAltura() + 1);
				this.direita.inserir(nodo);
			} else if (cmp <= 0) {
				if (this.esquerda == null)
					this.esquerda = new Arvore<T, C>();

				nodo.setAltura(nodo.getAltura() + 1);
				this.esquerda.inserir(nodo);
			}
		}
	}
}
