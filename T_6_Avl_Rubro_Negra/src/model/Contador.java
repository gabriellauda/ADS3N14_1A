package model;

public class Contador 
{
	private int _compara, nro_rotacoes;
	private String _arvore;
	
	public Contador()
	{
		_compara = 0;
		nro_rotacoes = 0;
		_arvore = "";
	}
	
	public Contador(String pArvore)
	{
		_compara = 0;
		nro_rotacoes = 0;
		_arvore = pArvore;
	}
	
	public int getComp() 
	{
		return _compara;
	}

	public void setComp(int _compara) 
	{
		this._compara = _compara;
	}

	public int getRot()
	{
		return nro_rotacoes;
	}

	public void setRot(int nro_rotacoes) 
	{
		this.nro_rotacoes = nro_rotacoes;
	}	
	
	public String getArv() 
	{
		return _arvore;
	}

	public void setArv(String _arvore) 
	{
		this._arvore = _arvore;
	}
}
