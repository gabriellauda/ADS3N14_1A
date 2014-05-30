package view;

import model.Jogador;

public class BatalhaViewer {

	public void print(String[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			if (i == 0)
				System.out.println("  A B C D E F G H I J");
			else
				System.out.println("");
			for (int j = 0; j < matriz[i].length; j++)
				if (j == 0)
					System.out.print(i + " " + matriz[i][j] + " ");
				else
					System.out.print(matriz[i][j] + " ");
		}
		System.out.println("");
	}

	public void printPtos(Jogador jogador) {
		System.out.println("Pontos: " + jogador.getPtos());
	}

	public void printText(String texto) {
		System.out.println(texto);
	}

}
