package compilador.ast;

public class FatorExpressao extends Fator{
    public Expressao E;
    
    public FatorExpressao(Expressao E) {
        this.E = E;
    }

    public void visit(Visitor v) {
        v.visitFatorExpressao(this);
    }
}
