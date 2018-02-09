package compilador.ast;
public class TipoAgregadoFloat extends TipoAgregado{
    FloatLiteral FL;
    Tipo T;
    
    public TipoAgregadoFloat(FloatLiteral FL, Tipo T){
        this.FL = FL;
        this.T = T;
    }
}
