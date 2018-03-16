package compilador.ast;
public class OpAd extends Identifier{
    
    public OpAd(String spelling) {
        super(spelling);
    }
    
    public void visit (Visitor v){
        v.visitOpAd(this);
    }
}
