package compilador.ast;
public class OpRel extends Operador{
    
    public OpRel(String spelling) {
        super(spelling);
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitOpRel(this, o);
    }    
}
