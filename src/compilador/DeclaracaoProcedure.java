package compilador;
public class DeclaracaoProcedure extends Declaracao{
    Identifier I;
    ParametroSequencial PS;
    Corpo C;
    
    public DeclaracaoProcedure(Identifier I, ParametroSequencial PS, Corpo C){
        this.I = I;
        this.PS = PS;
        this.C = C;
    } 
}
