package model;

public class Jogador {
	int pontos;

	public int getPtos() {
		return pontos;
	}

	public void setPtos(int pontos) {
		this.pontos = pontos;
	}

	public Jogador() {
		pontos = 15;
	}

	public boolean Perdeu() {
		if (pontos <= 0)
			return true;
		return false;
	}

}
