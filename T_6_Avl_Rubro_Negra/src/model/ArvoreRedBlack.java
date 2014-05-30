package model;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import model.Contador;

public class ArvoreRedBlack<K extends Comparable<K>, T> 
{
	private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Nodo raiz;     

    private class Nodo 
    {
        private K chave;           
        private T valor;         
        private Nodo esq, dir;  
        private boolean cor;     
        private int N;             

        public Nodo(K chave, T valor, boolean cor, int N) 
        {
            this.chave = chave;
            this.valor = valor;
            this.cor = cor;
            this.N = N;
        }
    }

    private boolean isRed(Nodo x) 
    {
        if (x == null) 
        	return false;
        return (x.cor == RED);
    }

    private int tamanho(Nodo x) 
    {
        if (x == null) 
        	return 0;
        return x.N;
    } 


    public int tamanho() 
    { 
        return tamanho(raiz); 
    }

    public boolean vazio() 
    {
        return raiz == null;
    }

    public T get(K chave) 
    { 
        return get(raiz, chave); 
    }

    private T get(Nodo x, K chave) 
    {
        while (x != null) 
        {
            int cmp = chave.compareTo(x.chave);
            
            if (cmp < 0) 
            	x = x.esq;
            else if (cmp > 0) 
            	x = x.dir;
            else
            	return x.valor;
        }
        return null;
    }

    public boolean contem(K chave) 
    {
        return (get(chave) != null);
    }

    private boolean contem(Nodo x, K chave) 
    {
        return (get(x, chave) != null);
    }

    public void insere(K chave, T valor, Contador _contador) 
    {
    	raiz = insere(raiz, chave, valor, _contador);
    	raiz.cor = BLACK;
        assert checar();
    }

    private Nodo insere(Nodo h, K chave, T valor, Contador _contador) 
    { 
        if (h == null) 
        	return new Nodo(chave, valor, RED, 1);

        int cmp = chave.compareTo(h.chave);
        _contador.setComp(_contador.getComp() + 1);
        
        if (cmp < 0) 
        	h.esq  = insere(h.esq,  chave, valor, _contador); 
        else if (cmp > 0) 
        	h.dir = insere(h.dir, chave, valor, _contador); 
        else
        	h.valor   = valor;

        if (isRed(h.dir) && !isRed(h.esq))      
        {
        	_contador.setRot(_contador.getRot() + 1);
        	h = rotacao_Esq(h);
        }
        if (isRed(h.esq)  &&  isRed(h.esq.esq)) 
        {
        	_contador.setRot(_contador.getRot() + 1);
        	h = rotacao_Dir(h);
        }
        if (isRed(h.esq)  &&  isRed(h.dir))     
        	trocarCor(h);
        h.N = tamanho(h.esq) + tamanho(h.dir) + 1;

        return h;
    }

    public void deleteMin() 
    {
        if (vazio()) 
        	throw new NoSuchElementException("Underflow");

        if (!isRed(raiz.esq) && !isRed(raiz.dir))
        	raiz.cor = RED;

        raiz = deleteMin(raiz);
        if (!vazio()) 
        	raiz.cor = BLACK;
        assert checar();
    }

    private Nodo deleteMin(Nodo h) 
    { 
        if (h.esq == null)
            return null;

        if (!isRed(h.esq) && !isRed(h.esq.esq))
            h = moverEsquerda(h);

        h.esq = deleteMin(h.esq);
        return balancear(h);
    }


    public void deleteMax() 
    {
        if (vazio()) 
        	throw new NoSuchElementException("Underflow");

        if (!isRed(raiz.esq) && !isRed(raiz.dir))
        	raiz.cor = RED;

        raiz = deleteMax(raiz);
        if (!vazio()) 
        	raiz.cor = BLACK;
        assert checar();
    }

    private Nodo deleteMax(Nodo h) 
    { 
        if (isRed(h.esq))
            h = rotacao_Dir(h);

        if (h.dir == null)
            return null;

        if (!isRed(h.dir) && !isRed(h.dir.esq))
            h = moverDireita(h);

        h.dir = deleteMax(h.dir);

        return balancear(h);
    }

    public void delete(K chave, Contador _contador) 
    { 
        if (!contem(chave)) 
        {
            System.err.println("Chave não existe: " + chave);
            return;
        }

        if (!isRed(raiz.esq) && !isRed(raiz.dir))
        	raiz.cor = RED;

        raiz = delete(raiz, chave, _contador);
        if (!vazio()) 
        	raiz.cor = BLACK;
        assert checar();
    }

