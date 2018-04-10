package compilador;
import compilador.ast.*;

public class Printer implements Visitor{
    int pipe = 0;
    
    public void indent()
    {
        int i;
        for (i = 0; i < pipe; i++)
            Compilador.compilerFrame.setAstText(" | ", false);  
    }
    
    public void print (Programa p, Object o)
    {
        Compilador.compilerFrame.setAstText("Iniciando impressao da arvore", true);
        p.visit(this, null);
    }
    
    public Object visitPrograma(Programa p, Object o)
    {
        if (p != null)
        {
            if (p.I != null) Compilador.compilerFrame.setAstText(((IdentifierSimples)p.I).spelling, true);
            if (p.C != null) 
            {
                if (p.C instanceof CorpoComDeclaracaoComando) ((CorpoComDeclaracaoComando)p.C).visit(this, null);
                if (p.C instanceof CorpoSemComando) ((CorpoSemComando)p.C).visit(this, null);
                if (p.C instanceof CorpoSemDeclaracao) ((CorpoSemDeclaracao)p.C).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitComandoAtribuicao(ComandoAtribuicao c, Object o)
    {
        indent();
        if (c != null)
        {
            Compilador.compilerFrame.setAstText(":=", true);
            if (c.I != null)
            {
                pipe++;
                indent();
                Compilador.compilerFrame.setAstText(((IdentifierSimples)c.I).spelling , true);
                pipe--;
            }
            if (c.E != null) 
            {
                pipe++;
                indent();
                if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this, null);
                if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this, null);
                if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this, null);
                pipe--;
            }
        }
        return null;
    }
    
    public Object visitComandoAtribuicaoIndexada(ComandoAtribuicaoIndexada c, Object o)
    {
        indent();
        if (c != null)
        {
            Compilador.compilerFrame.setAstText(":=", true);
            if (c.I != null)
            {
                pipe++;
                indent();
                Compilador.compilerFrame.setAstText(((IdentifierSimples)c.I).spelling + "[]", true);
                pipe--;
            }
            if (c.E1 != null) 
            {
                pipe++;
                pipe++;
                indent();
                if (c.E1 instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E1).visit(this, null);
                if (c.E1 instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E1).visit(this, null);
                if (c.E1 instanceof ExpressaoSimples) ((ExpressaoSimples)c.E1).visit(this, null);
                pipe--;
                pipe--;
            }
            if (c.E2 != null)
            {
                pipe++;
                indent();
                if (c.E2 instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E2).visit(this, null);
                if (c.E2 instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E2).visit(this, null);
                if (c.E2 instanceof ExpressaoSimples) ((ExpressaoSimples)c.E2).visit(this, null);
                pipe--;
            }
        }
        return null;
    }
    
    public Object visitComandoBegin(ComandoBegin c, Object o)
    {
        indent();
        Compilador.compilerFrame.setAstText("begin", true);
        if (c != null)
        {
                pipe++;
                if (c.C instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C).visit(this, null);
                if (c.C instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C).visit(this, null);
                if (c.C instanceof ComandoBegin) ((ComandoBegin)c.C).visit(this, null);
                if (c.C instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C).visit(this, null);
                if (c.C instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C).visit(this, null);
                if (c.C instanceof ComandoIf) ((ComandoIf)c.C).visit(this, null);
                if (c.C instanceof ComandoIfElse) ((ComandoIfElse)c.C).visit(this, null);
                if (c.C instanceof ComandoSequencial) ((ComandoSequencial)c.C).visit(this, null);
                if (c.C instanceof ComandoWhile) ((ComandoWhile)c.C).visit(this, null);
                pipe--;
        }
        return null;
    }
    
