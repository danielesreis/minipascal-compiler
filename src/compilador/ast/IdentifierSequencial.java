package compilador.ast;
public class IdentifierSequencial extends Identifier{
    public Identifier I1, I2;

    public IdentifierSequencial(Identifier I1, Identifier I2) {
        this.I1 = I1;
        this.I2 = I2;
    }
    
    public void visit (Visitor v){
        v.visitIdentifierSequencial(this);
    }
}
