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
			System.out.println("INSIRA UM NÚMERO");
			option = 0;
		}	
		entrada.nextLine();
		
		return option;
	}
	
	public int ReadNumber(boolean incluir)
	{	
		int numero = 0;
		
		if (incluir)
			System.out.println("INSIRA UM NÚMERO PARA INSERIR NA ARVORE: ");
		else
			System.out.println("INSIRA UM NÚMERO PARA EXCLUIR DA ARVORE: ");
		try
		{
			numero = entrada.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("INSIRA UM NÚMERO");
			numero = 0;
		}	
		entrada.nextLine();
		
		return numero;
	}
		
	public void Information(int _compara, int nro_rotacoes, String _arvore)
	{
		System.out.println("TIPO DE ARVORE: " + _arvore);
		System.out.println("NRO DE COMPARAÇO~ES: " + _compara);
		System.out.println("ROTAÇÕES REALIZADAS: " + nro_rotacoes);
		System.out.println("");
	}
	
	public void NaoExiste()
	{
		System.out.println("NÚMERO INSERIDO NÃO ENCONTRADO!");
		System.out.println("");
	}
	
	public void Exclui()
	{
		System.out.println("NÚMERO EXCLUÍDO CORRETAMENTE!");
		System.out.println("");
	}
	
}