    private Nodo delete(Nodo h, K chave, Contador _contador) 
    { 
        assert contem(h, chave);

        if (chave.compareTo(h.chave) < 0)  
        {
        	_contador.setComp(_contador.getComp() + 1);
            if (!isRed(h.esq) && !isRed(h.esq.esq))
                h = moverEsquerda(h);
            h.esq = delete(h.esq, chave, _contador);
        }
        else 
        {
        	_contador.setComp(_contador.getComp() + 1);
            if (isRed(h.esq))
            {
            	_contador.setRot(_contador.getRot() + 1);
                h = rotacao_Dir(h);
            }
            if (chave.compareTo(h.chave) == 0 && (h.dir == null))
                return null;
            if (!isRed(h.dir) && !isRed(h.dir.esq))
                h = moverDireita(h);
            if (chave.compareTo(h.chave) == 0) 
            {
            	Nodo x = min(h.dir);
                h.chave = x.chave;
                h.valor = x.valor;
                h.dir = deleteMin(h.dir);
            }
            else 
            	h.dir = delete(h.dir, chave, _contador);
        }
        return balancear(h);
    }

    private Nodo rotacao_Dir(Nodo h) 
    {
        assert (h != null) && isRed(h.esq);
        Nodo x = h.esq;
        h.esq = x.dir;
        x.dir = h;
        x.cor = x.dir.cor;
        x.dir.cor = RED;
        x.N = h.N;
        h.N = tamanho(h.esq) + tamanho(h.dir) + 1;
        return x;
    }

    private Nodo rotacao_Esq(Nodo h) 
    {
        assert (h != null) && isRed(h.dir);
        Nodo x = h.dir;
        h.dir = x.esq;
        x.esq = h;
        x.cor = x.esq.cor;
        x.esq.cor = RED;
        x.N = h.N;
        h.N = tamanho(h.esq) + tamanho(h.dir) + 1;
        return x;
    }

    private void trocarCor(Nodo h) 
    {
        assert (h != null) && (h.esq != null) && (h.dir != null);
        assert (!isRed(h) &&  isRed(h.esq) &&  isRed(h.dir))
            || ( isRed(h) && !isRed(h.esq) && !isRed(h.dir));
        h.cor = !h.cor;
        h.esq.cor = !h.esq.cor;
        h.dir.cor = !h.dir.cor;
    }

    private Nodo moverEsquerda(Nodo h) 
    {
        assert (h != null);
        assert isRed(h) && !isRed(h.esq) && !isRed(h.esq.esq);

        trocarCor(h);
        if (isRed(h.dir.esq)) 
        { 
            h.dir = rotacao_Dir(h.dir);
            h = rotacao_Esq(h);
        }
        return h;
    }

    private Nodo moverDireita(Nodo h) 
    {
        assert (h != null);
        assert isRed(h) && !isRed(h.dir) && !isRed(h.dir.esq);
        trocarCor(h);
        if (isRed(h.esq.esq)) 
        { 
            h = rotacao_Dir(h);
        }
        return h;
    }

    private Nodo balancear(Nodo h) 
    {
        assert (h != null);

        if (isRed(h.dir))                      
        	h = rotacao_Esq(h);
        if (isRed(h.esq) && isRed(h.esq.esq)) 
        	h = rotacao_Dir(h);
        if (isRed(h.esq) && isRed(h.dir))     
        	trocarCor(h);

        h.N = tamanho(h.esq) + tamanho(h.dir) + 1;
        return h;
    }

    public int altura() 
    { 
        return altura(raiz); 
    }
    
    private int altura(Nodo x) 
    {
        if (x == null) 
        	return -1;
        return 1 + Math.max(altura(x.esq), altura(x.dir));
    }

    public K min() 
    {
        if (vazio()) 
        	return null;
        return min(raiz).chave;
    } 

    private Nodo min(Nodo x) 
    { 
        assert x != null;
        if (x.esq == null) 
        	return x; 
        else                
        	return min(x.esq); 
    } 

    public K max() 
    {
        if (vazio()) 
        	return null;
        return max(raiz).chave;
    } 

    private Nodo max(Nodo x) 
    { 
        assert x != null;
        if (x.dir == null) 
        	return x; 
        else                 
        	return max(x.dir); 
    } 

    public K fundo(K chave) 
    {
    	Nodo x = fundo(raiz, chave);
        if (x == null) 
        	return null;
        else           
        	return x.chave;
    }    

    private Nodo fundo(Nodo x, K chave) 
    {
        if (x == null) 
        	return null;
        
        int cmp = chave.compareTo(x.chave);
        if (cmp == 0) 
        	return x;
        if (cmp < 0)  
        	return fundo(x.esq, chave);
        
        Nodo t = fundo(x.dir, chave);
        if (t != null) 
        	return t; 
        else           
        	return x;
    }

    public K teto(K key) 
    {  
    	Nodo x = teto(raiz, key);
        if (x == null) 
        	return null;
        else           
        	return x.chave;  
    }

