package compilador.ast;
public class FatorId extends Fator{
    public Identifier I;
    
    public FatorId(Identifier I) {
        this.I = I;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitFatorId(this, o);
    }
}
