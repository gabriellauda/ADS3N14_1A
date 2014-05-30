package model;

public class ListaBusca {
	private String nome;
	private int posicao;
	private int _comp;

	public ListaBusca() {
		nome = "";
		posicao = -1;
		_comp = 0;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCmp() {
		return _comp;
	}

	public void setCmp(int _comp) {
		this._comp = _comp;
	}

}
