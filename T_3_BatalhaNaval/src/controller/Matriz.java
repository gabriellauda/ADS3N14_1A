package controller;

import java.util.ArrayList;
import model.Navio;

public class Matriz{
	public final static int numLinhas = 10;
	public final static int numColunas = 10;	
	public String[][] batalha = new String[numLinhas][numColunas];
	public String[][] navios = new String[10][10];
	private int destroyed = 13;
	ArrayList<Navio> Navio = new ArrayList<Navio>();
	
	public int getMortos(){
		return destroyed;
	}

	public Matriz(){
		for (int i = 0; i < batalha.length; i++)
			for (int j = 0; j < batalha[i].length; j++)
				batalha[i][j] = ".";
	}
	
	public void AddNavio(){
		for (int i = 0; i < navios.length; i++)
			for (int j = 0; j < navios[i].length; j++)
				navios[i][j] = ".";
		
		Navio nav = new Navio(3, 2, 6);
		Navio.add(nav);
		
		Navio nav1 = new Navio(8, 6, 9);
		Navio.add(nav1);
		
		Navio nav2 = new Navio(5, 0, 3);
		Navio.add(nav2);
		
		Navio nav3 = new Navio(0, 5, 7);
		Navio.add(nav3);
		
		Navio nav4 = new Navio(8, 2, 4);
		Navio.add(nav4);
		
		Navio nav5 = new Navio(1, 6, 7);
		Navio.add(nav5);
		
		Navio nav6 = new Navio(4, 3, 4);
		Navio.add(nav6);
		
		Navio nav7 = new Navio(6, 7, 8);
		Navio.add(nav7);
		
		Navio nav8 = new Navio(2, 1, 1);
		Navio.add(nav8);
		
		Navio nav9 = new Navio(2, 8, 8);
		Navio.add(nav9);
		
		Navio nav10 = new Navio(9, 1, 1);
		Navio.add(nav10);
		
		Navio nav11 = new Navio(9, 9, 9);
		Navio.add(nav11);
		
		Navio nav12 = new Navio(5, 6, 6);
		Navio.add(nav12);
		
		for(Navio n : Navio) 
    	{  
			for (int i = n.getPosicaoIni(); i <= n.getPosicaoFim(); i++)
			{
				navios[n.getLinha()][i] = "O";
			}
    	}
		
	}
	
	public boolean findMorto(int linha, int col){
		for(Navio n : Navio) 
    	{  
			if (linha == n.getLinha() && (col >= n.getPosicaoIni() && col <= n.getPosicaoFim()))
			{
				for (int i = n.getPosicaoIni(); i <= n.getPosicaoFim(); i++)
				{
					if (batalha[n.getLinha()][i] != "O")
						return false;
				}
				destroyed--;
				return true;
			}
    	}
		return false;
	}
}
