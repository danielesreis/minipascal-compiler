package compilador.ast;
public class TipoAgregadoBoolean extends TipoAgregado{
    BooleanLiteral BL;
    Tipo T;
    
    public TipoAgregadoBoolean(BooleanLiteral BL, Tipo T) {
        this.BL = BL;
        this.T = T;
    }
}
