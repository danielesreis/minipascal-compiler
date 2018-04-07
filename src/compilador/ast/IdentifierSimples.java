package compilador.ast;

public class IdentifierSimples extends Identifier{
    public String spelling;
    
    public IdentifierSimples(String spelling) {
        this.spelling = spelling;
    }
    
    public void visit(Visitor v) {
        v.visitIdentifierSimples(this);
    }
    
}
