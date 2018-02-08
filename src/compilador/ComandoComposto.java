package compilador;
public class ComandoComposto extends Comando{
    ComandoSequencial CS;
    
    public ComandoComposto(ComandoSequencial CS) {
        this.CS = CS;
    }
}
