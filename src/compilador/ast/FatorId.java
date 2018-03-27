package compilador.ast;
public class FatorId extends Fator{
    public Identifier I;
    
    public FatorId(Identifier I) {
        this.I = I;
    }
    
     public void visit (Visitor v){
        v.visitFatorId(this);
    }
}
