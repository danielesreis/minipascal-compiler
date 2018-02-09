package compilador.ast;
public class DeclaracaoProcedure extends Declaracao{
    Identifier I;
    Parametro P;
    Corpo C;
    
    public DeclaracaoProcedure(Identifier I, Parametro P, Corpo C){
        this.I = I;
        this.P = P;
        this.C = C;
    } 
}
