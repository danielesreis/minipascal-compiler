package compilador.ast;
public class Identifier extends Terminal {

    public Identifier(Identifier I1, Identifier I2) {
        super(I1, I2);
    }
    
    public Identifier(String spelling) {
        super(spelling);
    }
}
