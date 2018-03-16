package compilador.ast;
public class OpRel extends Identifier{
    
    public OpRel(String spelling) {
        super(spelling);
    }
    
    public void visit (Visitor v){
        v.visitOpRel(this);
    }    
}
