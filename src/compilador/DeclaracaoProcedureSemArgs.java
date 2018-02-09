package compilador;
public class DeclaracaoProcedure extends Declaracao{
    Identifier I;
    Corpo C;
    
    public DeclaracaoProcedureSemArgs(Identifier I, Corpo C){
        this.I = I;
        this.C = C;
    } 
}
