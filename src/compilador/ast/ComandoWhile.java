package compilador.ast;
public class ComandoWhile extends Comando{
    public Expressao E;
    public Comando C;
    
    public ComandoWhile(Expressao E, Comando C) {
        this.E = E;
        this.C = C;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitComandoWhile(this, o);
    }
}
