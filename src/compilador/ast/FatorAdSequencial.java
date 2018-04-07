package compilador.ast;
public class FatorAdSequencial extends Fator{
    public Fator F1, F2;
    public OpAd OA;
    
    public FatorAdSequencial(Fator F1, OpAd OA, Fator F2) {
        this.F1 = F1;
        this.OA = OA;
        this.F2 = F2;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitFatorAdSequencial(this, o);
    }    
}
