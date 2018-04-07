package compilador.ast;
public class ParametroSequencial extends Parametro{
    public Parametro P1, P2;
    
    public ParametroSequencial(Parametro P1, Parametro P2) {
        this.P1 = P1;
        this.P2 = P2;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitParametroSequencial(this, o);
    }
}
