package compilador;
public class Expressao extends AST {
    ExpressaoSimples ES1, ES2;
    OpRel OR;
    
    public Expressao(ExpressaoSimples ES1, ExpressaoSimples ES2, OpRel OR) {
        this.ES1 = ES1;
        this.ES2 = ES2;
        this.OR = Or;
    }
}
