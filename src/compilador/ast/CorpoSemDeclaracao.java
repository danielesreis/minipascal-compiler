package compilador.ast;
public class CorpoSemDeclaracao extends Corpo{
    public Comando C;
    
    public CorpoSemDeclaracao(Comando C){
        this.C = C;
    }
    
    public Object visit (Visitor v, Object o){
        v.visitCorpoSemDeclaracao(this, o);
        return null;
    }
}
