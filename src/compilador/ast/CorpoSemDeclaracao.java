package compilador.ast;
public class CorpoSemDeclaracao extends Corpo{
    Comando C;
    
    public CorpoSemDeclaracao(Comando C){
        this.C = C;
    }
    
    public void visit (Visitor v){
        v.visitCorpoSemDeclaracao(this);
    }
}
