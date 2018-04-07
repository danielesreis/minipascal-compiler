package compilador.ast;
public abstract class Terminal extends AST{
    //public String spelling1, spelling2;
    //Identifier I1, I2;
    public String spelling;
    
    public Terminal(String spelling) {
        this.spelling = spelling;
    }
    
    /*public Terminal(String spelling1) {
        this.spelling1 = spelling1;
    }
    
    public Terminal(Identifier I1, Identifier I2) {
        this.I1 = I1;
        this.I2 = I2;
    }*/
}
