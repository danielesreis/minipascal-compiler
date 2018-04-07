package compilador.ast;
public class FatorChamadaFuncao extends Fator{
    public Identifier I;
    public Expressao E;
    
    public FatorChamadaFuncao(Identifier I, Expressao E) {
        this.I = I;
        this.E = E;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitFatorChamadaFuncao(this, o);
    }

}
