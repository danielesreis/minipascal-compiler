package compilador;
public class ComandoIf extends Comando{
    Expressao E;
    Comando C1;
    
    public ComandoIf(Expressao E, Comando C1) {
        this.E = E;
        this.C1 = C1;
    }
}
