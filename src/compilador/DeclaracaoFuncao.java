package compilador;
public class DeclaracaoFuncao extends Declaracao{
    Identifier I;
    ParametroSequencial PS;
    TipoSimples TS;
    Corpo C;
    
    public DeclaracaoFuncao(Identifier I, ParametroSequencial PS, TipoSimples Ts, Corpo C) {
        this.I = I;
        this.PS = PS;
        this.TS = TS;
        this.C = C;
    }
}
