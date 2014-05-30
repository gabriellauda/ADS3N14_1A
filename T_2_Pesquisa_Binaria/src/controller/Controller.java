package controller;

import view.MenuView;
import controller.Lista_Ordenada;
import controller.Nodo;

public class Controller {

	private Lista_Ordenada<Integer> list = new Lista_Ordenada<Integer>();
	private MenuView mview = new MenuView();

	public void startList() {
		for (int i = 0; i < 50; ++i) {
			Nodo<Integer> novoNodo = new Nodo<Integer>();
			novoNodo.setChave((int) (Math.random() * 10000));
			list.insert(novoNodo);
		}
	}

	public void printList() {
		Nodo<Integer> nodo = list.getHead();
		while (nodo != null) {
			mview.printInt(nodo.getChave());
			nodo = nodo.getNext();
		}
	}
}
