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
    
    public void visit (Visitor v){
        v.visitDeclaracaoFuncaoSemArgs(this);
    }
}
