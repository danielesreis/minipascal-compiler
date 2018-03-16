package compilador.ast;
public class ComandoWhile extends Comando{
    Expressao E;
    Comando C;
    
    public ComandoWhile(Expressao E, Comando C) {
        this.E = E;
        this.C = C;
    }
    
    public void visit (Visitor v){
        v.visitComandoWhile(this);
    }
}
