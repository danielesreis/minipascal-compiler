package compilador.ast;
public class ExpressaoBinaria extends Expressao{
    public ExpressaoSimples ES1, ES2;
    public OpRel OR;
    
    public ExpressaoBinaria(ExpressaoSimples ES1, ExpressaoSimples ES2, OpRel OR) {
        this.ES1 = ES1;
        this.ES2 = ES2;
        this.OR = OR;
    }
    
    public void visit (Visitor v){
        v.visitExpressaoBinaria(this);
    }
}
