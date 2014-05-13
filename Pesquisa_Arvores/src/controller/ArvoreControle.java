package controller;

import java.io.IOException;
import java.util.*;

import model.Arq;
import model.Arquivo;
import model.Pessoa;
import view.View;

public class ArvoreControle {
	final String nomeArquivo = "contatos.txt";
	int opcao = 0;
	private View view = new View();
	private Arvore<String, Pessoa> arvore = new Arvore<String, Pessoa>();
	Pessoa pessoa;
	private int elementos = 0;
	private int comparacoes = 0;
	private boolean achou = false;

	public void ExibeMenu() {

		LerArquivo();

		do {
			view.Menu();
			opcao = view.Read();
			switch (opcao) {
			case 1:
				InserirContato();
				break;
			case 2:
				Prefixa(arvore);
				break;
			case 3:
				Infixa(arvore);
				break;
			case 4:
				Posfixa(arvore);
				break;
			case 5:
				Busca();
				break;
			case 6:
				BuscaLargura(arvore);
				break;
			case 7:
				// BuscaProfundidade();
				break;
			}
		} while (opcao != 9);

		view.Dispose();
	}

	private void LerArquivo() {
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
					Nodo<String, Pessoa> novo = new Nodo<String, Pessoa>();
					Pessoa pessoa = new Pessoa();

					pessoa.setNome(array[0]);
					pessoa.setTelefone(array[1]);
					pessoa.setAtivo(ativo);

					novo.setChave(pessoa.getNome());
					novo.setDado(pessoa);

					arvore.inserir(novo);
					elementos++;
				}
			}
		}
	}

	public void InserirContato() {
		Pessoa p = new Pessoa();
		String inserirArquivo;

		try {
			p = view.InsereContato();
		} catch (Exception e) {
			e.printStackTrace();
		}

		inserirArquivo = p.getNome().toUpperCase() + "|"
				+ p.getTelefone().toUpperCase() + "|" + p.getAtivo();
		try {
			Arquivo.EscreverArquivo(nomeArquivo, inserirArquivo);
		} catch (IOException e) {

			e.printStackTrace();
		}

		Nodo<String, Pessoa> nodo = new Nodo<String, Pessoa>();

		nodo.setChave(p.getNome());
		nodo.setDado(p);

		arvore.inserir(nodo);
		elementos++;

		view.PosInsercao(nodo.getAltura(), elementos);
	}

	public void Prefixa(Arvore<String, Pessoa> arv) {
		if (arv != null) {
			if (arv.getRaiz() != null) {
				view.ViewPessoa(arv.getRaiz().getDado());
				Prefixa(arv.getEsquerda());
				Prefixa(arv.getDireita());
			}
		}
	}

	public void Infixa(Arvore<String, Pessoa> arv) {
		if (arv != null) {
			if (arv.getRaiz() != null) {
				Infixa(arv.getEsquerda());
				view.ViewPessoa(arv.getRaiz().getDado());
				Infixa(arv.getDireita());
			}
		}
	}

	public void Posfixa(Arvore<String, Pessoa> arv) {
		if (arv != null) {
			if (arv.getRaiz() != null) {
				Posfixa(arv.getEsquerda());
				Posfixa(arv.getDireita());
				view.ViewPessoa(arv.getRaiz().getDado());
			}
		}
	}

	public void Busca() {
		int opt = 0;
		String pesquisa = view.Pesquisa().toUpperCase();

		comparacoes = 0;
		achou = false;
		pessoa = null;
		BuscaAux(arvore, pesquisa, false);

		if (achou) {
			view.Comparacoes(comparacoes);

			do {
				view.Sub();
				opt = view.Read();
				switch (opt) {
				case 1:
					Remover(pessoa, pesquisa);
					opt = 9;
					break;
				}
			} while (opt != 9);
		} else
			view.NaoAchou();
	}

	public void BuscaAux(Arvore<String, Pessoa> arv, String pesquisa,
			boolean remover) {
		if (arv != null) {
			if (arv.getRaiz() != null) {
				comparacoes++;
				if (arv.getRaiz().getChave().equals(pesquisa)) {
					if (!remover) {
						view.ViewPessoa(arv.getRaiz().getDado());
						achou = true;
						pessoa = arv.getRaiz().getDado();
					} else {
						if (arv.getEsquerda() == null
								&& arv.getDireita() == null) {
							arv.setRaiz(null);
						} else if (arv.getEsquerda() != null
								&& arv.getDireita() == null) {
							arv.setRaiz(arv.getEsquerda().getRaiz());
							arv.setEsquerda(arv.getEsquerda().getEsquerda());
							arv.setDireita(arv.getEsquerda().getDireita());
						} else if (arv.getEsquerda() == null
								&& arv.getDireita() != null) {
							arv.setRaiz(arv.getDireita().getRaiz());
							arv.setEsquerda(arv.getDireita().getEsquerda());
							arv.setDireita(arv.getDireita().getDireita());
						} else if (arv.getEsquerda() != null
								&& arv.getDireita() != null) {
							Arvore<String, Pessoa> aux, aux2;
							Nodo<String, Pessoa> raizAux;

							if (arv.getEsquerda().getRaiz() == null
									&& arv.getDireita().getRaiz() == null) {
								arv.setRaiz(null);
							} else {
								if (arv.getDireita().getRaiz() == null)
									aux = arv.getEsquerda();
								else
									aux = arv.getDireita();
								aux2 = arv;
								while (aux.getRaiz() != null) {
									aux2 = aux;
									aux = aux.getEsquerda();
									if (aux == null)
										break;
								}
								raizAux = aux2.getRaiz();
								BuscaAux(arv, aux2.getRaiz().getChave(),
										remover);
								arv.setRaiz(raizAux);
							}
						}
					}
				} else if (arv.getRaiz().getChave().compareTo(pesquisa) <= 0)
					BuscaAux(arv.getDireita(), pesquisa, remover);
				else if (arv.getRaiz().getChave().compareTo(pesquisa) > 0)
					BuscaAux(arv.getEsquerda(), pesquisa, remover);
			}
		}
	}

	public void Remover(Pessoa p, String pesquisa) {
		String excluir;
		String anterior;

		excluir = p.getNome() + "|" + p.getTelefone() + "|" + p.getAtivo();
		anterior = excluir;

		excluir = excluir.substring(0, excluir.length() - 1);
		excluir = excluir + "0";
		try {
			Arquivo.AlterarArquivo(nomeArquivo, anterior, excluir);
		} catch (IOException e) {
			e.printStackTrace();
		}

		elementos--;
		comparacoes = 0;
		achou = false;
		pessoa = null;
		BuscaAux(arvore, pesquisa, true);
		view.Exclusao();
	}

	public void BuscaLargura(Arvore<String, Pessoa> arv) {
		Queue<Nodo<String, Pessoa>> fila = new LinkedList<Nodo<String, Pessoa>>();
		Pessoa p;
		int aux = 0;

		if (arv != null) {
			while (arv.getRaiz() != null) {
				fila.offer(arv.getRaiz());
				while (!fila.isEmpty()) {
					p = fila.poll().getDado();
					view.ViewPessoa(p);
					if (aux == 0) {
						arv.setRaiz(arv.getEsquerda().getRaiz());
						arv.setEsquerda(arv.getEsquerda().getEsquerda());
						arv.setDireita(arv.getEsquerda().getDireita());
						aux = 1;
					} else {
						arv.setRaiz(arv.getDireita().getRaiz());
						arv.setEsquerda(arv.getDireita().getEsquerda());
						arv.setDireita(arv.getDireita().getDireita());
						aux = 0;
					}
				}
			}
		}
	}
}
