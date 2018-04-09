package compilador.ast;

public class IdentifierSimples extends Identifier{
    
    public IdentifierSimples(String spelling) {
        this.spelling = spelling;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitIdentifierSimples(this, o);
    }
    
}
