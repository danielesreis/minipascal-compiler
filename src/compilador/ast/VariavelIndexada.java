package compilador.ast;
public class VariavelIndexada extends Variavel{
    public Identifier I;
    public Expressao E;
    
    public VariavelIndexada(Identifier I, Expressao E) {
        this.I = I;
        this.E = E;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitVariavelIndexada(this, o);
    }
}
