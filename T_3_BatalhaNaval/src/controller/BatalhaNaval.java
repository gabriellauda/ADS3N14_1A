package controller;

import java.util.Scanner;

import model.Jogador;
import model.Letra;
import view.BatalhaViewer;
import controller.Matriz;

public class BatalhaNaval {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Matriz matriz = new Matriz();
		BatalhaViewer bv = new BatalhaViewer();
		bv.print(matriz.batalha);

		matriz.AddNavio();
		Jogador jogador = new Jogador();

		while (jogador.getPtos() > 0 || matriz.getMortos() > 0) {
			if (jogador.getPtos() == 0 || matriz.getMortos() == 0)
				break;
			bv.printText("");
			bv.printText("INSIRA UMA COLUNA PARA INICIAR O JOGO:");
			String col = input.next();

			bv.printText("INSIRA UMA LINHA:");
			int linha = input.nextInt();

			if (linha <= 9 && linha >= 0 && Letra.findNumber(col) <= 9
					&& Letra.findNumber(col) >= 0) {
				if (matriz.batalha[linha][Letra.findNumber(col)] == ".") {
					jogador.setPtos(jogador.getPtos() - 1);

					if (matriz.navios[linha][Letra.findNumber(col)] == "O") {
						bv.printText("ALVO ATINGIDO!");
						matriz.batalha[linha][Letra.findNumber(col)] = "O";

						if (matriz.findMorto(linha, Letra.findNumber(col))) {
							bv.printText("NAVBIO DESTRUÍDO!");
							jogador.setPtos(jogador.getPtos() + 5);
						} else {
							jogador.setPtos(jogador.getPtos() + 3);
						}
					} else {
						bv.printText("VC ERROU O ALVO");
						matriz.batalha[linha][Letra.findNumber(col)] = "-";
					}
				} else {
					bv.printText("ESSA POSIÇÃO JA FOI ESCOLHIDA \n INSIRA OUTRO PONTO!");
				}
			} else {
				bv.printText("POSIÇÃO INVÁLÇIDA!");
			}
			bv.print(matriz.batalha);
			bv.printPtos(jogador);
		}
		if (jogador.getPtos() == 0) {
			bv.printText("VODÊ PERDEU!");
		} else {
			bv.printText("VC GANHOU O JOGO \n PARABENS!");
		}

		input.close();
	}

}
