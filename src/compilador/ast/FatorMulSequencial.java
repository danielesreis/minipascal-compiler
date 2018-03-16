package compilador.ast;
public class FatorMulSequencial extends Fator{
    Fator F1, F2;
    OpMul OM;
    
    public FatorMulSequencial(Fator F1, OpMul OM, Fator F2) {
        this.F1 = F1;
        this.OM = OM;
        this.F2 = F2;
    }
    
    public void visit (Visitor v){
        v.visitFatorMulSequencial(this);
    }
}
