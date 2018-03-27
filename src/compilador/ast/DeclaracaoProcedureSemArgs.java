package compilador.ast;
public class DeclaracaoProcedureSemArgs extends Declaracao{
    public Identifier I;
    public Corpo C;
    
    public DeclaracaoProcedureSemArgs(Identifier I, Corpo C){
        this.I = I;
        this.C = C;
    } 
    
    public void visit (Visitor v){
        v.visitDeclaracaoProcedureSemArgs(this);
    }
}
