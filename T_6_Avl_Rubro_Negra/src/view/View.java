package view;

import java.util.Scanner;

public class View 
{
	Scanner entrada = new Scanner(System.in);
	
	public void Dispose()
	{
		entrada.close();
	}
	
	public void Menu()
	{
		System.out.println("<MENU>");
		System.out.println("<1> INSERIR");
		System.out.println("<2> PREFIXA");
		System.out.println("<3> POSFIXA");
		System.out.println("<4> INFIXA");
		System.out.println("<5> EXCLUIR");
		System.out.println("<6> SAIR");
	}
		
	public int ReadOption()
	{	
		int option = 0;
		
		try
		{
			option = entrada.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("INSIRA UM N�MERO");
			option = 0;
		}	
		entrada.nextLine();
		
		return option;
	}
	
	public int ReadNumber(boolean incluir)
	{	
		int numero = 0;
		
		if (incluir)
			System.out.println("INSIRA UM N�MERO PARA INSERIR NA ARVORE: ");
		else
			System.out.println("INSIRA UM N�MERO PARA EXCLUIR DA ARVORE: ");
		try
		{
			numero = entrada.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("INSIRA UM N�MERO");
			numero = 0;
		}	
		entrada.nextLine();
		
		return numero;
	}
		
	public void Information(int _compara, int nro_rotacoes, String _arvore)
	{
		System.out.println("TIPO DE ARVORE: " + _arvore);
		System.out.println("NRO DE COMPARA�O~ES: " + _compara);
		System.out.println("ROTA��ES REALIZADAS: " + nro_rotacoes);
		System.out.println("");
	}
	
	public void NaoExiste()
	{
		System.out.println("N�MERO INSERIDO N�O ENCONTRADO!");
		System.out.println("");
	}
	
	public void Exclui()
	{
		System.out.println("N�MERO EXCLU�DO CORRETAMENTE!");
		System.out.println("");
	}
	
}
