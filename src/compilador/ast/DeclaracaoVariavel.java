package compilador.ast;
public class DeclaracaoVariavel extends Declaracao{
    public Identifier I;
    public Tipo T;
    
    public DeclaracaoVariavel(Identifier I, Tipo T) {
        this.I = I;
        this.T = T;
    }
    
    public void visit (Visitor v){
        v.visitDeclaracaoVariavel(this);
    }
}
