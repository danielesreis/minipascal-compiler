package compilador.ast;
public class OpMul extends Operador{
    
    public OpMul(String spelling) {
        super(spelling);
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitOpMul(this, o);
    }
}
