package compilador.ast;
public class ComandoComposto extends Comando{
    public ComandoSequencial CS;
    
    public ComandoComposto(ComandoSequencial CS) {
        this.CS = CS;
    }
    
    public void visit (Visitor v){
        v.visitComandoComposto(this);
    }
}
