package compilador.ast;
public class FatorAdSequencial extends Fator{
    Fator F1, F2;
    OpAd OA;
    
    public FatorAdSequencial(Fator F1, OpAd OA, Fator F2) {
        this.F1 = F1;
        this.OA = OA;
        this.F2 = F2;
    }
    
}
