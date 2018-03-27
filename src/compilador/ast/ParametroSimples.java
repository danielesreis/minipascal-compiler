package compilador.ast;
public class ParametroSimples extends Parametro{
    public Identifier I;
    public TipoSimples TS;
    
    public ParametroSimples(Identifier I, TipoSimples TS) {
        this.I = I;
        this.TS = TS;
    }
    
    public void visit (Visitor v){
        v.visitParametroSimples(this);
    }
}
