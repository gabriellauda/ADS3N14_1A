package view;

import java.util.Scanner;

import model.Pessoa;

public class View {
	Scanner entrada = new Scanner(System.in);

	public void Dispose() {
		entrada.close();
	}

	public void Menu() {
		System.out.println("<menu>");
		System.out.println("<1> inserir");
		System.out.println("<2> prefixa");
		System.out.println("<3> infixa");
		System.out.println("<4> posfixa");
		System.out.println("<5> procura");
		System.out.println("<6> procura largura");
		System.out.println("<7> procura profundidade");
		System.out.println("<9> Sai");
	}

	public void Sub() {
		System.out.println("<sub>");
		System.out.println("<1> remove");
		System.out.println("<9> Sai");
	}

	public int Read() {
		int escolha = 0;

		try {
			escolha = entrada.nextInt();
		} catch (Exception e) {
			System.out.println("insira um número intteiro");
			escolha = 0;
		}
		entrada.nextLine();

		return escolha;
	}

	public Pessoa InsereContato() throws Exception {
		Pessoa p = new Pessoa();
		String nome = "";
		String fone = "";
		int ativo = 1;

		System.out.println("insira o nome:");
		nome = entrada.nextLine();

		System.out.println("insira o telefone:");
		fone = entrada.nextLine();

		p.setNome(nome.toUpperCase());
		p.setTelefone(fone.toUpperCase());
		p.setAtivo(ativo);

		return p;
	}

	public void ViewPessoa(Pessoa p) {
		System.out.println("Nome: " + p.getNome());
		System.out.println("Telefone: " + p.getTelefone());
		System.out.println("");
	}

	public String Pesquisa() {
		String pesquisa;

		System.out.println("Pesquisa: ");
		pesquisa = entrada.nextLine();

		return pesquisa;
	}

	public void Comparacoes(int comparacoes) {
		System.out.println("Comparações: " + comparacoes);
		System.out.println("");
	}

	public void PosInsercao(int pAltura, int pNumeroItens) {
		System.out.println("Altura: " + pAltura);
		System.out.println("Itens: " + pNumeroItens);
		System.out.println("");
	}

	public void NaoAchou() {
		System.out.println("Nenhum contato encontrado!");
		System.out.println("");
	}

	public void Exclusao() {
		System.out.println("Contato excluído com sucesso!");
		System.out.println("");
	}

}
