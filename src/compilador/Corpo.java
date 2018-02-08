package compilador;
public class Corpo extends AST{
    DeclaracaoSequencial DS;
    ComandoSequencial CS;
    
    public Corpo(DeclaracaoSequencial DS, Comando Sequencial CS) {
        this.DS = DS;
        this.CS = CS;
    }
}
