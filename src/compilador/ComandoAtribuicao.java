package compilador;
public class ComandoAtribuicao extends Comando{
    Identifier I;
    Expressao E;
    
    public ComandoAtribuicaoIndexada(Identifier I, Expressao E) {
        this.I = I;
        this.E = E;
    }
}
