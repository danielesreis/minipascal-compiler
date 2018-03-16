package compilador.ast;
public class ParametroSequencial extends Parametro{
    Parametro P1, P2;
    
    public ParametroSequencial(Parametro P1, Parametro P2) {
        this.P1 = P1;
        this.P2 = P2;
    }
    
    public void visit (Visitor v){
        v.visitParametroSequencial(this);
    }
}
