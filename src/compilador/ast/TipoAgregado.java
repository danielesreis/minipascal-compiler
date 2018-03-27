package compilador.ast;
public class TipoAgregado extends Tipo{
    public Literal L1, L2;
    public Tipo T;
    
    public TipoAgregado(Literal L1, Literal L2, Tipo T) {
        this.L1 = L1;
        this.L2 = L2;
        this.T = T;
    }
    
    public void visit (Visitor v){
        v.visitTipoAgregado(this);
    }
}
