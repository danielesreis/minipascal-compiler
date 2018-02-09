package compilador.ast;
public class ComandoAtribuicaoIndexada extends Comando{
    Identifier I;
    Expressao E1, E2;
    
    public ComandoAtribuicaoIndexada(Identifier I, Expressao E1, Expressao E) {
        this.I = I;
        this.E1 = E1;
        this.E2 = E2;
    }
}
