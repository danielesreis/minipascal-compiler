package compilador.ast;
public class FatorChamadaFuncaoSemArgs extends Fator{
    public Identifier I;
    
    public FatorChamadaFuncaoSemArgs(Identifier I) {
        this.I = I;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitFatorChamadaFuncaoSemArgs(this, o);
    }
}
