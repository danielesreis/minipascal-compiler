package compilador.ast;
public class DeclaracaoVariavel extends Declaracao{
    Identifier I;
    Tipo T;
    
    public DeclaracaoVariavel(Identifier I, Tipo T) {
        this.I = I;
        this.T = T;
    }
}