    private Nodo teto(Nodo x, K chave) 
    {  
        if (x == null) 
        	return null;
        
        int cmp = chave.compareTo(x.chave);
        if (cmp == 0) 
        	return x;
        if (cmp > 0)  
        	return teto(x.dir, chave);
        
        Nodo t = teto(x.esq, chave);
        if (t != null) 
        	return t; 
        else           
        	return x;
    }

    public K selecionar(int k) 
    {
        if (k < 0 || k >= tamanho())  
        	return null;
        
        Nodo x = selecionar(raiz, k);
        return x.chave;
    }

    private Nodo selecionar(Nodo x, int k) 
    {
        assert x != null;
        assert k >= 0 && k < tamanho(x);
        
        int t = tamanho(x.esq); 
        if (t > k) 
        	return selecionar(x.esq,  k); 
        else if (t < k) 
        	return selecionar(x.dir, k-t-1); 
        else 
        	return x; 
    } 

    public int classificar(K chave) 
    {
        return classificar(chave, raiz);
    } 

    private int classificar(K chave, Nodo x) 
    {
        if (x == null) 
        	return 0; 
        
        int cmp = chave.compareTo(x.chave); 
        if (cmp < 0) 
        	return classificar(chave, x.esq); 
        else if (cmp > 0) 
        	return 1 + tamanho(x.esq) + classificar(chave, x.dir); 
        else              
        	return tamanho(x.esq); 
    } 

    public Iterable<K> chaves() 
    {
        return chaves(min(), max());
    }

    public Iterable<K> chaves(K inferior, K superior) 
    {
    	Queue<K> queue = new LinkedList<K>();
    	chaves(raiz, queue, inferior, superior);
        return queue;
    } 

    private void chaves(Nodo x, Queue<K> fila, K inferior, K superior) 
    { 
        if (x == null) 
        	return; 
        
        int cmplo = inferior.compareTo(x.chave); 
        int cmphi = superior.compareTo(x.chave); 
        if (cmplo < 0) 
        	chaves(x.esq, fila, inferior, superior); 
        if (cmplo <= 0 && cmphi >= 0) 
        	fila.offer(x.chave);
        if (cmphi > 0) 
        	chaves(x.dir, fila, inferior, superior); 
    } 

    public int tamanho(K inferior, K superior) 
    {
        if (inferior.compareTo(superior) > 0) 
        	return 0;
        if (contem(superior)) 
        	return classificar(superior) - classificar(inferior) + 1;
        else              
        	return classificar(superior) - classificar(inferior);
    }

    private boolean checar() 
    {
        if (!isBST())            
        	System.out.println("Arvore não simétrica");
        if (!tamanhoConsistente()) 
        	System.out.println("Arvore não consistente");
        if (!classificacaoConsistente()) 
        	System.out.println("Classificação não consistente");
        if (!is23())             
        	System.out.println("Arvore não é 2-3");
        if (!balanciada())       
        	System.out.println("Arvore não balanceada");
        return isBST() && tamanhoConsistente() && classificacaoConsistente() 
                       && is23() && balanciada();
    }

    private boolean isBST() 
    {
        return isBST(raiz, null, null);
    }

    private boolean isBST(Nodo x, K min, K max) 
    {
        if (x == null) 
        	return true;
        if (min != null && x.chave.compareTo(min) <= 0) 
        	return false;
        if (max != null && x.chave.compareTo(max) >= 0) 
        	return false;
        return isBST(x.esq, min, x.chave) && isBST(x.dir, x.chave, max);
    } 

    private boolean tamanhoConsistente() 
    {
        return tamanhoConsistente(raiz); 
    }

    private boolean tamanhoConsistente(Nodo x) 
    {
        if (x == null) 
        	return true;
        if (x.N != tamanho(x.esq) + tamanho(x.dir) + 1) 
        	return false;
        return tamanhoConsistente(x.esq) && tamanhoConsistente(x.dir);
    } 

    private boolean classificacaoConsistente() 
    {
        for (int i = 0; i < tamanho(); i++)
            if (i != classificar(selecionar(i))) 
            	return false;
        for (K key : chaves())
            if (key.compareTo(selecionar(classificar(key))) != 0) 
            	return false;
        return true;
    }

    private boolean is23() 
    { 
    	return is23(raiz); 
    }
    
    private boolean is23(Nodo x) 
    {
        if (x == null) 
        	return true;
        if (isRed(x.dir)) 
        	return false;
        if (x != raiz && isRed(x) && isRed(x.esq))
            return false;
        return is23(x.esq) && is23(x.dir);
    } 

    private boolean balanciada() 
    { 
        int black = 0;     
        Nodo x = raiz;
        while (x != null) 
        {
            if (!isRed(x)) black++;
            x = x.esq;
        }
        return balanciada(raiz, black);
    }

    private boolean balanciada(Nodo x, int black) 
    {
        if (x == null) 
        	return black == 0;
        if (!isRed(x)) 
        	black--;
        return balanciada(x.esq, black) && balanciada(x.dir, black);
    } 
}