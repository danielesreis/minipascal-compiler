package compilador.ast;

public class ComandoBegin extends Comando{
    public Comando C;
    
    public ComandoBegin(Comando C) {
        this.C = C;
    }

    public Object visit(Visitor v, Object o) {
        return v.visitComandoBegin(this, o);
    }
}
