package compilador.ast;
public class IdentifierSequencial extends Identifier{
    Identifier I1, I2;

    public IdentifierSequencial(Identifier I1, Identifier I2) {
        super(I1, I2);
    }
    
    public void visit (Visitor v){
        v.visitIdentifierSequencial(this);
    }
}
