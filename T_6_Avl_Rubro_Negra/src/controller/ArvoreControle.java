package controller;

import view.View;
import model.Contador;
import model.ArvoreRedBlack;
import model.ArvoreAVL;



public class ArvoreControle 
{
	int option = 0;
	private View _view = new View();
	private ArvoreAVL _arvoreAVL = new ArvoreAVL();
	private ArvoreRedBlack<Integer, Integer> _arvoreRB = new ArvoreRedBlack<Integer, Integer>();
	Contador _contador = new Contador();
	
	public void ShowMain()
	{
		do
		{
			_view.Menu();
			option = _view.ReadOption();
			switch (option) 
			{
				case 1:
					Insere();
					break;
				case 2:
					_arvoreAVL.prefixa(_arvoreAVL);
					break;
				case 3:
					_arvoreAVL.posfixa(_arvoreAVL);
					break;
				case 4:
					_arvoreAVL.infixa(_arvoreAVL);
					break;
				case 5:
					Deletar();
					break;
			}
		} 
		while (option != 6);
		
		_view.Dispose();
	}
	
	public void Insere()
	{
		int _nro = _view.ReadNumber(true);
		
		_contador = new Contador("TIPO DA ARVORE: AVL");
		_arvoreAVL = _arvoreAVL.insere(_arvoreAVL, _nro, _contador);
		_view.Information(_contador.getComp(), _contador.getRot(), _contador.getArv());
		
		_contador = new Contador("TIPO DA ARVORE: REDBLACK");
		_arvoreRB.insere(_nro, _nro, _contador);
		_view.Information(_contador.getComp(), _contador.getRot(), _contador.getArv());	
	}
	
	public void Deletar()
	{
		int _nro = _view.ReadNumber(false);
		_contador = new Contador("TIPO DA ARVORE: AVL");
		
		if (_arvoreAVL.pesquisar(_arvoreAVL, _nro, false, _contador)) 
        {
			_arvoreAVL = _arvoreAVL.deletar(_arvoreAVL, _nro);
			_arvoreAVL = _arvoreAVL.update(_arvoreAVL, _contador);
			_view.Exclui();
			_view.Information(_contador.getComp(), _contador.getRot(), _contador.getArv());
			
			_contador = new Contador("TIPO DA ARVORE: REDBLACK");
			_arvoreRB.delete(_nro, _contador);
			_view.Information(_contador.getComp(), _contador.getRot(), _contador.getArv());
			
        }
		else
		{
			_view.NaoExiste();
		}
	}
	
}
