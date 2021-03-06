package compilador.ast;
public class ComandoAtribuicaoIndexada extends Comando{
    public Identifier I;
    public Expressao E1, E2;
    
    public ComandoAtribuicaoIndexada(Identifier I, Expressao E1, Expressao E2) {
        this.I = I;
        this.E1 = E1;
        this.E2 = E2;
    }
    
    public Object visit (Visitor v, Object o){
        return v.visitComandoAtribuicaoIndexada(this, o);
    }
}
