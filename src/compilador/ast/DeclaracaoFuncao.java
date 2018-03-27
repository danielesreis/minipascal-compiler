package compilador.ast;
public class DeclaracaoFuncao extends Declaracao{
    public Identifier I;
    public Parametro P;
    public TipoSimples TS;
    public Corpo C;
    
    public DeclaracaoFuncao(Identifier I, Parametro P, TipoSimples TS, Corpo C) {
        this.I = I;
        this.P = P;
        this.TS = TS;
        this.C = C;
    }
    
    public void visit (Visitor v){
        v.visitDeclaracaoFuncao(this);
    }
}
