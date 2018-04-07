package compilador.ast;
public class Literal extends Fator{
    public String spelling;
    
    public Literal(String spelling) {
        this.spelling = spelling;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitLiteral(this, o);
    }
}
