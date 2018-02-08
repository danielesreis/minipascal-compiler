package compilador;
public class ComandoSequencial extends Comando{
    Comando C1, C2;
    
    public ComandoSequencial(Comando C1, Comando C2) {
        this.C1 = C1;
        this.C2 = C2;
    }
}
