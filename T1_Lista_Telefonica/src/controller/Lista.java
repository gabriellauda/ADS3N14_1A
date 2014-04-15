package controller;

import java.io.IOException;
import java.util.Scanner;

import model.Arq;
import model.Arquivo;
import views.Menu;

public class Lista {

	public static void main(String[] args) {

		// Arquivo lista
		final String nomeArquivo = "contatos.txt";

		// ListaOrdenada
		ListaOrdenada<String> lista = new ListaOrdenada<String>();
		Menu view = new Menu();

		// Var
		Scanner entrada = new Scanner(System.in);
		String nome;
		String telefone;
		String inserirArquivo;
		int opcao = 0;
		int ativoCad;

		// Leitura de arquivo, e inserção na lista;
		Arq arq = new Arq();
		try {
			arq = Arquivo.LeArquivo(nomeArquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < arq.numeroLinhas; i++) {
			String linha;
			try {
				linha = arq.texto[i].toString();
			} catch (NullPointerException n) {
				linha = "";
			}

			if (!linha.isEmpty()) {
				String array[] = new String[3];
				array = linha.split("\\|");
				int ativo = Integer.parseInt(array[2]);

				if (ativo == 1) {
					Nodo<String> novo = new Nodo<String>();
					novo.setChave(array[0] + " - " + array[1]);
					novo.setChaveArq(linha);
					lista.insert(novo);
				}
			}
		}

		do {
			System.out.println("\n");
			System.out.println("Escolha uma pção do menu:");
			System.out.println("1 - Inserir contato");
			System.out.println("2 - Percorrer contatos");
			System.out.println("3 - Busca contato");
			System.out.println("4 - Imprime contatos");
			System.out.println("5 - Sair");
			System.out.println("\n");
			try {
				opcao = entrada.nextInt();
			} catch (Exception e) {
				System.out.println("Insira um número");
				opcao = 0;
			}

			switch (opcao) {
			case 1:
				System.out.println("Insira o nome:");
				nome = entrada.next();

				System.out.println("Insira o telefone:");
				telefone = entrada.next();

				ativoCad = 1;

				inserirArquivo = nome.toUpperCase() + "|"
						+ telefone.toUpperCase() + "|" + ativoCad;

				try {
					Arquivo.EscreverArquivo(nomeArquivo, inserirArquivo);
				} catch (IOException e) {

					e.printStackTrace();
				}

				Nodo<String> novo = new Nodo<String>();
				novo.setChave(nome.toUpperCase() + " - "
						+ telefone.toUpperCase());
				novo.setChaveArq(inserirArquivo);
				lista.insert(novo);

				break;

			case 2:
				Nodo<String> avancar = lista.getHead();
				Nodo<String> voltar = lista.getHead();
				if (avancar != null)
					view.imprimeString(avancar.getChave());
				int opcao2 = 0;

				do {
					System.out.println("\n");
					System.out.println("opção 1 - Próximo Contato");
					System.out.println("opção 2 - Contato Anterior");
					System.out.println("opção 3 - Deletar Contato");
					System.out.println("opção 4 - Menu Anterior");
					System.out.println("\n");

					try {
						opcao2 = entrada.nextInt();
					} catch (Exception e) {
						System.out.println("\n");
						System.out.println("Insira um número");
						System.out.println("\n");
						opcao = 0;
					}

					switch (opcao2) {
					case 1:
						if (avancar != null) {
							if (avancar == lista.getTail()) {
								System.out.println("\n");
								System.out.println("Final da litsa");
								System.out.println("\n");
								view.imprimeString(avancar.getChave());
							} else {
								voltar = avancar;
								avancar = avancar.getNext();
								avancar.setAnterior(voltar);
								view.imprimeString(avancar.getChave());
							}
						}

						break;
					case 2:
						if (avancar != null) {
							if (avancar == lista.getHead()) {
								System.out.println("\n");
								System.out
										.println("Você já está no ínicio da lista");
								System.out.println("\n");
								view.imprimeString(avancar.getChave());
							} else {
								avancar = avancar.getAnterior();
								view.imprimeString(avancar.getChave());
							}
						}
						break;

					case 3:
						// Deleta no arquivo
						String excluir;
						excluir = avancar.getChaveArq();
						excluir = excluir.substring(0, excluir.length() - 1);
						excluir = excluir + "0";
						try {
							Arquivo.AlteraArquivo(nomeArquivo,
									avancar.getChaveArq(), excluir);
						} catch (IOException e) {
							e.printStackTrace();
						}
						// Deleta na lista
						avancar = lista.remove(avancar);
						System.out.println("\n");
						System.out.println("Contato removido!");
						System.out.println("\n");
						view.imprimeString(avancar.getChave());
						opcao2 = 5;
						break;
					}

				} while (opcao2 != 4);

				break;

			case 3:
				System.out.println("\n");
				System.out.println("Informe o nome para pesquisa:");
				System.out.println("\n");
				String busca = entrada.next();
				Nodo<String> buscaNodo = new Nodo<String>();
				buscaNodo.setChave(busca.toUpperCase());
				Nodo<String> resultado = new Nodo<String>();
				resultado = lista.Busca(buscaNodo);
				view.imprimeString(resultado.getChave());
				break;

			case 4:
				Nodo<String> nodo = lista.getHead();
				while (nodo != null) {
					view.imprimeString(nodo.getChave());
					nodo = nodo.getNext();
				}

				break;
			}

		} while (opcao != 5);
	}
}
