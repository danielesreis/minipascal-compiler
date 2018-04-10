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
        Declaracao d = idTable.retrieve(c.I.spelling);
        Tipo eTipo = (Tipo)c.E.visit(this, null);
        Tipo iTipo = (Tipo)d.visit(this, null);
        
        if(!(d instanceof DeclaracaoVariavel) || d == null) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Variável não declarada! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
        
        if(!eTipo.equalTo(iTipo)) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Os dois lados da igualdade não são equivalentes! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
        
        return null;
    }

    public Object visitComandoAtribuicaoIndexada(ComandoAtribuicaoIndexada c, Object o) {
        Declaracao d = idTable.retrieve(c.I.spelling);
        Tipo e1Tipo = (Tipo)c.E1.visit(this, null);
        Tipo e2Tipo = (Tipo)c.E2.visit(this, null);
        Tipo iTipo = (Tipo)d.visit(this, null);
        
        if(!e2Tipo.equalTo(iTipo)) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Os dois lados da igualdade não são equivalentes! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
        
        if(e1Tipo.kind != Tipo.INT_LIT) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Índice não é inteiro! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
        
        return null;
    }

    public Object visitComandoBegin(ComandoBegin c, Object o) {
        c.C.visit(null, this);
        return null;
    }

    public Object visitComandoChamadaProcedimento(ComandoChamadaProcedimento c, Object o) {
        Declaracao d = idTable.retrieve((c.I).spelling);
        if (!(d instanceof DeclaracaoProcedure) || d == null)
        {
            Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Procedure não declarada! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
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
        Declaracao d = idTable.retrieve((c.I).spelling);
        if (!(d instanceof DeclaracaoProcedureSemArgs) || d == null)
        {
            Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Procedure não declarada! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
        }
        return null;
    }

    public Object visitComandoIf(ComandoIf c, Object o) {
        Tipo eTipo = (Tipo)c.E.visit(this, null);
        
        if(!eTipo.equalTo(Tipo.BOOL_LIT)) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Condição inválida! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
        
        c.C1.visit(this, null);
        return null;
    }

    public Object visitComandoIfElse(ComandoIfElse c, Object o) {
        Tipo eTipo = (Tipo)c.E.visit(this, null);
        
        if(!eTipo.equalTo(Tipo.BOOL_LIT)) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Condição inválida! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
        
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
        Tipo eTipo = (Tipo)c.E.visit(this, null);
        if(!eTipo.equalTo(Tipo.BOOL_LIT)) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Condição inválida! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
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
        d.I.declaracao = d;
        d.C.visit(this, null);
        d.I.visit(this, null);
        d.P.visit(this, null);
        d.TS.visit(this, null);
        return d.TS;
    }

    public Object visitDeclaracaoFuncaoSemArgs(DeclaracaoFuncaoSemArgs d, Object o) {
        idTable.enter(((IdentifierSimples)d.I).spelling, d);
        d.I.declaracao = d;
        d.C.visit(this, null);
        d.I.visit(this, null);
        d.TS.visit(this, null);
        return d.TS;
    }

    public Object visitDeclaracaoProcedure(DeclaracaoProcedure d, Object o) {
        idTable.enter(((IdentifierSimples)d.I).spelling, d);
        d.I.declaracao = d; 
        d.C.visit(this, null);
        d.I.visit(this, null);
        d.P.visit(this, null);
        return null;
    }

    public Object visitDeclaracaoProcedureSemArgs(DeclaracaoProcedureSemArgs d, Object o) {
        idTable.enter(((IdentifierSimples)d.I).spelling, d);
        d.I.declaracao = d;
        d.C.visit(this, null);
        d.I.visit(this, null);
        return null;
    }

    public Object visitDeclaracaoSequencial(DeclaracaoSequencial d, Object o) {
        d.D1.visit(this, null);
        d.D2.visit(this, null);
        return null;
    }

    public Object visitDeclaracaoVariavel(DeclaracaoVariavel d, Object o) {
        idTable.enter(((IdentifierSimples)d.I).spelling, d);
        Identifier i = d.I;
        i.declaracao = d;
        d.I.visit(this, null);
        d.T.visit(this, null);
        return d.T;
    }

    public Object visitExpressaoBinaria(ExpressaoBinaria e, Object o) {
        e.ES1.visit(this, null);
        e.ES2.visit(this, null);
        e.OR.visit(this, null);
        e.tipo.kind = Tipo.BOOL_LIT;
        return e.tipo;
    }

    public Object visitExpressaoSequencial(ExpressaoSequencial e, Object o) {
        e.E1.visit(this, null);
        e.E2.visit(this, null);
        return null;
    }

    public Object visitExpressaoSimples(ExpressaoSimples e, Object o) {
        return e.F.visit(this, null);
    }

    public Object visitFatorAdSequencial(FatorAdSequencial f, Object o) {
        Tipo tipo = new TipoSimples("", Tipo.UND);
        
        Tipo fTipo1 = (Tipo)f.F1.visit(this, null);
        Tipo fTipo2 = (Tipo)f.F2.visit(this, null);
        f.OA.visit(this, null);
                
        if (f.OA.spelling.toLowerCase().equals("or") && fTipo1.kind == Token.BOOL_LIT && fTipo2.kind == Token.BOOL_LIT) tipo.kind = Tipo.BOOL_LIT;
        else 
        {
            if (fTipo1.kind == Token.TIPO_SIMPLES && fTipo2.kind == Token.TIPO_SIMPLES)
            {
                Byte bType1, bType2;
                bType1 = Tipo.getTypeByte(((TipoSimples)fTipo1).spelling);
                bType2 = Tipo.getTypeByte(((TipoSimples)fTipo2).spelling);
                if (bType1 == Tipo.FLOAT_LIT || bType2 == Tipo.FLOAT_LIT) tipo.kind = Tipo.FLOAT_LIT;
                else tipo.kind = Tipo.INT_LIT;
            }
            else if (fTipo1.kind == Tipo.FLOAT_LIT || fTipo2.kind == Tipo.FLOAT_LIT)
            {
                if ((fTipo1.kind != Tipo.BOOL_LIT) && (fTipo2.kind != Tipo.BOOL_LIT)) tipo.kind = Tipo.FLOAT_LIT;
                else
                {
                    tipo.kind = Tipo.ERROR;
                    Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Operação com valores incompatíveis! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
                }
            }
            else if (fTipo1.kind == Tipo.INT_LIT && fTipo2.kind == Tipo.INT_LIT) tipo.kind = Tipo.INT_LIT;
            else {
                //tipo agregado
            }
        }
        return tipo;
    }

    public Object visitFatorChamadaFuncao(FatorChamadaFuncao f, Object o) {
        Declaracao d = idTable.retrieve(((IdentifierSimples)f.I).spelling);
        
        if (!(d instanceof DeclaracaoFuncao) || d == null) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Declaração da função inválida! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
        else 
        {
            f.E.visit(this, null);
            return ((DeclaracaoFuncao)d).TS;
        }
        return Tipo.ERROR;
    }

    public Object visitFatorChamadaFuncaoSemArgs(FatorChamadaFuncaoSemArgs f, Object o) {
        Declaracao d = (Declaracao)f.I.declaracao.visit(this, null);
        
        if (!(d instanceof DeclaracaoFuncaoSemArgs) || d == null) Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Declaração da função inválida! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
        else return ((DeclaracaoFuncaoSemArgs)d).TS;
            
        return Tipo.ERROR;
    }

    //retorna Tipo
    public Object visitFatorId(FatorId f, Object o) {
        return ((DeclaracaoVariavel)f.I.declaracao.visit(this, null)).T;
    }

    //retorno null?
    public Object visitFatorMulSequencial(FatorMulSequencial f, Object o) {
        Tipo tipo = null;
        tipo.kind = Tipo.ERROR;
        Tipo fTipo1 = (Tipo)f.F1.visit(this, null);
        Tipo fTipo2 = (Tipo)f.F2.visit(this, null);
        f.OM.visit(this, null);
        
        if (f.OM.spelling.toLowerCase().equals("and") && fTipo1.kind == Token.BOOL_LIT && fTipo2.kind == Token.BOOL_LIT) tipo.kind = Tipo.BOOL_LIT;
        else 
        {
            if (fTipo1.kind == Token.TIPO_SIMPLES && fTipo2.kind == Token.TIPO_SIMPLES)
            {
                Byte bType1, bType2;
                bType1 = Tipo.getTypeByte(((TipoSimples)fTipo1).spelling);
                bType2 = Tipo.getTypeByte(((TipoSimples)fTipo2).spelling);
                if (bType1 == Tipo.FLOAT_LIT || bType2 == Tipo.FLOAT_LIT) tipo.kind = Tipo.FLOAT_LIT;
                else tipo.kind = Tipo.INT_LIT;
            }
            else if (fTipo1.kind == Tipo.FLOAT_LIT || fTipo2.kind == Tipo.FLOAT_LIT)
            {
                if ((fTipo1.kind != Tipo.BOOL_LIT) && (fTipo2.kind != Tipo.BOOL_LIT)) tipo.kind = Tipo.FLOAT_LIT;
                else
                {
                    tipo.kind = Tipo.ERROR;
                    Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Operação com valores incompatíveis! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");
                }
            }
            else if (fTipo1.kind == Tipo.INT_LIT && fTipo2.kind == Tipo.INT_LIT) tipo.kind = Tipo.INT_LIT;
            else {
                //tipo agregado
            }
        }
        return tipo;
    }

    public Object visitFatorExpressao(FatorExpressao f, Object o) {
        return (Tipo)f.E.visit(this, null);
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
        Tipo tipo = new TipoSimples(l.spelling, Tipo.ERROR);
        
        if(l.spelling.toLowerCase().equals("true") || l.spelling.toLowerCase().equals("false")) tipo.kind = Tipo.BOOL_LIT;
        else {
            tipo.kind = Tipo.INT_LIT;
            if (l.spelling.contains(".")) tipo.kind = Tipo.FLOAT_LIT;
        }
        return tipo;
    }

    ///////////////////////////////////////////////////////////////////////////
    public Object visitOpAd(OpAd op, Object o) {
        return null;
    }

    public Object visitOpMul(OpMul op, Object o) {
        return null;
    }

    public Object visitOpRel(OpRel op, Object o) {
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
        if (!v.E.tipo.equalTo(Tipo.INT_LIT))
            Compilador.compilerFrame.setOutputText("ERRO CONTEXTUAL! Índice inválido! " + 
                "(Linha " + Compilador.currentLine + " | Coluna: " + Compilador.currentColumn + ")");        
        return v.I.declaracao.visit(this, null);
    }
}

