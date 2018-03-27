package compilador.ast;
public class CorpoSemComando extends Corpo{
    public Declaracao D;
    
    public CorpoSemComando(Declaracao D) {
        this.D = D;
    }
    
    public void visit (Visitor v){
        v.visitCorpoSemComando(this);
    }
}