    public Object visitComandoChamadaProcedimento(ComandoChamadaProcedimento c, Object o)
    {
        indent();
        if (c != null)
        {
            if (c.I != null)
            {
                Compilador.compilerFrame.setAstText(((IdentifierSimples)c.I).spelling + "()", true);
            }
            if (c.E != null)
            {
                if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this, null);
                if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this, null);
                if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitComandoChamadaProcedimentoSemArgs(ComandoChamadaProcedimentoSemArgs c, Object o)
    {
        indent();
        if (c != null)
        {
            if (c.I != null) Compilador.compilerFrame.setAstText(((IdentifierSimples)c.I).spelling + "()", true);
        }
        return null;
    }
    
    public Object visitComandoIf(ComandoIf c, Object o)
    {
        indent();
        Compilador.compilerFrame.setAstText("if", true);
        if (c != null)
        {
            if (c.E != null)
            {
                pipe++;
                indent();
                if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this, null);
                if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this, null);
                if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this, null);
                pipe--;
            }
            if (c.C1 != null)
            {
                pipe++;
                if (c.C1 instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoBegin) ((ComandoBegin)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoIf) ((ComandoIf)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoIfElse) ((ComandoIfElse)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoSequencial) ((ComandoSequencial)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoWhile) ((ComandoWhile)c.C1).visit(this, null);
                pipe--;
            }
        }
        return null;
    }
    
    public Object visitComandoIfElse(ComandoIfElse c, Object o)
    {
        indent();
        Compilador.compilerFrame.setAstText("if", true);
        if (c != null)
        {
            if (c.E != null)
            {
                pipe++;
                indent();
                if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this, null);
                if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this, null);
                if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this, null);
                pipe--;
            }
            if (c.C1 != null) 
            {
                pipe++;
                if (c.C1 instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoBegin) ((ComandoBegin)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoIf) ((ComandoIf)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoIfElse) ((ComandoIfElse)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoSequencial) ((ComandoSequencial)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoWhile) ((ComandoWhile)c.C1).visit(this, null);
                pipe--;
            }
            if (c.C2 != null)
            {
                pipe++;
                indent();
                Compilador.compilerFrame.setAstText("else", true);
                pipe++;
                if (c.C2 instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoBegin) ((ComandoBegin)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoIf) ((ComandoIf)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoIfElse) ((ComandoIfElse)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoSequencial) ((ComandoSequencial)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoWhile) ((ComandoWhile)c.C2).visit(this, null);
                pipe--;
                pipe--;
            }
        }
        return null;
    }
    
    public Object visitComandoSequencial(ComandoSequencial c, Object o)
    {
        if (c != null)
        {
            if (c.C1 != null) 
            {
                if (c.C1 instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoBegin) ((ComandoBegin)c.C1).visit(this, null);                
                if (c.C1 instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoIf) ((ComandoIf)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoIfElse) ((ComandoIfElse)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoSequencial) ((ComandoSequencial)c.C1).visit(this, null);
                if (c.C1 instanceof ComandoWhile) ((ComandoWhile)c.C1).visit(this, null);
            }
            if (c.C2 != null)
            {
                if (c.C2 instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoBegin) ((ComandoBegin)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoIf) ((ComandoIf)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoIfElse) ((ComandoIfElse)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoSequencial) ((ComandoSequencial)c.C2).visit(this, null);
                if (c.C2 instanceof ComandoWhile) ((ComandoWhile)c.C2).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitComandoWhile(ComandoWhile c, Object o)
    {
        indent();
        Compilador.compilerFrame.setAstText("while", true);
        if (c != null)
        {
            if (c.E != null)
            {
                pipe++;
                indent();
                if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this, null);
                if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this, null);
                if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this, null);
                pipe--;
            }
            if (c.C != null)
            {
                pipe++;
                if (c.C instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C).visit(this, null);
                if (c.C instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C).visit(this, null);
                if (c.C instanceof ComandoBegin) ((ComandoBegin)c.C).visit(this, null);
                if (c.C instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C).visit(this, null);
                if (c.C instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C).visit(this, null);
                if (c.C instanceof ComandoIf) ((ComandoIf)c.C).visit(this, null);
                if (c.C instanceof ComandoIfElse) ((ComandoIfElse)c.C).visit(this, null);
                if (c.C instanceof ComandoSequencial) ((ComandoSequencial)c.C).visit(this, null);
                if (c.C instanceof ComandoWhile) ((ComandoWhile)c.C).visit(this, null);
                pipe--;
            }
        }
        return null;
    }
    
    public Object visitCorpoComDeclaracaoComando(CorpoComDeclaracaoComando c, Object o)
    {
        if (c != null)
        {
            if (c.D != null)
            {
                pipe++;
                if (c.D instanceof DeclaracaoFuncao) ((DeclaracaoFuncao)c.D).visit(this, null);
                if (c.D instanceof DeclaracaoFuncaoSemArgs) ((DeclaracaoFuncaoSemArgs)c.D).visit(this, null);
                if (c.D instanceof DeclaracaoProcedure) ((DeclaracaoProcedure)c.D).visit(this, null);
                if (c.D instanceof DeclaracaoProcedureSemArgs) ((DeclaracaoProcedureSemArgs)c.D).visit(this, null);
                if (c.D instanceof DeclaracaoSequencial) ((DeclaracaoSequencial)c.D).visit(this, null);
                if (c.D instanceof DeclaracaoVariavel) ((DeclaracaoVariavel)c.D).visit(this, null);
            }
            if (c.C != null)
            {
                if (c.C instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C).visit(this, null);
                if (c.C instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C).visit(this, null);
                if (c.C instanceof ComandoBegin) ((ComandoBegin)c.C).visit(this, null);
                if (c.C instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C).visit(this, null);
                if (c.C instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C).visit(this, null);
                if (c.C instanceof ComandoIf) ((ComandoIf)c.C).visit(this, null);
                if (c.C instanceof ComandoIfElse) ((ComandoIfElse)c.C).visit(this, null);
                if (c.C instanceof ComandoSequencial) ((ComandoSequencial)c.C).visit(this, null);
                if (c.C instanceof ComandoWhile) ((ComandoWhile)c.C).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitCorpoSemComando(CorpoSemComando c, Object o)
    {
        if (c != null)
        {
            if (c.D != null)
            {
                pipe++;
                if (c.D instanceof DeclaracaoFuncao) ((DeclaracaoFuncao)c.D).visit(this, null);
                if (c.D instanceof DeclaracaoFuncaoSemArgs) ((DeclaracaoFuncaoSemArgs)c.D).visit(this, null);
                if (c.D instanceof DeclaracaoProcedure) ((DeclaracaoProcedure)c.D).visit(this, null);
                if (c.D instanceof DeclaracaoProcedureSemArgs) ((DeclaracaoProcedureSemArgs)c.D).visit(this, null);
                if (c.D instanceof DeclaracaoSequencial) ((DeclaracaoSequencial)c.D).visit(this, null);
                if (c.D instanceof DeclaracaoVariavel) ((DeclaracaoVariavel)c.D).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitCorpoSemDeclaracao(CorpoSemDeclaracao c, Object o)
    {
        if (c != null)
        {
            if (c.C != null)
            {
                pipe++;
                if (c.C instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C).visit(this, null);
                if (c.C instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C).visit(this, null);
                if (c.C instanceof ComandoBegin) ((ComandoBegin)c.C).visit(this, null);
                if (c.C instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C).visit(this, null);
                if (c.C instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C).visit(this, null);
                if (c.C instanceof ComandoIf) ((ComandoIf)c.C).visit(this, null);
                if (c.C instanceof ComandoIfElse) ((ComandoIfElse)c.C).visit(this, null);
                if (c.C instanceof ComandoSequencial) ((ComandoSequencial)c.C).visit(this, null);
                if (c.C instanceof ComandoWhile) ((ComandoWhile)c.C).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitDeclaracaoFuncao(DeclaracaoFuncao d, Object o)
    {
        indent();
        if (d != null)
        {
            if (d.TS != null)
            {
                d.TS.visit(this, null);
            }
            if (d.I != null)
            {
                Compilador.compilerFrame.setAstText(((IdentifierSimples)d.I).spelling, true);
            }
            if (d.P != null)
            {
                if (d.P instanceof ParametroSequencial) ((ParametroSequencial) d.P).visit(this, null);
                if (d.P instanceof ParametroSimples) ((ParametroSimples) d.P).visit(this, null);
            }
            if (d.C != null)
            {
                if (d.C instanceof CorpoComDeclaracaoComando) ((CorpoComDeclaracaoComando)d.C).visit(this, null);
                if (d.C instanceof CorpoSemComando) ((CorpoSemComando)d.C).visit(this, null);
                if (d.C instanceof CorpoSemDeclaracao) ((CorpoSemDeclaracao)d.C).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitDeclaracaoFuncaoSemArgs(DeclaracaoFuncaoSemArgs d, Object o)
    {
        indent();
        if (d != null)
        {
            if (d.TS != null)
            {
                d.TS.visit(this, null);
            }
            if (d.I != null)
            {
                Compilador.compilerFrame.setAstText(((IdentifierSimples)d.I).spelling, true);
            }
            if (d.C != null) 
            {
                if (d.C instanceof CorpoComDeclaracaoComando) ((CorpoComDeclaracaoComando)d.C).visit(this, null);
                if (d.C instanceof CorpoSemComando) ((CorpoSemComando)d.C).visit(this, null);
                if (d.C instanceof CorpoSemDeclaracao) ((CorpoSemDeclaracao)d.C).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitDeclaracaoProcedure(DeclaracaoProcedure d, Object o)
    {
        indent();
        if (d != null)
        {
            if (d.I != null)
            {
                Compilador.compilerFrame.setAstText(((IdentifierSimples)d.I).spelling, true);
            }
            if (d.P != null)
            {
                if (d.P instanceof ParametroSequencial) ((ParametroSequencial) d.P).visit(this, null);
                if (d.P instanceof ParametroSimples) ((ParametroSimples) d.P).visit(this, null);
            }
            if (d.C != null)
            {
                if (d.C instanceof CorpoComDeclaracaoComando) ((CorpoComDeclaracaoComando)d.C).visit(this, null);
                if (d.C instanceof CorpoSemComando) ((CorpoSemComando)d.C).visit(this, null);
                if (d.C instanceof CorpoSemDeclaracao) ((CorpoSemDeclaracao)d.C).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitDeclaracaoProcedureSemArgs(DeclaracaoProcedureSemArgs d, Object o)
    {
        indent();
        if (d != null)
        {
            if (d.I != null)
            {
                Compilador.compilerFrame.setAstText(((IdentifierSimples)d.I).spelling, true);
            }
            if (d.C != null) 
            {
                if (d.C instanceof CorpoComDeclaracaoComando) ((CorpoComDeclaracaoComando)d.C).visit(this, null);
                if (d.C instanceof CorpoSemComando) ((CorpoSemComando)d.C).visit(this, null);
                if (d.C instanceof CorpoSemDeclaracao) ((CorpoSemDeclaracao)d.C).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitDeclaracaoSequencial(DeclaracaoSequencial d, Object o)
    {
        if (d != null)
        {
            if (d.D1 != null)
            {                
                if (d.D1 instanceof DeclaracaoFuncao) ((DeclaracaoFuncao)d.D1).visit(this, null);
                if (d.D1 instanceof DeclaracaoFuncaoSemArgs) ((DeclaracaoFuncaoSemArgs)d.D1).visit(this, null);
                if (d.D1 instanceof DeclaracaoProcedure) ((DeclaracaoProcedure)d.D1).visit(this, null);
                if (d.D1 instanceof DeclaracaoProcedureSemArgs) ((DeclaracaoProcedureSemArgs)d.D1).visit(this, null);
                if (d.D1 instanceof DeclaracaoSequencial) ((DeclaracaoSequencial)d.D1).visit(this, null);
                if (d.D1 instanceof DeclaracaoVariavel) ((DeclaracaoVariavel)d.D1).visit(this, null);
            }
            if (d.D2 != null)
            {
                if (d.D2 instanceof DeclaracaoFuncao) ((DeclaracaoFuncao)d.D2).visit(this, null);
                if (d.D2 instanceof DeclaracaoFuncaoSemArgs) ((DeclaracaoFuncaoSemArgs)d.D2).visit(this, null);
                if (d.D2 instanceof DeclaracaoProcedure) ((DeclaracaoProcedure)d.D2).visit(this, null);
                if (d.D2 instanceof DeclaracaoProcedureSemArgs) ((DeclaracaoProcedureSemArgs)d.D2).visit(this, null);
                if (d.D2 instanceof DeclaracaoSequencial) ((DeclaracaoSequencial)d.D2).visit(this, null);
                if (d.D2 instanceof DeclaracaoVariavel) ((DeclaracaoVariavel)d.D2).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitDeclaracaoVariavel(DeclaracaoVariavel d, Object o)
    {
        indent();
        if (d != null)
        {
            if (d.I != null) recursivePrint(d.I);
            if (d.T != null) 
            {
                if (d.T instanceof TipoAgregado) ((TipoAgregado)d.T).visit(this, null);
                if (d.T instanceof TipoSimples) ((TipoSimples)d.T).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitExpressaoBinaria(ExpressaoBinaria e, Object o)
    {
        if (e != null)
        {
            if (e.OR != null)
            {
                Compilador.compilerFrame.setAstText(e.OR.spelling, true);
            }
            if (e.ES1 != null)
            {
                pipe++;
                indent();
                e.ES1.visit(this, null);
                pipe--;
            }
            if (e.ES2 != null)
            {
                pipe++;
                indent();
                e.ES2.visit(this, null);
                pipe--;
            }
        }
        return null;
    }
    
    public Object visitExpressaoSequencial(ExpressaoSequencial e, Object o)
    {
        if (e != null)
        {
            if (e.E1 != null)
            {
                if (e.E1 instanceof ExpressaoBinaria) ((ExpressaoBinaria)e.E1).visit(this, null);
                if (e.E1 instanceof ExpressaoSequencial) ((ExpressaoSequencial)e.E1).visit(this, null);
                if (e.E1 instanceof ExpressaoSimples) ((ExpressaoSimples)e.E1).visit(this, null);

            }
            if (e.E2 != null)
            {
                if (e.E2 instanceof ExpressaoBinaria) ((ExpressaoBinaria)e.E2).visit(this, null);
                if (e.E2 instanceof ExpressaoSequencial) ((ExpressaoSequencial)e.E2).visit(this, null);
                if (e.E2 instanceof ExpressaoSimples) ((ExpressaoSimples)e.E2).visit(this, null);
            }        
        }
        return null;
    }
    
    public Object visitExpressaoSimples(ExpressaoSimples e, Object o)
    {
        if (e != null)
        {
            if (e.F != null)
            {
                if (e.F instanceof FatorId) ((FatorId)e.F).visit(this, null);
                if (e.F instanceof FatorAdSequencial) ((FatorAdSequencial)e.F).visit(this, null);
                if (e.F instanceof FatorChamadaFuncao) ((FatorChamadaFuncao)e.F).visit(this, null);
                if (e.F instanceof FatorChamadaFuncaoSemArgs) ((FatorChamadaFuncaoSemArgs)e.F).visit(this, null);
                if (e.F instanceof FatorMulSequencial) ((FatorMulSequencial)e.F).visit(this, null);
                if (e.F instanceof Literal) ((Literal)e.F).visit(this, null);
                if (e.F instanceof FatorExpressao) ((FatorExpressao)e.F).visit(this, null);
                if (e.F instanceof VariavelIndexada) ((VariavelIndexada)e.F).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitFatorAdSequencial(FatorAdSequencial f, Object o)
    {
        if (f != null)
        {
            if (f.OA != null)
            {
                Compilador.compilerFrame.setAstText(f.OA.spelling, true);
            }
            if (f.F1 != null)
            {
                
                pipe++;
                indent();
                if (f.F1 instanceof FatorId) ((FatorId)f.F1).visit(this, null);
                if (f.F1 instanceof FatorAdSequencial) ((FatorAdSequencial)f.F1).visit(this, null);
                if (f.F1 instanceof FatorChamadaFuncao) ((FatorChamadaFuncao)f.F1).visit(this, null);
                if (f.F1 instanceof FatorChamadaFuncaoSemArgs) ((FatorChamadaFuncaoSemArgs)f.F1).visit(this, null);
                if (f.F1 instanceof FatorMulSequencial)((FatorMulSequencial)f.F1).visit(this, null);
                if (f.F1 instanceof Literal) ((Literal)f.F1).visit(this, null);
                if (f.F1 instanceof FatorExpressao) ((FatorExpressao)f.F1).visit(this, null);
                if (f.F1 instanceof VariavelIndexada) ((VariavelIndexada)f.F1).visit(this, null);
                pipe--;}
            if (f.F2 != null)
            {
                
                pipe++;
                indent();
                if (f.F2 instanceof FatorId) ((FatorId)f.F2).visit(this, null);
                if (f.F2 instanceof FatorAdSequencial) ((FatorAdSequencial)f.F2).visit(this, null);
                if (f.F2 instanceof FatorChamadaFuncao) ((FatorChamadaFuncao)f.F2).visit(this, null);
                if (f.F2 instanceof FatorChamadaFuncaoSemArgs) ((FatorChamadaFuncaoSemArgs)f.F2).visit(this, null);
                if (f.F2 instanceof FatorMulSequencial)
                    ((FatorMulSequencial)f.F2).visit(this, null);
                if (f.F2 instanceof Literal) ((Literal)f.F2).visit(this, null);
                if (f.F2 instanceof FatorExpressao) ((FatorExpressao)f.F2).visit(this, null);
                if (f.F2 instanceof VariavelIndexada) ((VariavelIndexada)f.F2).visit(this, null);
                pipe--;
            }
        }
        return null;
    }
    
    public Object visitFatorChamadaFuncao(FatorChamadaFuncao f, Object o)
    {
        if (f != null)
        {
            if (f.I != null) 
            {
                Compilador.compilerFrame.setAstText(((IdentifierSimples)f.I).spelling + "()", true);
            }
            if (f.E != null)
            {
                if (f.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)f.E).visit(this, null);
                if (f.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)f.E).visit(this, null);
                if (f.E instanceof ExpressaoSimples) ((ExpressaoSimples)f.E).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitFatorChamadaFuncaoSemArgs(FatorChamadaFuncaoSemArgs f, Object o)
    {
        if (f != null)
        {
            if (f.I != null) Compilador.compilerFrame.setAstText(((IdentifierSimples)f.I).spelling + "()", true);
        }
        return null;
    }
    
    public Object visitFatorId(FatorId f, Object o)
    {
        if (f != null)
        {
            if (f.I != null) Compilador.compilerFrame.setAstText(((IdentifierSimples)f.I).spelling, true);
        }
        return null;
    }
    
    public Object visitFatorMulSequencial(FatorMulSequencial f, Object o)
    {
        if (f != null)
        {
            if (f.OM != null)
            {
                Compilador.compilerFrame.setAstText(f.OM.spelling, true);  
            }
            if (f.F1 != null)
            {
                pipe++;
                indent();
                if (f.F1 instanceof FatorId) ((FatorId)f.F1).visit(this, null);
                if (f.F1 instanceof FatorAdSequencial) ((FatorAdSequencial)f.F1).visit(this, null);
                if (f.F1 instanceof FatorChamadaFuncao) ((FatorChamadaFuncao)f.F1).visit(this, null);
                if (f.F1 instanceof FatorChamadaFuncaoSemArgs) ((FatorChamadaFuncaoSemArgs)f.F1).visit(this, null);
                if (f.F1 instanceof FatorMulSequencial) ((FatorMulSequencial)f.F1).visit(this, null);
                if (f.F1 instanceof Literal) ((Literal)f.F1).visit(this, null);
                if (f.F1 instanceof FatorExpressao) ((FatorExpressao)f.F1).visit(this, null);
                if (f.F1 instanceof VariavelIndexada) ((VariavelIndexada)f.F1).visit(this, null);
                pipe--;
            }
            if (f.F2 != null)
            {
                
                pipe++;
                indent();
                if (f.F2 instanceof FatorId) ((FatorId)f.F2).visit(this, null);
                if (f.F2 instanceof FatorAdSequencial) ((FatorAdSequencial)f.F2).visit(this, null);
                if (f.F2 instanceof FatorChamadaFuncao) ((FatorChamadaFuncao)f.F2).visit(this, null);
                if (f.F2 instanceof FatorChamadaFuncaoSemArgs) ((FatorChamadaFuncaoSemArgs)f.F2).visit(this, null);
                if (f.F2 instanceof FatorMulSequencial) ((FatorMulSequencial)f.F2).visit(this, null);
                if (f.F2 instanceof Literal) ((Literal)f.F2).visit(this, null);
                if (f.F2 instanceof FatorExpressao) ((FatorExpressao)f.F2).visit(this, null);
                if (f.F2 instanceof VariavelIndexada) ((VariavelIndexada)f.F2).visit(this, null);
                pipe--;
            }
        }
        return null;
    }
    
    public Object visitFatorExpressao(FatorExpressao f, Object o)
    {
        if (f != null)
        {
            if (f.E != null)
            {
                if (f.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)f.E).visit(this, null);
                if (f.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)f.E).visit(this, null);
                if (f.E instanceof ExpressaoSimples) ((ExpressaoSimples)f.E).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitIdentifierSimples(IdentifierSimples i, Object o)
    {
        if (i != null)
            Compilador.compilerFrame.setAstText(i.spelling, true);
        return null;
    }
    
    public Object visitIdentifierSequencial(IdentifierSequencial i, Object o)
    {
        if (i != null) recursivePrint(i);
        return null;
    }
    
    public Object visitLiteral(Literal l, Object o)
    {
        Compilador.compilerFrame.setAstText(l.spelling, true);
        return null;
    }
    
    public Object visitOpAd(OpAd op, Object o)
    {
        Compilador.compilerFrame.setAstText(op.spelling, true);
        return null;
    }
    
    public Object visitOpMul(OpMul op, Object o)
    {
        Compilador.compilerFrame.setAstText(op.spelling, true);
        return null;
    }
    
    public Object visitOpRel(OpRel op, Object o)
    {
        Compilador.compilerFrame.setAstText(op.spelling, true);
        return null;
    }
    
    public Object visitParametroSequencial(ParametroSequencial p, Object o)
    {
        if (p != null)
        {
            if (p.P1 != null)
            {
                if (p.P1 instanceof ParametroSequencial) ((ParametroSequencial)p.P1).visit(this, null);
                if (p.P1 instanceof ParametroSimples) ((ParametroSimples)p.P1).visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitParametroSimples(ParametroSimples p, Object o)
    {
        if (p != null)
        {
            if (p.I != null) recursivePrint(p.I);
            if (p.TS != null)
            {
                p.TS.visit(this, null);
            }
        }
        return null;
    }
    
    public Object visitTipoAgregado(TipoAgregado t, Object o)
    {
        if (t != null)
        {
            if (t.L1 != null) t.L1.visit(this, null);
            if (t.L2 != null) t.L2.visit(this, null);
            if (t.T != null) t.T.visit(this, null);
        }
        return null;
    }
    
    public Object visitTipoSimples(TipoSimples t, Object o)
    {
        if (t != null)
        {
            Compilador.compilerFrame.setAstText(": " + t.spelling, true);
        }return null;
    }
    
    public Object visitVariavelId(VariavelId v, Object o)
    {
        if (v != null)
        {
            if (v.I != null) Compilador.compilerFrame.setAstText(": " + ((IdentifierSimples)v.I).spelling, true);
        }
        return null;
    }
    
    public Object visitVariavelIndexada(VariavelIndexada v, Object o)
    {
        if (v != null)
        {
            if (v.I != null) 
            {
                Compilador.compilerFrame.setAstText(((IdentifierSimples)v.I).spelling + "[]", true);
            }
            if (v.E != null)
            {
                pipe++;
                indent();
                if (v.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)v.E).visit(this, null);
                if (v.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)v.E).visit(this, null);
                if (v.E instanceof ExpressaoSimples) ((ExpressaoSimples)v.E).visit(this, null);
                pipe--;
            }
        }
        return null;
    }
    
    public void recursivePrint(Identifier i)
    {
        if (i instanceof IdentifierSequencial)
        {
            recursivePrint(((IdentifierSequencial)i).I1);
            Compilador.compilerFrame.setAstText(", ", false);
            Compilador.compilerFrame.setAstText(((IdentifierSimples)(((IdentifierSequencial)i).I2)).spelling, false);
        }
        else Compilador.compilerFrame.setAstText(((IdentifierSimples)i).spelling, false);
    }
}