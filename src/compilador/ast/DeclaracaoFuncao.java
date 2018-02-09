package compilador.ast;
public class DeclaracaoFuncao extends Declaracao{
    Identifier I;
    Parametro P;
    TipoSimples TS;
    Corpo C;
    
    public DeclaracaoFuncao(Identifier I, Parametro P, TipoSimples TS, Corpo C) {
        this.I = I;
        this.P = P;
        this.TS = TS;
        this.C = C;
    }
}
