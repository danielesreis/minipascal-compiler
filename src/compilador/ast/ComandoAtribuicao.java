package compilador.ast;
public class ComandoAtribuicao extends Comando{
    public Identifier I;
    public Expressao E;
    
    public ComandoAtribuicao(Identifier I, Expressao E) {
        this.I = I;
        this.E = E;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitComandoAtribuicao(this, o);
    }
}
