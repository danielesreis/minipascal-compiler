package compilador.ast;
public class CorpoComDeclaracaoComando extends Corpo{
    public Declaracao D;
    public Comando C;
    
    public CorpoComDeclaracaoComando(Declaracao D, Comando C) {
        this.D = D;
        this.C = C;
    }
    
    public Object visit (Visitor v, Object o){
        v.visitCorpoComDeclaracaoComando(this, o);
        return null;
    }
}
