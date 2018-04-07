package compilador.ast;
public class VariavelId extends Variavel{
    public Identifier I;
    
    public VariavelId(Identifier I) {
        this.I = I;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitVariavelId(this, o);
    }
}
