package compilador.ast;
public class FatorChamadaFuncaoSemArgs extends Fator{
    Identifier I;
    
    public FatorChamadaFuncaoSemArgs(Identifier I) {
        this.I = I;
    }
    
    public void visit (Visitor v){
        v.visitFatorChamadaFuncaoSemArgs(this);
    }
}
