package model;

import model.Contador;

public class ArvoreAVL 
{			 
    public int _nro;
    public int alt_Dir;
    public int alt_Esq;
    public ArvoreAVL dir;
    public ArvoreAVL esq;
 
    public ArvoreAVL insere(ArvoreAVL aux, int _nro, Contador _contador) 
    {
    	ArvoreAVL novo;
        if (aux == null || aux._nro == 0) 
        {
            novo = new ArvoreAVL();
            novo._nro = _nro;
            novo.alt_Dir = 0;
            novo.alt_Esq = 0;
            novo.esq = null;
            novo.dir = null;
            aux = novo;
        } 
        else if (_nro < aux._nro) 
        {
        	_contador.setComp(_contador.getComp() + 1);
            aux.esq = insere(aux.esq, _nro, _contador);
            if (aux.esq.alt_Dir > aux.esq.alt_Esq) 
            {
                aux.alt_Esq = aux.esq.alt_Dir + 1;
            } 
            else 
            {
                aux.alt_Esq = aux.esq.alt_Esq + 1;
            }
            aux = balancear(aux, _contador);
        } 
        else 
        {
        	_contador.setComp(_contador.getComp() + 1);
            aux.dir = insere(aux.dir, _nro, _contador);
            if (aux.dir.alt_Dir > aux.dir.alt_Esq) 
            {
                aux.alt_Dir = aux.dir.alt_Dir + 1;
            } 
            else 
            {
                aux.alt_Dir = aux.dir.alt_Esq + 1;
            }
            aux = balancear(aux, _contador);
        }
        return aux;
    }
 
    public ArvoreAVL balancear(ArvoreAVL aux, Contador _contador) 
    {
        int d, df;
        d = aux.alt_Dir - aux.alt_Esq;
        if (d == 2) 
        {
            df = aux.dir.alt_Dir - aux.dir.alt_Esq;
            if (df >= 0) 
            {
            	_contador.setRot(_contador.getRot() + 1);
                aux = rotacao_Esq(aux);
            } 
            else 
            {
            	_contador.setRot(_contador.getRot() + 2);
                aux.dir = rotacao_Dir(aux.dir);
                aux = rotacao_Esq(aux);
            }
        } 
        else if (d == -2) 
        {
            df = aux.esq.alt_Dir - aux.esq.alt_Esq;
            if (df <= 0) 
            {
            	_contador.setRot(_contador.getRot() + 1);
                aux = rotacao_Dir(aux);
            } 
            else 
            {
            	_contador.setRot(_contador.getRot() + 2);
                aux.esq = rotacao_Esq(aux.esq);
                aux = rotacao_Dir(aux);
            }
        }
        return aux;
    }
 
    public ArvoreAVL rotacao_Esq(ArvoreAVL aux) 
    {
    	ArvoreAVL aux1, aux2;
        aux1 = aux.dir;
        aux2 = aux1.esq;
        aux.dir = aux2;
        aux1.esq = aux;
        if (aux.dir == null) 
        {
            aux.alt_Dir = 0;
        } 
        else if (aux.dir.alt_Esq > aux.dir.alt_Dir) 
        {
            aux.alt_Dir = aux.dir.alt_Esq + 1;
        } 
        else 
        {
            aux.alt_Dir = aux.dir.alt_Dir + 1;
        }
 
        if (aux1.esq.alt_Esq > aux1.esq.alt_Dir) 
        {
            aux1.alt_Esq = aux1.esq.alt_Esq + 1;
        } 
        else 
        {
            aux1.alt_Esq = aux1.esq.alt_Dir + 1;
        }
        return aux1;
    }
 
    public ArvoreAVL rotacao_Dir(ArvoreAVL aux) 
    {
    	ArvoreAVL aux1, aux2;
        aux1 = aux.esq;
        aux2 = aux1.dir;
        aux.esq = aux2;
        aux1.dir = aux;
        if (aux.esq == null) 
        {
            aux.alt_Esq = 0;
        } 
        else if (aux.esq.alt_Esq > aux.esq.alt_Dir) 
        {
            aux.alt_Esq = aux.esq.alt_Esq + 1;
        } 
        else 
        {
            aux.alt_Esq = aux.esq.alt_Dir + 1;
        }
 
        if (aux1.dir.alt_Esq > aux1.dir.alt_Dir) 
        {
            aux1.alt_Dir = aux1.dir.alt_Esq + 1;
        } 
        else 
        {
            aux1.alt_Dir = aux1.dir.alt_Dir + 1;
        }
        return aux1;
    }
 
    public void infixa(ArvoreAVL aux) 
    {
        if (aux != null) 
        {
        	infixa(aux.esq);
            System.out.print(aux._nro + "\n");
            infixa(aux.dir);
        }
    }
 
    public void prefixa(ArvoreAVL aux) 
    {
        if (aux != null) 
        {
            System.out.print(aux._nro + "\n");
            prefixa(aux.esq);
            prefixa(aux.dir);
        }
    }
 
    public void posfixa(ArvoreAVL aux) 
    {
        if (aux != null) 
        {
        	posfixa(aux.esq);
        	posfixa(aux.dir);
            System.out.print(aux._nro + "\n");
        }
    }
 
    public ArvoreAVL deletar(ArvoreAVL aux, int _nro) 
    {
    	ArvoreAVL p, p2;
        if (aux._nro == _nro) 
        {
            if (aux.esq == aux.dir) 
            {
                return null;
            } 
            else if (aux.esq == null) 
            {
                return aux.dir;
            } 
            else if (aux.dir == null) 
            {
                return aux.esq;
            } 
            else 
            {
                p2 = aux.dir;
                p = aux.dir;
                while (p.esq != null) 
                {
                    p = p.esq;
                }
                p.esq = aux.esq;
                return p2;
            }
        } 
        else if (aux._nro < _nro) 
        {
            aux.dir = deletar(aux.dir, _nro);
        } 
        else 
        {
            aux.esq = deletar(aux.esq, _nro);
        }
        return aux;
    }
 
    public ArvoreAVL update(ArvoreAVL aux, Contador _contador) 
    {
        if (aux != null) 
        {
            aux.esq = update(aux.esq, _contador);
            if (aux.esq == null) 
            {
                aux.alt_Esq = 0;
            } 
            else if (aux.esq.alt_Esq > aux.esq.alt_Dir) 
            {
                aux.alt_Esq = aux.esq.alt_Esq + 1;
            } 
            else 
            {
                aux.alt_Esq = aux.esq.alt_Dir + 1;
            }
            
            aux.dir = update(aux.dir, _contador);
            if (aux.dir == null) 
            {
                aux.alt_Esq = 0;
            } 
            else if (aux.dir.alt_Esq > aux.dir.alt_Dir) 
            {
                aux.alt_Dir = aux.dir.alt_Esq + 1;
            } 
            else 
            {
                aux.alt_Dir = aux.dir.alt_Dir + 1;
            }
            aux = balancear(aux, _contador);
        }
        return aux;
    }
 
    public boolean pesquisar(ArvoreAVL aux, int _nro, boolean loc, Contador _contador) 
    {
        if (aux != null && loc == false) 
        {
        	_contador.setComp(_contador.getComp() + 1);
            if (aux._nro == _nro) 
            {
                loc = true;
            } 
            else if (_nro < aux._nro) 
            {
                loc = pesquisar(aux.esq, _nro, loc, _contador);
            } 
            else 
            {
                loc = pesquisar(aux.dir, _nro, loc, _contador);
            }
        }
        return loc;
    }
}
