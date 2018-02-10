package compilador.ast;
public class FatorChamadaFuncao extends Fator{
    Identifier I;
    Expressao E;
    
    public FatorChamadaFuncao(Identifier I, Expressao E) {
        this.I = I;
        this.E = E;
    }
}
