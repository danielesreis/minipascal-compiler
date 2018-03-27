package compilador;
import compilador.ast.*;

public class Printer implements Visitor{
    
    public void print (Programa p)
    {
        System.out.println("Iniciando impressao da arvore");
        p.visit(this);
    }
    
    public void visitPrograma(Programa p)
    {
        if (p != null)
        {
            if (p.I != null) System.out.println(p.I.spelling1);
            if (p.C != null) 
            {
                if (p.C instanceof CorpoComDeclaracaoComando) ((CorpoComDeclaracaoComando)p.C).visit(this);
                if (p.C instanceof CorpoSemComando) ((CorpoSemComando)p.C).visit(this);
                if (p.C instanceof CorpoSemDeclaracao) ((CorpoSemDeclaracao)p.C).visit(this);
            }
        }
    }
    
    public void visitComandoAtribuicao(ComandoAtribuicao c)
    {
        if (c != null)
        {
            if (c.I != null) System.out.println(c.I.spelling1);
            if (c.E != null) 
            {
                if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this);
                if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this);
                if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this);
            }
        }
    }
    
    public void visitComandoAtribuicaoIndexada(ComandoAtribuicaoIndexada c)
    {
        if (c != null)
        {
            if (c.I != null) System.out.println(c.I.spelling1);
            if (c.E1 != null) 
            {
                if (c.E1 instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E1).visit(this);
                if (c.E1 instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E1).visit(this);
                if (c.E1 instanceof ExpressaoSimples) ((ExpressaoSimples)c.E1).visit(this);
            }
            if (c.E2 != null)
            {
                if (c.E2 instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E2).visit(this);
                if (c.E2 instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E2).visit(this);
                if (c.E2 instanceof ExpressaoSimples) ((ExpressaoSimples)c.E2).visit(this);
            }
        }
    }
    
    public void visitComandoChamadaProcedimento(ComandoChamadaProcedimento c)
    {
        if (c != null)
        {
            if (c.I != null) System.out.println(c.I.spelling1);
            if (c.E != null)
            {
                if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this);
                if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this);
                if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this);
            }
        }
    }
    
    public void visitComandoChamadaProcedimentoSemArgs(ComandoChamadaProcedimentoSemArgs c)
    {
        if (c != null)
        {
            if (c.I != null) System.out.println(c.I.spelling1);
        }
    }
    
    public void visitComandoComposto(ComandoComposto c)
    {
        if (c != null)
        {
            if (c.CS != null) c.CS.visit(this);
        }
    }
    
    public void visitComandoIf(ComandoIf c)
    {
        if (c != null)
        {
            if (c.E != null)
            {
                if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this);
                if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this);
                if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this);
            }
            if (c.C1 != null)
            {
                if (c.C1 instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C1).visit(this);
                if (c.C1 instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C1).visit(this);
                if (c.C1 instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C1).visit(this);
                if (c.C1 instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C1).visit(this);
                if (c.C1 instanceof ComandoComposto) ((ComandoComposto)c.C1).visit(this);
                if (c.C1 instanceof ComandoIf) ((ComandoIf)c.C1).visit(this);
                if (c.C1 instanceof ComandoIfElse) ((ComandoIfElse)c.C1).visit(this);
                if (c.C1 instanceof ComandoSequencial) ((ComandoSequencial)c.C1).visit(this);
                if (c.C1 instanceof ComandoWhile) ((ComandoWhile)c.C1).visit(this);
            }
        }
    }
    
    public void visitComandoIfElse(ComandoIfElse c)
    {
        if (c != null)
        {
            if (c.E != null)
            {
                if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this);
                if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this);
                if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this);
            }
            if (c.C1 != null) 
            {
                if (c.C1 instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C1).visit(this);
                if (c.C1 instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C1).visit(this);
                if (c.C1 instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C1).visit(this);
                if (c.C1 instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C1).visit(this);
                if (c.C1 instanceof ComandoComposto) ((ComandoComposto)c.C1).visit(this);
                if (c.C1 instanceof ComandoIf) ((ComandoIf)c.C1).visit(this);
                if (c.C1 instanceof ComandoIfElse) ((ComandoIfElse)c.C1).visit(this);
                if (c.C1 instanceof ComandoSequencial) ((ComandoSequencial)c.C1).visit(this);
                if (c.C1 instanceof ComandoWhile) ((ComandoWhile)c.C1).visit(this);
            }
            if (c.C2 != null)
            {
                if (c.C2 instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C2).visit(this);
                if (c.C2 instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C2).visit(this);
                if (c.C2 instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C2).visit(this);
                if (c.C2 instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C2).visit(this);
                if (c.C2 instanceof ComandoComposto) ((ComandoComposto)c.C2).visit(this);
                if (c.C2 instanceof ComandoIf) ((ComandoIf)c.C2).visit(this);
                if (c.C2 instanceof ComandoIfElse) ((ComandoIfElse)c.C2).visit(this);
                if (c.C2 instanceof ComandoSequencial) ((ComandoSequencial)c.C2).visit(this);
                if (c.C2 instanceof ComandoWhile) ((ComandoWhile)c.C2).visit(this);
            }
        }
    }
    
    public void visitComandoSequencial(ComandoSequencial c)
    {
        if (c != null)
        {
            if (c.C1 != null) 
            {
                if (c.C1 instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C1).visit(this);
                if (c.C1 instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C1).visit(this);
                if (c.C1 instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C1).visit(this);
                if (c.C1 instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C1).visit(this);
                if (c.C1 instanceof ComandoComposto) ((ComandoComposto)c.C1).visit(this);
                if (c.C1 instanceof ComandoIf) ((ComandoIf)c.C1).visit(this);
                if (c.C1 instanceof ComandoIfElse) ((ComandoIfElse)c.C1).visit(this);
                if (c.C1 instanceof ComandoSequencial) ((ComandoSequencial)c.C1).visit(this);
                if (c.C1 instanceof ComandoWhile) ((ComandoWhile)c.C1).visit(this);
            }
            if (c.C2 != null)
            {
                if (c.C2 instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C2).visit(this);
                if (c.C2 instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C2).visit(this);
                if (c.C2 instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C2).visit(this);
                if (c.C2 instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C2).visit(this);
                if (c.C2 instanceof ComandoComposto) ((ComandoComposto)c.C2).visit(this);
                if (c.C2 instanceof ComandoIf) ((ComandoIf)c.C2).visit(this);
                if (c.C2 instanceof ComandoIfElse) ((ComandoIfElse)c.C2).visit(this);
                if (c.C2 instanceof ComandoSequencial) ((ComandoSequencial)c.C2).visit(this);
                if (c.C2 instanceof ComandoWhile) ((ComandoWhile)c.C2).visit(this);
            }
        }
    }
    
    public void visitComandoWhile(ComandoWhile c)
    {
        if (c != null)
        {
            if (c.E != null)
            {
                if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this);
                if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this);
                if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this);
            }
            if (c.C != null)
            {
                if (c.C instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C).visit(this);
                if (c.C instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C).visit(this);
                if (c.C instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C).visit(this);
                if (c.C instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C).visit(this);
                if (c.C instanceof ComandoComposto) ((ComandoComposto)c.C).visit(this);
                if (c.C instanceof ComandoIf) ((ComandoIf)c.C).visit(this);
                if (c.C instanceof ComandoIfElse) ((ComandoIfElse)c.C).visit(this);
                if (c.C instanceof ComandoSequencial) ((ComandoSequencial)c.C).visit(this);
                if (c.C instanceof ComandoWhile) ((ComandoWhile)c.C).visit(this);
            }
        }
    }
    
    public void visitCorpoComDeclaracaoComando(CorpoComDeclaracaoComando c)
    {
        if (c != null)
        {
            if (c.D != null)
            {
                if (c.D instanceof DeclaracaoFuncao) ((DeclaracaoFuncao)c.D).visit(this);
                if (c.D instanceof DeclaracaoFuncaoSemArgs) ((DeclaracaoFuncaoSemArgs)c.D).visit(this);
                if (c.D instanceof DeclaracaoProcedure) ((DeclaracaoProcedure)c.D).visit(this);
                if (c.D instanceof DeclaracaoProcedureSemArgs) ((DeclaracaoProcedureSemArgs)c.D).visit(this);
                if (c.D instanceof DeclaracaoSequencial) ((DeclaracaoSequencial)c.D).visit(this);
                if (c.D instanceof DeclaracaoVariavel) ((DeclaracaoVariavel)c.D).visit(this);
            }
            if (c.C != null)
            {
                if (c.C instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C).visit(this);
                if (c.C instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C).visit(this);
                if (c.C instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C).visit(this);
                if (c.C instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C).visit(this);
                if (c.C instanceof ComandoComposto) ((ComandoComposto)c.C).visit(this);
                if (c.C instanceof ComandoIf) ((ComandoIf)c.C).visit(this);
                if (c.C instanceof ComandoIfElse) ((ComandoIfElse)c.C).visit(this);
                if (c.C instanceof ComandoSequencial) ((ComandoSequencial)c.C).visit(this);
                if (c.C instanceof ComandoWhile) ((ComandoWhile)c.C).visit(this);
            }
        }
    }
    
    public void visitCorpoSemComando(CorpoSemComando c)
    {
        if (c != null)
        {
            if (c.D != null)
            {
                if (c.D instanceof DeclaracaoFuncao) ((DeclaracaoFuncao)c.D).visit(this);
                if (c.D instanceof DeclaracaoFuncaoSemArgs) ((DeclaracaoFuncaoSemArgs)c.D).visit(this);
                if (c.D instanceof DeclaracaoProcedure) ((DeclaracaoProcedure)c.D).visit(this);
                if (c.D instanceof DeclaracaoProcedureSemArgs) ((DeclaracaoProcedureSemArgs)c.D).visit(this);
                if (c.D instanceof DeclaracaoSequencial) ((DeclaracaoSequencial)c.D).visit(this);
                if (c.D instanceof DeclaracaoVariavel) ((DeclaracaoVariavel)c.D).visit(this);
            }
        }
    }
    
    public void visitCorpoSemDeclaracao(CorpoSemDeclaracao c)
    {
        if (c != null)
        {
            if (c.C != null)
            {
                if (c.C instanceof ComandoAtribuicao) ((ComandoAtribuicao)c.C).visit(this);
                if (c.C instanceof ComandoAtribuicaoIndexada) ((ComandoAtribuicaoIndexada)c.C).visit(this);
                if (c.C instanceof ComandoChamadaProcedimento) ((ComandoChamadaProcedimento)c.C).visit(this);
                if (c.C instanceof ComandoChamadaProcedimentoSemArgs) ((ComandoChamadaProcedimentoSemArgs)c.C).visit(this);
                if (c.C instanceof ComandoComposto) ((ComandoComposto)c.C).visit(this);
                if (c.C instanceof ComandoIf) ((ComandoIf)c.C).visit(this);
                if (c.C instanceof ComandoIfElse) ((ComandoIfElse)c.C).visit(this);
                if (c.C instanceof ComandoSequencial) ((ComandoSequencial)c.C).visit(this);
                if (c.C instanceof ComandoWhile) ((ComandoWhile)c.C).visit(this);
            }
        }
    }
    
    public void visitDeclaracaoFuncao(DeclaracaoFuncao d)
    {
        if (d != null)
        {
            if (d.TS != null) d.TS.visit(this);
            if (d.I != null) System.out.println(d.I.spelling1);
            if (d.P != null)
            {
                if (d.P instanceof ParametroSequencial) ((ParametroSequencial) d.P).visit(this);
                if (d.P instanceof ParametroSimples) ((ParametroSimples) d.P).visit(this);
            }
            if (d.C != null)
            {
                if (d.C instanceof CorpoComDeclaracaoComando) ((CorpoComDeclaracaoComando)d.C).visit(this);
                if (d.C instanceof CorpoSemComando) ((CorpoSemComando)d.C).visit(this);
                if (d.C instanceof CorpoSemDeclaracao) ((CorpoSemDeclaracao)d.C).visit(this);
            }
        }
    }
    
    public void visitDeclaracaoFuncaoSemArgs(DeclaracaoFuncaoSemArgs d)
    {
        if (d != null)
        {
            if (d.TS != null) d.TS.visit(this);
            if (d.I != null) System.out.println(d.I.spelling1);
            if (d.C != null) 
            {
                if (d.C instanceof CorpoComDeclaracaoComando) ((CorpoComDeclaracaoComando)d.C).visit(this);
                if (d.C instanceof CorpoSemComando) ((CorpoSemComando)d.C).visit(this);
                if (d.C instanceof CorpoSemDeclaracao) ((CorpoSemDeclaracao)d.C).visit(this);
            }
        }
    }
    
    public void visitDeclaracaoProcedure(DeclaracaoProcedure d)
    {
        if (d != null)
        {
            if (d.I != null) System.out.println(d.I.spelling1);
            if (d.P != null)
            {
                if (d.P instanceof ParametroSequencial) ((ParametroSequencial) d.P).visit(this);
                if (d.P instanceof ParametroSimples) ((ParametroSimples) d.P).visit(this);
            }
            if (d.C != null)
            {
                if (d.C instanceof CorpoComDeclaracaoComando) ((CorpoComDeclaracaoComando)d.C).visit(this);
                if (d.C instanceof CorpoSemComando) ((CorpoSemComando)d.C).visit(this);
                if (d.C instanceof CorpoSemDeclaracao) ((CorpoSemDeclaracao)d.C).visit(this);
            }
        }
    }
    
    public void visitDeclaracaoProcedureSemArgs(DeclaracaoProcedureSemArgs d)
    {
        if (d != null)
        {
            if (d.I != null) System.out.println(d.I.spelling1);
            if (d.C != null) 
            {
                if (d.C instanceof CorpoComDeclaracaoComando) ((CorpoComDeclaracaoComando)d.C).visit(this);
                if (d.C instanceof CorpoSemComando) ((CorpoSemComando)d.C).visit(this);
                if (d.C instanceof CorpoSemDeclaracao) ((CorpoSemDeclaracao)d.C).visit(this);
            }
        }
    }
    
    public void visitDeclaracaoSequencial(DeclaracaoSequencial d)
    {
        if (d != null)
        {
            if (d.D1 != null)
            {
                if (d.D1 instanceof DeclaracaoFuncao) ((DeclaracaoFuncao)d.D1).visit(this);
                if (d.D1 instanceof DeclaracaoFuncaoSemArgs) ((DeclaracaoFuncaoSemArgs)d.D1).visit(this);
                if (d.D1 instanceof DeclaracaoProcedure) ((DeclaracaoProcedure)d.D1).visit(this);
                if (d.D1 instanceof DeclaracaoProcedureSemArgs) ((DeclaracaoProcedureSemArgs)d.D1).visit(this);
                if (d.D1 instanceof DeclaracaoSequencial) ((DeclaracaoSequencial)d.D1).visit(this);
                if (d.D1 instanceof DeclaracaoVariavel) ((DeclaracaoVariavel)d.D1).visit(this);
            }
            if (d.D2 != null)
            {
                if (d.D2 instanceof DeclaracaoFuncao) ((DeclaracaoFuncao)d.D2).visit(this);
                if (d.D2 instanceof DeclaracaoFuncaoSemArgs) ((DeclaracaoFuncaoSemArgs)d.D2).visit(this);
                if (d.D2 instanceof DeclaracaoProcedure) ((DeclaracaoProcedure)d.D2).visit(this);
                if (d.D2 instanceof DeclaracaoProcedureSemArgs) ((DeclaracaoProcedureSemArgs)d.D2).visit(this);
                if (d.D2 instanceof DeclaracaoSequencial) ((DeclaracaoSequencial)d.D2).visit(this);
                if (d.D2 instanceof DeclaracaoVariavel) ((DeclaracaoVariavel)d.D2).visit(this);
            }
        }
    }
    
    public void visitDeclaracaoVariavel(DeclaracaoVariavel d)
    {
        if (d != null)
        {
            if (d.I != null)
            {
                System.out.println(d.I.spelling1);
                if (d.I instanceof IdentifierSequencial) System.out.println(d.I.spelling2);
            }
            if (d.T != null) 
            {
                if (d.T instanceof TipoAgregado) ((TipoAgregado)d.T).visit(this);
                if (d.T instanceof TipoSimples) ((TipoSimples)d.T).visit(this);
            }
        }
    }
    
    public void visitExpressaoBinaria(ExpressaoBinaria e)
    {
        if (e != null)
        {
            if (e.OR != null) System.out.println(e.OR.spelling1);
            if (e.ES1 != null) e.ES1.visit(this);
            if (e.ES2 != null) e.ES2.visit(this); 
        }
    }
    
    public void visitExpressaoSequencial(ExpressaoSequencial e)
    {
        if (e != null)
        {
            if (e.E1 != null)
            {
                if (e.E1 instanceof ExpressaoBinaria) ((ExpressaoBinaria)e.E1).visit(this);
                if (e.E1 instanceof ExpressaoSequencial) ((ExpressaoSequencial)e.E1).visit(this);
                if (e.E1 instanceof ExpressaoSimples) ((ExpressaoSimples)e.E1).visit(this);

            }
            if (e.E2 != null)
            {
                if (e.E2 instanceof ExpressaoBinaria) ((ExpressaoBinaria)e.E2).visit(this);
                if (e.E2 instanceof ExpressaoSequencial) ((ExpressaoSequencial)e.E2).visit(this);
                if (e.E2 instanceof ExpressaoSimples) ((ExpressaoSimples)e.E2).visit(this);
            }        
        }
    }
    
    public void visitExpressaoSimples(ExpressaoSimples e)
    {
        if (e != null)
        {
            if (e.F != null)
            {
                if (e.F instanceof FatorId) ((FatorId)e.F).visit(this);
                if (e.F instanceof FatorAdSequencial) ((FatorAdSequencial)e.F).visit(this);
                if (e.F instanceof FatorChamadaFuncao) ((FatorChamadaFuncao)e.F).visit(this);
                if (e.F instanceof FatorChamadaFuncaoSemArgs) ((FatorChamadaFuncaoSemArgs)e.F).visit(this);
                if (e.F instanceof FatorMulSequencial) ((FatorMulSequencial)e.F).visit(this);
                if (e.F instanceof Literal) ((Literal)e.F).visit(this);
            }
        }
    }
    
    public void visitFatorAdSequencial(FatorAdSequencial f)
    {
        if (f != null)
        {
            if (f.OA != null) System.out.println(f.OA.spelling1);
            if (f.F1 != null)
            {
                if (f.F1 instanceof FatorId) ((FatorId)f.F1).visit(this);
                if (f.F1 instanceof FatorAdSequencial) ((FatorAdSequencial)f.F1).visit(this);
                if (f.F1 instanceof FatorChamadaFuncao) ((FatorChamadaFuncao)f.F1).visit(this);
                if (f.F1 instanceof FatorChamadaFuncaoSemArgs) ((FatorChamadaFuncaoSemArgs)f.F1).visit(this);
                if (f.F1 instanceof FatorMulSequencial) ((FatorMulSequencial)f.F1).visit(this);
                if (f.F1 instanceof Literal) ((Literal)f.F1).visit(this);
            }
            if (f.F2 != null)
            {
                if (f.F2 instanceof FatorId) ((FatorId)f.F2).visit(this);
                if (f.F2 instanceof FatorAdSequencial) ((FatorAdSequencial)f.F2).visit(this);
                if (f.F2 instanceof FatorChamadaFuncao) ((FatorChamadaFuncao)f.F2).visit(this);
                if (f.F2 instanceof FatorChamadaFuncaoSemArgs) ((FatorChamadaFuncaoSemArgs)f.F2).visit(this);
                if (f.F2 instanceof FatorMulSequencial) ((FatorMulSequencial)f.F2).visit(this);
                if (f.F2 instanceof Literal) ((Literal)f.F2).visit(this);
            }
        }
    }
    
    public void visitFatorChamadaFuncao(FatorChamadaFuncao f)
    {
        if (f != null)
        {
            if (f.I != null) System.out.println(f.I.spelling1);
            if (f.E != null)
            {
                if (f.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)f.E).visit(this);
                if (f.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)f.E).visit(this);
                if (f.E instanceof ExpressaoSimples) ((ExpressaoSimples)f.E).visit(this);
            }
        }
    }
    
    public void visitFatorChamadaFuncaoSemArgs(FatorChamadaFuncaoSemArgs f)
    {
        if (f != null)
        {
            if (f.I != null) System.out.println(f.I.spelling1);
        }
    }
    
    public void visitFatorId(FatorId f)
    {
        if (f != null)
        {
            if (f.I != null) System.out.println(f.I.spelling1);
        }
    }
    
    public void visitFatorMulSequencial(FatorMulSequencial f)
    {
        if (f != null)
        {
            if (f.OM != null) System.out.println(f.OM.spelling1);
            if (f.F1 != null)
            {
                if (f.F1 instanceof FatorId) ((FatorId)f.F1).visit(this);
                if (f.F1 instanceof FatorAdSequencial) ((FatorAdSequencial)f.F1).visit(this);
                if (f.F1 instanceof FatorChamadaFuncao) ((FatorChamadaFuncao)f.F1).visit(this);
                if (f.F1 instanceof FatorChamadaFuncaoSemArgs) ((FatorChamadaFuncaoSemArgs)f.F1).visit(this);
                if (f.F1 instanceof FatorMulSequencial) ((FatorMulSequencial)f.F1).visit(this);
                if (f.F1 instanceof Literal) ((Literal)f.F1).visit(this);
            }
            if (f.F2 != null)
            {
                if (f.F2 instanceof FatorId) ((FatorId)f.F2).visit(this);
                if (f.F2 instanceof FatorAdSequencial) ((FatorAdSequencial)f.F2).visit(this);
                if (f.F2 instanceof FatorChamadaFuncao) ((FatorChamadaFuncao)f.F2).visit(this);
                if (f.F2 instanceof FatorChamadaFuncaoSemArgs) ((FatorChamadaFuncaoSemArgs)f.F2).visit(this);
                if (f.F2 instanceof FatorMulSequencial) ((FatorMulSequencial)f.F2).visit(this);
                if (f.F2 instanceof Literal) ((Literal)f.F2).visit(this);
            }
        }
    }
    
    public void visitIdentifier(Identifier i)
    {
        System.out.println("teste visit identifier");
    }
    
    public void visitIdentifierSequencial(IdentifierSequencial i)
    {
        if (i != null)
        {
            System.out.println(i.spelling1);
            System.out.println(i.spelling2);
        }
    }
    
    public void visitLiteral(Literal l)
    {
        System.out.println(l.spelling);
    }
    
    public void visitOpAd(OpAd o)
    {
        System.out.println("teste visit opad");
    }
    
    public void visitOpMul(OpMul o)
    {
        System.out.println("teste visit opmul");
    }
    
    public void visitOpRel(OpRel o)
    {
        System.out.println("teste visit oprel");
    }
    
    public void visitParametroSequencial(ParametroSequencial p)
    {
        if (p != null)
        {
            if (p.P1 != null)
            {
                if (p.P1 instanceof ParametroSequencial) ((ParametroSequencial)p.P1).visit(this);
                if (p.P1 instanceof ParametroSimples) ((ParametroSimples)p.P1).visit(this);
            }
        }
    }
    
    public void visitParametroSimples(ParametroSimples p)
    {
        if (p != null)
        {
            if (p.I != null)
            {
                System.out.println(p.I.spelling1);
                if (p.I instanceof IdentifierSequencial) System.out.println(p.I.spelling2);
            }
            if (p.TS != null) p.TS.visit(this);
        }
    }
    
    public void visitTipoAgregado(TipoAgregado t)
    {
        if (t != null)
        {
            if (t.L1 != null) t.L1.visit(this);
            if (t.L2 != null) t.L2.visit(this);
            if (t.T != null) t.T.visit(this);
        }
    }
    
    public void visitTipoSimples(TipoSimples t)
    {
        if (t != null)
        {
            System.out.println(t.spelling);
        }
    }
    
    public void visitVariavelId(VariavelId v)
    {
        if (v != null)
        {
            if (v.I != null) System.out.println(v.I.spelling1);
        }
    }
    
    public void visitVariavelIndexada(VariavelIndexada v)
    {
        if (v != null)
        {
            if (v.I != null) System.out.println(v.I.spelling1);
            if (v.E != null)
            {
                if (v.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)v.E).visit(this);
                if (v.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)v.E).visit(this);
                if (v.E instanceof ExpressaoSimples) ((ExpressaoSimples)v.E).visit(this);
            }
        }
    }
}