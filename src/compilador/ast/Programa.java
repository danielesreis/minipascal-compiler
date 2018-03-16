package compilador.ast;
public class Programa extends AST{
    Identifier I;
    Corpo C;
    
    public Programa(Identifier I, Corpo C) {
        this.I = I;
        this.C = C;
    }
    
    public void visit (Visitor v){
        v.visitPrograma(this);
    }
}
