package compilador;
public class Corpo extends AST{
    DeclaracaoSequencial DS;
    ComandoSequencial CS;
    
    public Corpo(DeclaracaoSequencial DS, ComandoSequencial CS) {
        this.DS = DS;
        this.CS = CS;
    }
}
