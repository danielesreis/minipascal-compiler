package compilador.ast;
public class ExpressaoSimples extends Expressao{
    Fator F;
    
    public ExpressaoSimples(Fator F) {
        this.F = F;
    }
    
    public void visit (Visitor v){
        v.visitExpressaoSimples(this);
    }
}
