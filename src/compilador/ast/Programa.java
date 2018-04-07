package compilador.ast;
public class Programa extends AST{
    public Identifier I;
    public Corpo C;
    
    public Programa(Identifier I, Corpo C) {
        this.I = I;
        this.C = C;
    }
    
    public Object visit (Visitor v, Object o){
        v.visitPrograma(this, o);
        return null;
    }
}
