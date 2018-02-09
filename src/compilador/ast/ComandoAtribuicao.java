package compilador.ast;
public class ComandoAtribuicao extends Comando{
    Identifier I;
    Expressao E;
    
    public ComandoAtribuicao(Identifier I, Expressao E) {
        this.I = I;
        this.E = E;
    }
}
