package compilador.ast;
public class Literal extends Fator{
    public String spelling;
    
    public Literal(String spelling) {
        this.spelling = spelling;
    }
    
    public void visit (Visitor v){
        v.visitLiteral(this);
    }
}
