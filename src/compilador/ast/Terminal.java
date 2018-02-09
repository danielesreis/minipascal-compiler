package compilador.ast;
public class Terminal extends AST{
    public String spelling;
    
    public Terminal(String spelling) {
        this.spelling = spelling;
    }
}
