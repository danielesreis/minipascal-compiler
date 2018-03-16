package compilador.ast;
public class VariavelId extends Variavel{
    Identifier I;
    
    public VariavelId(Identifier I) {
        this.I = I;
    }
    
    public void visit (Visitor v){
        v.visitVariavelId(this);
    }
}
