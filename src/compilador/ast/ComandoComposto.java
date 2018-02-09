package compilador.ast;
public class ComandoComposto extends Comando{
    ComandoSequencial CS;
    
    public ComandoComposto(ComandoSequencial CS) {
        this.CS = CS;
    }
}
