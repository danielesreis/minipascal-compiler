package compilador;
public class ComandoIf extends Comando{
    Expressao E;
    Comando C1, C2;
    
    public ComandoIf(Expressao E, Comando C1, Comando C2) {
        this.E = E;
        this.C1 = C1;
        this.C2 = C2;
    }
}
