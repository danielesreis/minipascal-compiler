package compilador;
public class ComandoAtribuicao extends Comando{
    Identifier I;
    ExpressaoSequencial ES;
    Expressao E;
    
    public ComandoAtribuicao(Identifier I, ExpressaoSequencial ES, Expressao E) {
        this.I = I;
        this.ES = ES;
        this.E = E;
    }
}
