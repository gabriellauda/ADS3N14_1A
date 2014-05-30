package model;

public class Pessoa {

	private String nome;
	private String telefone;
	private int active;

	public Pessoa() {
		nome = "";
		telefone = "";
		active = 0;
	}

	public Pessoa(String _Nome, String _Telefone, int _Ativo) {
		nome = _Nome;
		telefone = _Telefone;
		active = _Ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getAtivo() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

}
