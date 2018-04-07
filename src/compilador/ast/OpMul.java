package compilador.ast;
public class OpMul extends Operador{
    
    public OpMul(String spelling) {
        super(spelling);
    }
    
    public void visit (Visitor v){
        v.visitOpMul(this);
    }
}
