package controller;

import java.util.ArrayList;

import model.ListaBusca;

public class Busca_Binaria {
	private int numCompara;
	private int pos_e;
	private int pos_prox;
	private String nome_prox;

	public int getCompara() {
		return numCompara;
	}

	public int getPosicaoMaisProxima() {
		return pos_prox;
	}

	public String getNomeMaisProximo() {
		return nome_prox;
	}

	public int getFindPosition() {
		return pos_e;
	}

	public Busca_Binaria() {
		numCompara = 0;
		pos_e = -1;
		pos_prox = -1;
		nome_prox = "";
	}

	public void Pesquisa(Nodo<String> nodoBusca, Lista_Ordenada<String> list,
			String search) {

		nodoBusca = list.getHead();

		int positions = 0;
		while (nodoBusca != null) {
			positions++;
			nodoBusca = nodoBusca.getNext();
		}

		String[] array = new String[positions];

		nodoBusca = list.getHead();

		for (int i = 0; i < positions; i++) {
			String arraySplit[] = new String[3];
			arraySplit = nodoBusca.getChaveArq().split("\\|");

			array[i] = arraySplit[0];
			nodoBusca = nodoBusca.getNext();
		}

		int result = -1;
		int ini = 0;
		int fim = array.length - 1;
		int meio;
		ArrayList<ListaBusca> listaBusca = new ArrayList<ListaBusca>();

		while (ini <= fim) {

			ListaBusca lb = new ListaBusca();

			numCompara++;
			meio = (ini + fim) / 2;

			lb.setNome(array[meio]);
			lb.setPosicao(meio);
			lb.setCmp(array[meio].compareTo(search));
			listaBusca.add(lb);

			if (array[meio].compareTo(search) < 0)
				ini = meio + 1;
			else if (array[meio].compareTo(search) > 0)
				fim = meio - 1;
			else {
				result = meio;
				break;
			}
		}

		pos_e = result;

		int pos = Integer.MAX_VALUE;

		if (result == -1) {

			for (ListaBusca l : listaBusca) {
				if (l.getCmp() < pos && l.getCmp() > 0) {
					pos = l.getCmp();
					pos_prox = l.getPosicao();
					nome_prox = l.getNome();
				}
			}

		}

	}
}
