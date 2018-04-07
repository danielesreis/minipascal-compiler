package compilador.ast;
public class DeclaracaoFuncaoSemArgs extends Declaracao{
    public Identifier I;
    public TipoSimples TS;
    public Corpo C;
    
    public DeclaracaoFuncaoSemArgs(Identifier I, TipoSimples TS, Corpo C) {
        this.I = I;
        this.TS = TS;
        this.C = C;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitDeclaracaoFuncaoSemArgs(this, o);
    }
}
