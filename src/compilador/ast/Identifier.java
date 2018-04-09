package compilador.ast;
import compilador.Type;

public abstract class Identifier extends AST {
    public AST declaracao;
    public String spelling;
    public Type type;
}
