package compilador;
public class ComandoChamadaProcedimento extends Comando{
    Identifier I;
    ExpressaoSequencial ES;
    
    public ComandoChamadaProcedimento(Identifier I, ExpressaoSequencial ES) {
        this.I = I;
        this.ES = ES;
    }
}
