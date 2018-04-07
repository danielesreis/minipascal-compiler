package compilador.ast;
public class ExpressaoSequencial extends Expressao{
    public Expressao E1, E2;
    
    public ExpressaoSequencial(Expressao E1, Expressao E2) {
        this.E1 = E1;
        this.E2 = E2;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitExpressaoSequencial(this, o);
    }
}
