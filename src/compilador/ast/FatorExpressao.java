package compilador.ast;

public class FatorExpressao extends Fator{
    public Expressao E;
    
    public FatorExpressao(Expressao E) {
        this.E = E;
    }

    public Object visit (Visitor v, Object o){
        return v.visitFatorExpressao(this, o);
    }
}
