package compilador;
import compilador.ast.*;

public class Checker implements Visitor{
    IdentificationTable idTable;
    
    public Checker(IdentificationTable idTable) {
        this.idTable = idTable;
    }

    public Object visitPrograma(Programa p, Object o) {
        p.C.visit(this, null);
        return null;
    }

    public Object visitComandoAtribuicao(ComandoAtribuicao c, Object o) {
        Type eType = (Type)c.E.visit(this, null);
        Type iType = (Type)c.I.declaracao.visit(this, null);
        
        if(!eType.equalTo(iType)) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Os dois lados da igualdade não são equivalentes! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");
        
        return null;
    }

    public Object visitComandoAtribuicaoIndexada(ComandoAtribuicaoIndexada c, Object o) {
        Type e1Type = (Type)c.E1.visit(this, null);
        Type e2Type = (Type)c.E2.visit(this, null);
        Type iType = (Type)c.I.declaracao.visit(this, null);
        
        if(!e2Type.equalTo(iType)) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Os dois lados da igualdade não são equivalentes! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");
        
        if(e1Type.kind != Type.INTG) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Índice não é inteiro! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");
        
        return null;
    }

    public Object visitComandoBegin(ComandoBegin c, Object o) {
        c.C.visit(null, this);
        return null;
    }

    public Object visitComandoChamadaProcedimento(ComandoChamadaProcedimento c, Object o) {
        Declaracao d = idTable.retrieve(((IdentifierSimples)c.I).spelling);
        if (d == null)
        {
            Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Procedure não declarada! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");
        }
        
        if (c.E != null)
        {
            if (c.E instanceof ExpressaoBinaria) ((ExpressaoBinaria)c.E).visit(this, null);
            if (c.E instanceof ExpressaoSequencial) ((ExpressaoSequencial)c.E).visit(this, null);
            if (c.E instanceof ExpressaoSimples) ((ExpressaoSimples)c.E).visit(this, null);
        }
        return null;
    }

    public Object visitComandoChamadaProcedimentoSemArgs(ComandoChamadaProcedimentoSemArgs c, Object o) {        
        Declaracao d = idTable.retrieve(((IdentifierSimples)c.I).spelling);
        if (d == null)
        {
            Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Procedure não declarada! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");
        }
        return null;
    }

    public Object visitComandoIf(ComandoIf c, Object o) {
        Type eType = (Type)c.E.visit(this, null);
        
        if(!eType.equalTo(Type.BOOL)) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Condição inválida! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");
        
        c.C1.visit(this, null);
        return null;
    }

    public Object visitComandoIfElse(ComandoIfElse c, Object o) {
        Type eType = (Type)c.E.visit(this, null);
        
        if(!eType.equalTo(Type.BOOL)) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Condição inválida! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");
        
        c.C1.visit(this, null);
        c.C2.visit(this, null);
        return null;
    }

    public Object visitComandoSequencial(ComandoSequencial c, Object o) {
        c.C1.visit(null, this);
        c.C2.visit(null, this);
        return null;
    }

    public Object visitComandoWhile(ComandoWhile c, Object o) {
        Type eType = (Type)c.E.visit(this, null);
        if(!eType.equalTo(Type.BOOL)) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Condição inválida! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");
        c.C.visit(this, null);
        return null;
    }

    public Object visitCorpoComDeclaracaoComando(CorpoComDeclaracaoComando c, Object o) {
        c.D.visit(this, null);
        c.C.visit(this, null);
        return null;
    }

    public Object visitCorpoSemComando(CorpoSemComando c, Object o) {
        c.D.visit(this, null);
        return null;
    }

    public Object visitCorpoSemDeclaracao(CorpoSemDeclaracao c, Object o) {
        c.C.visit(this, null);
        return null;
    }

    public Object visitDeclaracaoFuncao(DeclaracaoFuncao d, Object o) {
        idTable.enter(((IdentifierSimples)d.I).spelling, d);
        d.C.visit(this, null);
        d.I.visit(this, null);
        d.P.visit(this, null);
        d.TS.visit(this, null);
        return d;
    }

    public Object visitDeclaracaoFuncaoSemArgs(DeclaracaoFuncaoSemArgs d, Object o) {
        idTable.enter(((IdentifierSimples)d.I).spelling, d);
        d.C.visit(this, null);
        d.I.visit(this, null);
        d.TS.visit(this, null);
        return d;
    }

