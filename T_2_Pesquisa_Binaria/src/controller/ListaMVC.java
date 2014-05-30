package controller;

import java.io.IOException;
import java.util.Scanner;

import view.MenuView;
import controller.Busca_Binaria;
import controller.Lista_Ordenada;
import controller.Nodo;
import contatos.*;

public class ListaMVC {

	public static void main(String[] args) {

		// arquivo de contatos
		final String nomeArquivo = "contatos.txt";

		// nova lista
		Lista_Ordenada<String> list = new Lista_Ordenada<String>();
		MenuView mview = new MenuView();

		// Var
		Scanner input = new Scanner(System.in);
		String nome;
		String telefone;
		String insereArquivo;
		int option = 0;
		int ativoCad;

		// pega o arquivo pra botar na lista dos contatos;
		Arq arq = new Arq();
		try {
			arq = Arquivo.ReadFile(nomeArquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < arq.numLinha; i++) {
			String linha;
			try {
				linha = arq.text[i].toString();
			} catch (NullPointerException n) {
				linha = "";
			}

			if (!linha.isEmpty()) {
				String array[] = new String[3];
				array = linha.split("\\|");
				int active = Integer.parseInt(array[2]);

				if (active == 1) {
					Nodo<String> novoNodo = new Nodo<String>();
					novoNodo.setChave(array[0] + " - " + array[1]);
					novoNodo.setChaveArq(linha);
					list.insert(novoNodo);
				}
			}
		}

		do {
			System.out.println("INFORMNE SUA OPÇÃO:");
			System.out.println("1 - ADD CONTATO");
			System.out.println("2 - PERCORRER CONTATOS");
			System.out.println("3 - MOSTRAR CONTATOS");
			System.out.println("4 - PESQUISAR CONTATOS");
			System.out.println("5 - PESQUISA BINÁRIA DE CONTATOS");
			System.out.println("6 - SAIR");
			try {
				option = input.nextInt();
			} catch (Exception e) {
				System.out.println("INSIRA UM NÚMERO");
				option = 0;
			}

			switch (option) {
			case 1:
				System.out.println("Informe o nome:");
				nome = input.next();

				System.out.println("Informe o telefone:");
				telefone = input.next();

				ativoCad = 1;

				insereArquivo = nome.toUpperCase() + "|"
						+ telefone.toUpperCase() + "|" + ativoCad;

				try {
					Arquivo.EscreverArquivo(nomeArquivo, insereArquivo);
				} catch (IOException e) {

					e.printStackTrace();
				}

				Nodo<String> novoNodo = new Nodo<String>();
				novoNodo.setChave(nome.toUpperCase() + " - "
						+ telefone.toUpperCase());
				novoNodo.setChaveArq(insereArquivo);
				list.insert(novoNodo);

				break;

			case 2:
				Nodo<String> next = list.getHead();
				Nodo<String> back = list.getHead();
				if (next != null)
					mview.printStr(next.getChave());
				int option_ = 0;

				do {
					System.out.println("1 - PRÓXZIMO");
					System.out.println("2 - VOLTA");
					System.out.println("3 - DELETA");
					System.out.println("6 - SAIR");

					try {
						option_ = input.nextInt();
					} catch (Exception e) {
						System.out.println("INSIRA UM NPUMERO");
						option = 0;
					}

					switch (option_) {
					case 1:
						if (next != null) {
							if (next == list.getTail()) {
								System.out.println("FIM DA LISTA DE CONTATOS");
								mview.printStr(next.getChave());
							} else {
								back = next;
								next = next.getNext();
								next.setAnt(back);
								mview.printStr(next.getChave());
							}
						}

						break;
					case 2:
						if (next != null) {
							if (next == list.getHead()) {
								System.out
										.println("VC ESTÁ NO COMEÇO DA LISTA");
								mview.printStr(next.getChave());
							} else {
								next = next.getAnt();
								mview.printStr(next.getChave());
							}
						}
						break;

					case 3:
						// NÃO TA FUNCIONANDO . VER COM JEFFAMN!!
						// deleta dos arquivo de contatos
//						String deletar;
//						deletar = next.getChaveArq();
//						deletar = deletar.substring(0, deletar.length() - 1);
//						deletar = deletar + "0";
//						try {
//							Arquivo.EditFile(nomeArquivo, next.getChaveArq(),
//									deletar);
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//						// Exclui da lista
//						next = list.remove(next);
//						System.out.println("CONTATO EXCLUÍDO COM SUCESSO!");
//						mview.printStr(next.getChave());
//						option_ = 6;
						break;
					}

				} while (option_ != 6);

				break;

			case 3:
				Nodo<String> nodo = list.getHead();
				while (nodo != null) {
					mview.printStr(nodo.getChave());
					nodo = nodo.getNext();
				}

				break;

			case 4:
				System.out.println("INSIRA UM NOME PARA BUSCA:");
				String search = input.next();
				Nodo<String> buscaNodo = new Nodo<String>();
				buscaNodo.setChave(search.toUpperCase());
				Nodo<String> result = new Nodo<String>();
				result = list.Busca(buscaNodo);
				mview.printStr(result.getChave());
				break;

			case 5:
				System.out.println("INSIRA UM NOME PARA PESQUISA BINÁRIA:");
				String buscaB = input.next();
				Nodo<String> nodoBusca = null;

				Busca_Binaria bb = new Busca_Binaria();

				bb.Pesquisa(nodoBusca, list, buscaB.toUpperCase());

				if (bb.getFindPosition() == -1)
					System.out
							.println("NOME AUSENTE NA LISTA!\n POSIÇÃO APROXIMADA: "
									+ bb.getPosicaoMaisProxima()
									+ "\nNome: "
									+ bb.getNomeMaisProximo());
				else
					System.out.println("POSIÇÃO : " + bb.getFindPosition()
							+ "\nCOMPARAÇÕES: " + bb.getCompara());
				;

				break;
			}

		} while (option != 6);
		input.close();
	}
}
