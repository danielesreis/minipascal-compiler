package compilador.ast;
public class TipoAgregadoInteger extends TipoAgregado{
    IntegerLiteral IL;
    Tipo T;
    
    public TipoAgregadoInteger(IntegerLiteral IL, Tipo T) {
        this.IL = IL;
        this.T = T;
    }
}