    public Object visitDeclaracaoProcedure(DeclaracaoProcedure d, Object o) {
        idTable.enter(((IdentifierSimples)d.I).spelling, d);
        d.C.visit(this, null);
        d.I.visit(this, null);
        d.P.visit(this, null);
        return d;
    }

    public Object visitDeclaracaoProcedureSemArgs(DeclaracaoProcedureSemArgs d, Object o) {
        idTable.enter(((IdentifierSimples)d.I).spelling, d);
        d.C.visit(this, null);
        d.I.visit(this, null);
        return d;
    }

    public Object visitDeclaracaoSequencial(DeclaracaoSequencial d, Object o) {
        d.D1.visit(this, null);
        d.D2.visit(this, null);
        return d;
    }

    public Object visitDeclaracaoVariavel(DeclaracaoVariavel d, Object o) {
        idTable.enter(((IdentifierSimples)d.I).spelling, d);
        d.I.visit(this, null);
        d.T.visit(this, null);
        return d;
    }

    public Object visitExpressaoBinaria(ExpressaoBinaria e, Object o) {
        e.ES1.visit(this, null);
        e.ES2.visit(this, null);
        e.OR.visit(this, null);
        return e.type;
    }

    public Object visitExpressaoSequencial(ExpressaoSequencial e, Object o) {
        e.E1.visit(this, null);
        e.E2.visit(this, null);
        return e.type;
    }

    public Object visitExpressaoSimples(ExpressaoSimples e, Object o) {
        return e.F.visit(this, null);
    }

    public Object visitFatorAdSequencial(FatorAdSequencial f, Object o) {
        f.F1.visit(this, null);
        f.F2.visit(this, null);
        f.OA.visit(this, null);
        return f.type;
    }

    public Object visitFatorChamadaFuncao(FatorChamadaFuncao f, Object o) {
        Declaracao d = idTable.retrieve(((IdentifierSimples)f.I).spelling);
        
        if (d == null) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Declaração da função inválida! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");
        
        f.E.visit(this, null);
        return f.type;
    }

    public Object visitFatorChamadaFuncaoSemArgs(FatorChamadaFuncaoSemArgs f, Object o) {
        Declaracao d = (Declaracao)f.I.declaracao.visit(this, null);
        
        if (d == null) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Declaração da função inválida! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");
        
        return f.type;
    }

    public Object visitFatorId(FatorId f, Object o) {
        return (Declaracao)f.I.declaracao.visit(this, null);
    }

    public Object visitFatorMulSequencial(FatorMulSequencial f, Object o) {
        f.F1.visit(this, null);
        f.F2.visit(this, null);
        f.OM.visit(this, null);
        return f.type;
    }

    public Object visitFatorExpressao(FatorExpressao f, Object o) {
        return (Type)f.E.visit(this, null);
    }

    public Object visitIdentifierSimples(IdentifierSimples i, Object o) {
        Declaracao d = idTable.retrieve(i.spelling);
        return d;
    }

    ///////////////////////////////////////////////////////////////////////////
    public Object visitIdentifierSequencial(IdentifierSequencial i, Object o) {
        return i.declaracao.visit(this, null);
    }

    public Object visitLiteral(Literal l, Object o) {
        l.visit(this, null);
        
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////
    public Object visitOpAd(OpAd op, Object o) {
        op.visit(this, null);
        return null;
    }

    public Object visitOpMul(OpMul op, Object o) {
        op.visit(this, null);
        return null;
    }

    public Object visitOpRel(OpRel op, Object o) {
        op.visit(this, null);
        return null;
    }

    public Object visitParametroSequencial(ParametroSequencial p, Object o) {
        p.P1.visit(this, null);
        p.P2.visit(this, null);
        return null;
    }

    public Object visitParametroSimples(ParametroSimples p, Object o) {
        p.TS.visit(this, null);
        return p.I.declaracao.visit(this, null);
    }

    public Object visitTipoAgregado(TipoAgregado t, Object o) {
        t.L1.visit(this, null);
        t.L2.visit(this, null);
        t.T.visit(this, null);
        return null;
    }

    public Object visitTipoSimples(TipoSimples t, Object o) {
        return null;
    }

    public Object visitVariavelId(VariavelId v, Object o) {
        return v.I.declaracao.visit(this, null);
    }

    public Object visitVariavelIndexada(VariavelIndexada v, Object o) {
        if (!v.E.type.equalTo(Type.INTG))
            Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Índice inválido! " + 
                "(Linha " + Compilador.currentLine + "| Coluna: " + Compilador.currentColumn + ")");        
        return v.I.declaracao.visit(this, null);
    }
}

