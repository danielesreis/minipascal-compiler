package compilador.ast;
public class OpAd extends Operador{
    
    public OpAd(String spelling) {
        super(spelling);
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitOpAd(this, o);
    }
}
