package compilador.ast;
public class ExpressaoSimples extends Expressao{
    Fator F;
    
    public ExpressaoSimples(Fator F) {
        this.F = F;
    }
}
