package compilador.ast;
public class VariavelIndexada extends Variavel{
    Identifier I;
    Expressao E;
    
    public VariavelIndexada(Identifier I, Expressao E) {
        this.I = I;
        this.E = E;
    }
}
