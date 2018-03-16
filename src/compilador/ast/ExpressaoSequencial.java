package compilador.ast;
public class ExpressaoSequencial extends Expressao{
    Expressao E1, E2;
    
    public ExpressaoSequencial(Expressao E1, Expressao E2) {
        this.E1 = E1;
        this.E2 = E2;
    }
    
    public void visit (Visitor v){
        v.visitExpressaoSequencial(this);
    }
}
