package compilador.ast;
public class DeclaracaoFuncaoSemArgs extends Declaracao{
    Identifier I;
    TipoSimples TS;
    Corpo C;
    
    public DeclaracaoFuncaoSemArgs(Identifier I, TipoSimples TS, Corpo C) {
        this.I = I;
        this.TS = TS;
        this.C = C;
    }
}
