package compilador.ast;
public class TipoAgregado extends Tipo{
    Literal L1, L2;
    Tipo T;
    
    public TipoAgregado(Literal L1, Literal L2, Tipo T) {
        this.L1 = L1;
        this.L2 = L2;
        this.T = T;
    }
}
