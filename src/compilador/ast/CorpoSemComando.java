package compilador.ast;
public class CorpoSemComando extends Corpo{
    public Declaracao D;
    
    public CorpoSemComando(Declaracao D) {
        this.D = D;
    }
    
    public Object visit (Visitor v, Object o){
        v.visitCorpoSemComando(this, o);
        return null;
    }
}
