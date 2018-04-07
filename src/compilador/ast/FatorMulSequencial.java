package compilador.ast;
public class FatorMulSequencial extends Fator{
    public Fator F1, F2;
    public OpMul OM;
    
    public FatorMulSequencial(Fator F1, OpMul OM, Fator F2) {
        this.F1 = F1;
        this.OM = OM;
        this.F2 = F2;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitFatorMulSequencial(this, o);
    }
}
