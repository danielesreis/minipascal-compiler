package compilador.ast;

public class ComandoBegin extends Comando{
    public Comando C;
    
    public ComandoBegin(Comando C) {
        this.C = C;
    }

    public void visit(Visitor v) {
        v.visitComandoBegin(this);
    }
}
