package compilador.ast;
public abstract class AST {
    public abstract Object visit(Visitor v, Object o);
}
