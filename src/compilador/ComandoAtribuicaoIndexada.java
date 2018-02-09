package compilador;
public class ComandoAtribuicaoIndexada extends Comando{
    Identifier I;
    ExpressaoSequencial ES;
    Expressao E;
    
    public ComandoAtribuicaoIndexada(Identifier I, ExpressaoSequencial ES, Expressao E) {
        this.I = I;
        this.ES = ES;
        this.E = E;
    }
}
