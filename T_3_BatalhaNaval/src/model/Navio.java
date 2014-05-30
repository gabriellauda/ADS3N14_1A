package model;

public class Navio {

	int posicaoInicial;
	int posicaoFinal;
	int linha;

	public int getPosicaoIni() {
		return posicaoInicial;
	}

	public int getPosicaoFim() {
		return posicaoFinal;
	}

	public int getLinha() {
		return linha;
	}

	public Navio(int Linha, int posIni, int posFim) {
		linha = Linha;
		posicaoInicial = posIni;
		posicaoFinal = posFim;
	}

}
