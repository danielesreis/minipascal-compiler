package compilador.ast;
public class ComandoIf extends Comando{
    public Expressao E;
    public Comando C1;
    
    public ComandoIf(Expressao E, Comando C1) {
        this.E = E;
        this.C1 = C1;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitComandoIf(this, o);
    }
}
