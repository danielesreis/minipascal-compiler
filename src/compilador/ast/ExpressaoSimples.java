package compilador.ast;
public class ExpressaoSimples extends Expressao{
    public Fator F;
    
    public ExpressaoSimples(Fator F) {
        this.F = F;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitExpressaoSimples(this, o);
    }
}
