package compilador.ast;
public class DeclaracaoProcedure extends Declaracao{
    public Identifier I;
    public Parametro P;
    public Corpo C;
    
    public DeclaracaoProcedure(Identifier I, Parametro P, Corpo C){
        this.I = I;
        this.P = P;
        this.C = C;
    } 
    
    public Object visit (Visitor v, Object o){
        return v.visitDeclaracaoProcedure(this, o);
    }
}
