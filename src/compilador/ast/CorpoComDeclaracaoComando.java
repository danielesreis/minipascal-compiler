package compilador.ast;
public class CorpoComDeclaracaoComando extends Corpo{
    Declaracao D;
    Comando C;
    
    public CorpoComDeclaracaoComando(Declaracao D, Comando C) {
        this.D = D;
        this.C = C;
    }
    
    public void visit (Visitor v){
        v.visitCorpoComDeclaracaoComando(this);
    }
}
