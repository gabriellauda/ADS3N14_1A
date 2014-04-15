package controller;

import views.Menu;

public class Controller {

	private ListaOrdenada<Integer> lista = new ListaOrdenada<Integer>();
	private Menu view = new Menu();

	public void iniciaLista() {
		for (int i = 0; i < 50; ++i) {
			Nodo<Integer> novo = new Nodo<Integer>();
			novo.setChave((int) (Math.random() * 5000));
			lista.insert(novo);
		}
	}

	public void imprimeLista() {
		Nodo<Integer> nodo = lista.getHead();
		while (nodo != null) {
			view.imprimeInteiro(nodo.getChave());
			nodo = nodo.getNext();
		}
	}
}
